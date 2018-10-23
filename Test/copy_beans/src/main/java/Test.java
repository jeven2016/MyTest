import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class Test {

  /**
   * 使用CgLib的拷贝实现
   *
   * @param count
   */
  public static void testCglibCopy(int count) {
    Middle m1 = new Middle();
    m1.setFlag(true);
    m1.setId(1000L);
    m1.setItem(new int[]{10, 20, 30});
    m1.setPrice(12.33);
    m1.setType("hahaha!");
    m1.setDate(new Date());
    long t1 = System.currentTimeMillis();

    BeanCopier copier = BeanCopier.create(Middle.class, MiddleDto.class, false);
    for (int i = 0; i < count; i++) {
      MiddleDto mg = new MiddleDto();
      copier.copy(m1, mg, null);
    }
    System.out.println("BeanCopier-time1:"
            + (System.currentTimeMillis() - t1));
  }

  /**
   * 使用Dozer拷贝
   *
   * @param count
   */
  public static void testMapObjectToObject(int count) {
    Middle m1 = new Middle();
    m1.setFlag(true);
    m1.setId(1000L);
    m1.setItem(new int[]{10, 20, 30});
    m1.setPrice(12.33);
    m1.setType("hahaha!");
    m1.setDate(new Date());
    long t1 = System.currentTimeMillis();
    Mapper mapper = new DozerBeanMapper();
    for (int i = 0; i < count; i++) {
      MiddleDto mg = new MiddleDto();

      MiddleDto destObject = (MiddleDto) mapper.map(m1, MiddleDto.class);
    }
    System.out.println("DozerBeanMapper-time1:"
            + (System.currentTimeMillis() - t1));
  }

  /**
   * 使用PropertyCopy
   *
   * @param count
   */
  public static void testPropertyCopyObjectObject(int count) {
    Middle m1 = new Middle();
    m1.setFlag(true);
    m1.setId(1000L);
    m1.setItem(new int[]{10, 20, 30});
    m1.setPrice(12.33);
    m1.setType("hahaha!");
    m1.setDate(new Date());
    long t1 = System.currentTimeMillis();

    for (int i = 0; i < count; i++) {
      MiddleDto mg = new MiddleDto();
      try {
        PropertyUtils.copyProperties(mg, m1);
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("PropertyUtils-time2:"
            + (System.currentTimeMillis() - t1));
  }

  /**
   * 使用Bean Copy
   *
   * @param count
   */
  public static void testBeanCopyObjectObject(int count) {
    Middle m1 = new Middle();
    m1.setFlag(true);
    m1.setId(1000L);
    m1.setItem(new int[]{10, 20, 30});
    m1.setPrice(12.33);
    m1.setType("hahaha!");
    m1.setDate(new Date());
    long t1 = System.currentTimeMillis();

    for (int i = 0; i < count; i++) {
      MiddleDto mg = new MiddleDto();
      try {
        BeanUtils.copyProperties(mg, m1);
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("BeanUtils-time3:"
            + (System.currentTimeMillis() - t1));
  }

  /**
   * 使用cglib的BeanCopier性能最高
   */
  public static void main(String[] args) {
    int total = 1000000;
    for (int i = 0; i < 4; i++) {
      System.out.println("=======================");
      Test.testPropertyCopyObjectObject(total);
      Test.testBeanCopyObjectObject(total);
      Test.testMapObjectToObject(total);
      Test.testCglibCopy(total);
      System.out.println("=======================");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
