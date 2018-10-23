package aop.simple;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:aop/simple/aop.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AopXmlTest implements ApplicationContextAware {
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

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}
