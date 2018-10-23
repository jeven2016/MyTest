package aop.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@ContextConfiguration(classes = AnnotationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ParameterTest implements ApplicationContextAware {
  ApplicationContext ctx;

  @Test
  public void testShowInfo() {
    System.out.println("=============testShowInfo========================");
    Person person = new Person();
    person.setName("wzj");

    City city = new City();
    city.setName("NanJing");

    ArrayList list = new ArrayList();
    list.add(person);

    IParameter parameter = (IParameter) ctx.getBean("Parameter");
    parameter.showInfo(list, city);
    System.out.println("\nfrom testShowInfo: show Info");
    System.out.println("=====================================");
  }

  @Test
  public void testLiveTTL() {
    System.out.println("=============testShowInfo========================");
    IParameter parameter = (IParameter) ctx.getBean("Parameter");
    parameter.getLiveTTL("ttl", 20);
    System.out.println("from testLiveTTL: show LiveTTL");
    System.out.println("=====================================");
  }

  @Test
  public void testAfterReturning() {
    System.out.println("=============testShowInfo========================");
    IParameter parameter = (IParameter) ctx.getBean("Parameter");
    parameter.getLiveTTL("ttl", 20);
    System.out.println("from testLiveTTL: show LiveTTL");
    System.out.println("=====================================");
  }

  @Test
  public void testThrow() {
    System.out.println("=============testThrow========================");
    IParameter parameter = (IParameter) ctx.getBean("Parameter");
    parameter.throwExcep();
    System.out.println("from testThrow: throw RuntimeException");
    System.out.println("=====================================");
  }

  @Test
  public void testThrow2() {
    System.out.println("=============testThrow2========================");
    IParameter parameter = (IParameter) ctx.getBean("Parameter");
    try {
      parameter.throwExcep2();
    } catch (Exception e) {
      System.out.println("from testThrow2: an exception ->" + e.getMessage());
    }
    System.out.println("from testThrow2: throw checked Exception");
    System.out.println("=====================================");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}
