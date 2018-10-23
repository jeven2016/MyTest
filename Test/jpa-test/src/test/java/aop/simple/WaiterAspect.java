package aop.simple;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * 切点函数有：
 *
 * @annotation within/@within
 * args/@args
 * target/@target
 * this
 */
@Aspect
@Component
public class WaiterAspect /*implements Order*/ {

/*
  @Before("this(waiter)")
  public void testThis(LucyWaiter waiter){
    System.out.println("From testThis: hello" + waiter.getName());
  }
*/

  /**
   * 匹配：
   * 返回值为任何类型 *
   * 包下Iwaiter及其实现类、子类中的包含gree的方法
   * 参数为任何类型 ..
   */
  @Before("execution(* aop.simple.IWaiter+.gree*(..))")
  public void interceptGreeTo() {
    System.out.println("From interceptor: hello sir, may I help you?");
  }

  /**
   * 后置匹配:
   * 方法为publi类型
   * 包下Iwaiter及其实现类、子类中的包含gree的方法
   * 参数为任何类型 ..
   */
  @AfterReturning("execution(public * aop.simple.IWaiter+.service*(..))")
  public void interceptService() {
    System.out.println("From interceptor: sir, the waiter has serviced for you");
  }

  @Before("args(aop.simple.IWaiter)")  //args参数包含了类及其子类
//  @Before("execution(* *(aop.simple.IWaiter+))") //两者等效
  public void intercepWashHandsByName() {
    System.out.println("intercepWashHandsByName: before wash not my hands");
  }

  /**
   * within 最小拦截粒度是class无法拦截method(不加+号，则无法匹配到子类实现类中去)
   */
  @Before("within(aop.simple..IWaiter+)")
  public void testWithin() {
    System.out.println("from testWithin: all class should be intercepted by this method");
  }

  /**
   * target 最小拦截粒度是class无法拦截method，不需要添加+号，会默认包括子类、实现类，会对类的所有方法进行
   * 拦截
   */
  @Before("target(aop.simple.IWaiter)")
  public void tesTarget() {
    System.out.println("from tesTarget: tesTarget method");
  }

  /**
   * @within只能后跟注解类型的接口或类，如果是普通的POJO则会抛出异常, 这里会拦截所有标记了@SaveToMaster的类及其子类中的方法
   */
  @Before("@within(aop.simple.SaveToMaster)")
  public void testAtWithin() {
    System.out.println("from testAtWithin: all class all sub-class of IWaiter should be intercepted by this method");
  }

  /**
   * 对所有标记了@NeedForLog的类进行拦截(注意不是方法，如果在方法上使用了该注解则是无效拦截),
   * 这里使用对PointCut切点的引用进行拦截
   */
  @Pointcut("@annotation(aop.simple.NeedForLog)")
  public void interceptForLog() {
    System.out.println("from interceptForLog: I got a single to save log");
  }

  /**
   * 引用interceptForLog方法上的PointCut注解,如果是其他类上的注解需要添加完整的包名
   */
  @Before("interceptForLog()")
  public void doForLog() {
    System.out.println("from doForLog: I got a single to save log");
  }


  @After("within(aop.simple..IWaiter+) && execution(public * greeTo(..))")
  public void joinInterceptAfter() {
    System.out.println("form joinInterceptAfter: hello, within & execution.");

  }

  /**
   * 拦截IWaiter接口实现类及子类的public greeTo方法。
   */
  @AfterReturning("within(aop.simple..IWaiter+) && execution(public * greeTo(..))")
  public void joinInterceptAfterReturn() {
    System.out.println("form joinInterceptAfterReturn: hello, within & execution.");

  }

  /**
   * Around 增强,可以根据情况进行是否调用的处理
   */
  @Around("execution(* aop.simple..setName(..)) && target(aop.simple.IWaiter)")
  public void firtAround(ProceedingJoinPoint joinPoint) {
    System.out.println("-------------Around output-----------");
    System.out.print("方法的参数为：");
    Object[] args = joinPoint.getArgs();
    for (Object arg : args) {
      System.out.print(arg + ",");
    }
    System.out.println();

    String kind = joinPoint.getKind();
    System.out.println("kind : " + kind);

    Signature signature = joinPoint.getSignature();
    System.out.println("signatrue:");
    System.out.println("\t name=" + signature.getName());
    System.out.println("\t DeclaringTypeName=" + signature.getDeclaringTypeName());
    System.out.println("\t DeclaringType=" + signature.getDeclaringType());
    System.out.println("\t Modifiers=" + signature.getModifiers());
    System.out.println();

    SourceLocation sourceLocation = joinPoint.getSourceLocation();
    System.out.println("SourceLocation:");
//    System.out.println("\t fileName=" + sourceLocation.getFileName());    //this will throw a exception
//    System.out.println("\t line=" + sourceLocation.getLine());
    System.out.println("\t WithinType=" + sourceLocation.getWithinType());
    System.out.println("\t class=" + sourceLocation.getClass());

    System.out.println("getTarget: " + joinPoint.getTarget());
    System.out.println("getThis: " + joinPoint.getThis());
    System.out.println("toLongString: " + joinPoint.toLongString()); //输出AOP表达式
    System.out.println("toShortString: " + joinPoint.toShortString());

    System.out.println("-------------End Of Around output-----------");

    String name = (String) args[0];
    if (name.equals("lucy")) {
      try {
        //reset name
        joinPoint.proceed(new String[]{"name equals to lucy"});
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    } else if (name.equals("")) {
      //return and no callback to that method
    } else {
      try {
        //reset name
        joinPoint.proceed(new String[]{"name is " + name});
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }


  }


  /**
   * 绑定连接点入参
   * ==============================================================================================
   * ==============================================================================================
   * ==============================================================================================
   * ==============================================================================================
   */

  /**
   * 拦截方法后并将参数传递到此方法
   */
  @Before("target(aop.simple.IParameter) && args(persons,city)")
  public void fistParamA(ArrayList<Person> persons, City city) {
    System.out.format("from fistParamA: persons.size=%d, city=%s", persons.size(), city);
  }

  @AfterReturning(value = "execution(* aop.simple.IParameter+.getLiveTTL(..))", returning = "val")
  public void afterReturning(int val) {
    System.out.println("from afterReturning: " + val);
  }

  @AfterThrowing(value = "execution(* aop.simple.IParameter+.throwExcep(..))", throwing = "exp")
  public void testThrowing(RuntimeException exp) {
    System.out.println("from testThrowing: the exception is " + exp.getMessage());
  }

  @AfterThrowing(value = "execution(* aop.simple.IParameter+.throwExcep2(..))", throwing = "exp")
  public void testThrowing2(Exception exp) {
    System.out.println("from testThrowing2: the exception is " + exp.getMessage());
  }


  /**
   * 实现Order接口提供不同切面拦截的顺序
   * @return
   */
 /* @Override
  public int value() {
    return 0;
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    return null;
  }  */
}
