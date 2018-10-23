import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ListCopyTest {
  public static void main(String[] args) {
    testPropertiesCopy();
//    testCglibCopy();
  }

  public static void testPropertiesCopy() {
    List<Middle> middleList = generateMiddleList();
    List<MiddleDto> dtoList = generateMiddleDtoList();

    try {
      BeanUtils.copyProperties(dtoList, middleList);
      System.out.println("testPropertiesCopy:"+ dtoList.get(0).getId());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  public static void testCglibCopy() {
    List<Middle> middleList = generateMiddleList();
    List<MiddleDto> dtoList = generateMiddleDtoList();

    BeanCopier copier = BeanCopier.create(List.class, List.class, false);
    copier.copy(middleList,dtoList,null);
    System.out.println();
  }

  public static List<Middle> generateMiddleList() {
    List<Middle> list = new ArrayList<>();
    Middle middle = new Middle();
    middle.setId(11);
    list.add(middle);

    middle = new Middle();
    middle.setId(12);
    list.add(middle);
    return list;
  }

  public static List<MiddleDto> generateMiddleDtoList() {
    List<MiddleDto> list = new ArrayList<>();
    MiddleDto middle = new MiddleDto();
    list.add(middle);

    middle = new MiddleDto();
    list.add(middle);
    return list;
  }


}
