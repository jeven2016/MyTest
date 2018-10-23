package ejb3.server;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

public class StateCalInterceptor {

  @AroundInvoke
  public Object intercept(InvocationContext ctx) throws Exception {
    System.out.println("Intercept: "+ctx.getMethod().getName());
    return ctx.proceed();
  }
}
