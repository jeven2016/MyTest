package ejb3.server;


import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.util.concurrent.atomic.AtomicInteger;

@Stateful(name = "StateCal", mappedName = "StateCal")
@Remote(IStateCalRemote.class)
@Local(IStateCalLocal.class)
@Interceptors(StateCalInterceptor.class)
public class StateCal implements IStateCalLocal, IStateCalRemote {
  AtomicInteger value = new AtomicInteger(0);

  @EJB(name="HelloWorld")
  private IHelloWorldLocal helloWorldLocal;

  @Override
  public int add() {
    int num = value.incrementAndGet();
    return num;
  }

  @Override
  @Interceptors({StateCalInterceptor.class})
  public int del() {
    int num = value.decrementAndGet();
    return num;
  }

  @Override
  public String getInfo() {
    return helloWorldLocal.say(value.get()+"");
  }


}
