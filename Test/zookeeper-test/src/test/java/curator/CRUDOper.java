package curator;


import junit.framework.Assert;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.BeforeClass;
import org.junit.Test;
import zookeeper.ServerStarter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CRUDOper {
  private static CuratorFramework zkClient;
  private CountDownLatch cl = new CountDownLatch(4);

  @BeforeClass
  public static void bfCls() {
   /* Thread thr = startEmbededServer();
    thr.start();*/

    RetryPolicy rp = new ExponentialBackoffRetry(100, 1);
    zkClient = CuratorFrameworkFactory.builder().connectString
            ("127.0.0.1:2181")
            .retryPolicy(rp)
            .connectionTimeoutMs(1000)
            .sessionTimeoutMs(10000)
            .namespace("myspace") //会在根目录下创建/myspace节点，之后创建的节点均在此Node之下
            .build();
    zkClient.start();
  }

  private static Thread startEmbededServer() {
    //start a zookeeper server
    return new Thread(() -> {
      try {
        //zookeeper服务器三秒后才启动
        TimeUnit.SECONDS.sleep(4);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      ServerStarter.start();
    });
  }

  @Test
  public void testCreate() {
    try {
      //真正创建的节点路径为:/namespace/auth2
      zkClient.create().forPath("/auth2");
      zkClient.checkExists().forPath("/auth2");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDeeplyCreate() {
    try {
      if (zkClient.checkExists().forPath("/auth2/tree1") != null) {
        zkClient.delete().forPath("/auth2/tree1");
      }
      zkClient.create().creatingParentsIfNeeded().forPath("/auth2/tree1", "helloWorld".getBytes());
      String value = new String(zkClient.getData().forPath("/auth2/tree1"));
      System.out.println("testDeeplyCreate::getData of /auth2/tree1: " + value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateTempNode() {
    try {
      //ensure the parent node has been created
      testDeeplyCreate();
      //创建带有序号的持久数据
      zkClient.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath
              ("/auth2/tree1/temp");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCreateTemp() throws Exception {
    //创建临时数据Node
    zkClient.create().withMode(CreateMode.EPHEMERAL).forPath
            ("/auth2/tree1/tempNoPersistent", "hello2".getBytes());
    System.out.println(new String(zkClient.getData().forPath
            ("/auth2/tree1/tempNoPersistent")));

    Assert.assertTrue(zkClient.checkExists().forPath
            (("/auth2/tree1/tempNoPersistent")) != null);
  }

  @Test
  public void getChildren() {
    try {
      //这里的根目录是"/myspace"
      List<String> foundList = zkClient.getChildren().forPath("/");
      foundList.forEach(path -> {
        System.out.println(path);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDelete() {
    try {
      Stat stat = zkClient.checkExists().forPath("/auth2");
      if (stat == null) {
        System.out.println("testDelete(): /myspace/auth2 is not " +
                "existed.");
      } else {
        zkClient.delete().forPath("/auth2");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试Watch功能
   */
  @Test
  public void testListener() {
    Executor executor = Executors.newFixedThreadPool(3);

    //注册监听器
    zkClient.getCuratorListenable().addListener(new CuratorListener() {
      @Override
      public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
        System.out.println(Thread.currentThread().getName()
                + "------------------------");
        System.out.println("getPath:" + event.getPath());
        System.out.println("getName:" + event.getName());
        System.out.println("getType:" + event.getType());
        System.out.println("getData:" + event.getData());
        System.out.println("getWatchedEvent().getWrapper().getType:" + event.getWatchedEvent().getWrapper().getType());
        System.out.println("-------------------------------");
      }
    }, executor);

    try {
      //创建4个异步执行的任务
      List<Callable<String>> tsks = get4Tasks();

      //创建只有一个线程的线程池，串行执行
      ScheduledExecutorService executor1 = Executors.newScheduledThreadPool(1);
      List<Future<String>> result = executor1.invokeAll(tsks);
      result.forEach(future -> {
        try {
          String val = future.get().toString();
          System.out.println(Thread.currentThread().getName() + ": " + val);
          TimeUnit.SECONDS.sleep(3);
          cl.countDown();
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      //主线程等待结束
      cl.await();
      System.out.println("game over.");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private List<Callable<String>> get4Tasks() {
    List<Callable<String>> callables = new ArrayList<>();

    //create
    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        zkClient.create().inBackground().forPath("/ltn");
        return "create";
      }
    };
    callables.add(callable);

    //get
    callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        zkClient.getData().inBackground().forPath("/ltn");
        return "getData";
      }
    };
    callables.add(callable);

    //set
    callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        zkClient.setData().inBackground().forPath("/ltn", "setvalue".getBytes(Charset
                .forName("utf-8")));
        return "setData";
      }
    };
    callables.add(callable);

    //set
    callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        zkClient.delete().inBackground().forPath("/ltn");
        return "delete";
      }
    };
    callables.add(callable);
    return callables;
  }
}
