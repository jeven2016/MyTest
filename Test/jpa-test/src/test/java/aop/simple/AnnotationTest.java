package aop.simple;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = AnnotationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnotationTest implements ApplicationContextAware {
  private ApplicationContext ctx;

  @org.junit.Test
  public void sayGreeTo() {
    System.out.println("=============sayGreeTo========================");
    IWaiter waiter = (IWaiter) ctx.getBean("simpleWaiter");
    waiter.greeTo();
    System.out.println("=====================================");
  }

  @org.junit.Test
  public void doService() {
    System.out.println("=============doService========================");
    IWaiter waiter = (IWaiter) ctx.getBean("simpleWaiter");
    waiter.service();
    System.out.println("=====================================");
  }

  @org.junit.Test
  public void washJasonHand() {
    System.out.println("=============washJasonHand========================");
    SimpleWaiter waiter = (SimpleWaiter) ctx.getBean("simpleWaiter");
    IWaiter jason = new SimpleWaiter();
    jason.setName("jason");
    waiter.washHands(jason);
    System.out.println("=====================================");
  }

  @org.junit.Test
  public void testNeedForLogAnno() {
    /**
     * 测试对自定义注解@NeedForLog的拦截
     */
    System.out.println("=============testNeedForLogAnno========================");
    LucyWaiter waiter = (LucyWaiter) ctx.getBean("LucyWaiter");
    waiter.goAway();
    System.out.println("=====================================");
  }

  @org.junit.Test
  public void testAround() {
    /**
     * 测试Around
     */
    System.out.println("=============testAround========================");
    LucyWaiter waiter = (LucyWaiter) ctx.getBean("LucyWaiter");
    waiter.setName("lucy2");
    System.out.println("real name =" + waiter.getName());
    System.out.println("=====================================");
  }

  @org.junit.Test
  public void testAround2() {
    /**
     * 测试Around
     */
    System.out.println("=============testAround========================");
    LucyWaiter waiter = (LucyWaiter) ctx.getBean("LucyWaiter");
    waiter.setName("");

    //名字为空时，不会对setName进行调用，所以名称不会为空字符串
    System.out.println("real name ="+waiter.getName());
    System.out.println("=====================================");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}
