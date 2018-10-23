package aop.simple;

@SaveToMaster("master1")
public class WomanWaiter implements IWaiter {
  private String name;
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
   this.name = name;
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
  public void goAway() {
    pt("From WomanWaiter: I'm going away");
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
