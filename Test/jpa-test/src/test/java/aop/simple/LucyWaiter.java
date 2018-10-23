package aop.simple;

public class LucyWaiter extends WomanWaiter {

  private String name;
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  private void privateSay(){
    pt("From LucyWaiter: I cannot say anything");
  }

  @Override
  public void greeTo() {
    pt("From WomanWaiter: say hello");
  }

  @Override
  public void service() {
    pt("From WomanWaiter: let me service for you");
  }

  @Override
  @NeedForLog
  public void goAway() {
    pt("From WomanWaiter: I'm going away");
    pt("this method has a call to a private method: privateSay");
  }

  public void washHands() {
    pt("From WomanWaiter: I'm washing my hands.");
  }

  public void washHands(IWaiter waiter) {
    pt("From WomanWaiter: I'm washing " + name + " hands.");
  }

  public void pt(String msg) {
    System.out.println(msg);
  }
}
