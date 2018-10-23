package test.single_1_N;

import zjtech.bmf.inject.zjtech.entity.ClothesEntity;
import zjtech.bmf.inject.zjtech.entity.PersonEntity;
import zjtech.bmf.inject.zjtech.rep.ClothesRep;
import zjtech.bmf.service.ClothesService;
import zjtech.bmf.service.PersonService;
import zjtech.bmf.inject.zjtech.util.MysqlJpaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@ContextConfiguration(classes = MysqlJpaConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true) //aop:aspectj-autoproxy 配置
//@PropertySource({"classpath:site-jdbc.properties"})

//@Transactional //这个注解不添加出异常不回滚
public class PersonClothes_UTest {

  @Autowired
  PersonService personService;

  @Autowired
  ClothesService clothesService;

  @Autowired
  ClothesRep clothesRep;

  /**
   * 级联增加Person与Clothes
   */
  @Test
  public void addPesonAndClothes() {
    int val = new Random(100).nextInt();
    PersonEntity personEntity = new PersonEntity();
    personEntity.setName("test" + val);
    personEntity.setDesct("show desc" + val);

    ClothesEntity clothesEntity = new ClothesEntity();
    clothesEntity.setName("cloth_" + val);
    clothesEntity.setType(0);
    clothesEntity.setPerson(personEntity);

    ClothesEntity clothesEntity1 = new ClothesEntity();
    clothesEntity1.setName("cloth1_" + val);
    clothesEntity1.setType(0);
    clothesEntity1.setPerson(personEntity);

    ClothesEntity clothesEntity2 = new ClothesEntity();
    clothesEntity2.setName("cloth1_" + val);
    clothesEntity2.setType(0);
    clothesEntity2.setPerson(personEntity);

    personEntity.getClothes().add(clothesEntity);
    personEntity.getClothes().add(clothesEntity1);
    personEntity.getClothes().add(clothesEntity2);

    personEntity = personService.add(personEntity);
    validateNotEmpty(personEntity);
  }

  /**
   * update Person，并在内部删除一个对象的同时增加一个对象，并修改另外一个对象
   */
  @Test
  public void operateClothesByPerson() {
    PersonEntity personEntity = personService.findOne(13);
    Set<ClothesEntity> clothesEntities = personEntity.getClothes();

    //删除Id=25的Clothes
    clothesEntities.removeIf(clothesEntity -> clothesEntity.getId() == 25);

    //修改ID=26的clothes,并更新关联的Person
    clothesEntities.stream().filter(clothesEntity -> clothesEntity.getId() == 26).forEach(clothesEntity1 -> {
      personService.findOne(10);
      clothesEntity1.setName("update Now");
      clothesEntity1.setPerson(personService.findOne(10));
    });

    //增加一个
    ClothesEntity clothesEntity2 = new ClothesEntity();
    clothesEntity2.setName("cloth1_add");
    clothesEntity2.setType(0);
    clothesEntity2.setPerson(personEntity); //如果没有级联Merge,则不会保存Person

    clothesEntities.add(clothesEntity2);

    personService.update(personEntity);
  }


  @Test
  public void updateClothesBySelf() {
    ClothesEntity clothesEntity2 = getClothesEntity();
    setPerson(clothesEntity2, personService.findOne(13));
  }

  private void setPerson(ClothesEntity clothesEntity2, PersonEntity one) {
    clothesEntity2.setPerson(one);
  }

  private ClothesEntity getClothesEntity() {
    ClothesEntity clothesEntity2 = new ClothesEntity();
    clothesEntity2.setId(19);
    clothesEntity2.setName("cloth12_" + 19);
    clothesEntity2.setType(1);
    clothesEntity2.setPerson(null);

    clothesEntity2 = clothesService.update(clothesEntity2);

//    personService.findOne()

//    clothesEntity2.setPerson();
    return clothesEntity2;
  }

  /**
   * 调用Rep中的自定义的删除方法,使用In的方式
   */
  @Test
  public void removeByRepMethod() {
    List<Integer> list = new ArrayList<>();
    list.add(3);
    list.add(4);
    clothesRep.deleteByIds(list);
  }

  /**
   * 只更新名字字段
   */
  @Test
  public void updateClothesName() {
    clothesRep.updateName("hello", 5);
  }

  /**
   * 代码捕捉Exception并回滚事务，当遇到Exception,回滚修改
   */
  @Test
  public void testRollBack_catchException() {
    try {
      clothesService.updateAndDeleteByService(15, 18);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testRollBack_RuntimeException() {
    updateAndDeleteByService_RuntimeException_RollBack();
  }

  @Test
  public void testNotRollBack() {
    try {
      clothesService.updateAndDeleteByService_notRollBack();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * 解@Transactional cglib与java动态代理最大区别是代理目标对象不用实现接口, 那么注解要是写到接口方法上，要是使用cglib代理，这是注解事物就失效了，为了保持兼容注解最好都写到实现类方法上。
   * 5. Spring团队建议在具体的类（或类的方法）上使用 @Transactional 注解，而不要使用在类所要实现的任何接口上 。在接口上使用 @Transactional 注解，只能当你设置了基于接口的代理时它才生效。
   * 因为注解是 不能继承 的，这就意味着如果正在使用基于类的代理时，那么事务的设置将不能被基于类的代理所识别，而且对象也将不会被事务代理所包装。
   */
  @Transactional(rollbackFor = {Throwable.class})
  public void updateAndDelete() {
    clothesRep.updateName("updateNow", 9);

    /*clothesRep.delete(10);
    int a = 2 / 0;
    clothesRep.delete(9);*/
  }


  /**
   * 代码未t捕捉Exception并回滚事务，当遇到Exception,不会回滚修改
   */
  public void updateAndDeleteByService_RuntimeException_RollBack() {
    try {
      clothesService.updateName("updateNow", 19);
      clothesService.updateName("updateNow2", 19);
      clothesService.deleteById(25);
      clothesService.updateNameThrowRuntimeException("updateNameThrowRuntimeException", 19);
    } catch (Exception e) {
//      e.printStackTrace();
    }
  }

  private void validateNotEmpty(PersonEntity personEntity) {
    Assert.isTrue(personEntity != null);
    Assert.isTrue(!personEntity.getClothes().isEmpty());

    Assert.isTrue(personEntity.getId() != 0);

    //Addresss实体的ID不为默认值
    boolean isInvalidId = personEntity.getClothes().stream().anyMatch(
            (clothesEntity) -> {
              return clothesEntity.getId() == 0;
            });
    Assert.isTrue(!isInvalidId);
  }
}
