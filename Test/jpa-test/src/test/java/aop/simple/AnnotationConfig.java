package aop.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

/**
 * Created by root on 14-12-9.
 */

@Configuration
@ComponentScan("aop.simple")
@EnableAspectJAutoProxy(proxyTargetClass = true)
/*
<tx:annotation-driven transaction-manager="transactionManager"
        proxy-target-class="true"/>
        　　注意：proxy-target-class属性值决定是基于接口的还是基于类的代理被创建。
        如果proxy-target-class 属性值被设置为true，那么基于类的代理将起作用（这时需要cglib库）。
        如果proxy-target-class属值被设置为false或者这个属性被省略，那么标准的JDK 基于接口的代理将起作用。
        即使你未声明 proxy-target-class="true" ，但运行类没有继承接口，spring也会自动使用CGLIB代理。
        高版本spring自动根据运行类选择 JDK 或 CGLIB 代理*/

public class AnnotationConfig {

  @Bean(name = "simpleWaiter")
  public IWaiter simpleWaiter() {
    IWaiter waiter = new SimpleWaiter();
    waiter.setName("waiterName");
    return waiter;
  }

  @Bean(name = "WomanWaiter")
  public IWaiter womanWaiter() {
    IWaiter waiter = new WomanWaiter();
    waiter.setName("WomanWaiter");
    return waiter;
  }

  @Bean(name = "LucyWaiter")
  @Scope("prototype")
  public IWaiter lucyWaiter() {
    IWaiter waiter = new LucyWaiter();
    waiter.setName("LucyWaiter");
    return waiter;
  }

  @Bean(name = "Parameter")
  @Scope("prototype")
  public IParameter parameter() {
    IParameter parameter = new ParameterFirst();
    return parameter;
  }

}
