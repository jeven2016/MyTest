package aop.simple;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component("simpleWaiter")
public class SimpleWaiter implements IWaiter {
  private String name = "default";

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void greeTo() {
    pt("From SimpleWaiter: say hello");
  }

  @Override
  public void service() {
    pt("From SimpleWaiter: let me service for you");
  }

  @Override
  public void goAway() {
    pt("From SimpleWaiter: I'm going away");
  }

  public void washHands() {
    pt("From SimpleWaiter: I'm washing my hands.");
  }

  public void washHands(IWaiter waiter) {
    pt("From SimpleWaiter: I'm washing " + name + " hands.");
  }

  public void pt(String msg) {
    System.out.println(msg);
  }
}
