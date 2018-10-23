package zjtech.bmf.inject.zjtech;

import zjtech.bmf.inject.zjtech.entity.AddressEntity;
import zjtech.bmf.inject.zjtech.entity.PersonEntity;
import zjtech.bmf.inject.zjtech.rep.PersonRep;
import zjtech.bmf.service.AddressService;
import zjtech.bmf.service.PersonService;
import zjtech.bmf.inject.zjtech.util.MysqlJpaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Random;

@ContextConfiguration(classes = MysqlJpaConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional(readOnly = true)//全局设置为只读事物
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class PersonTest {
  ApplicationContext context;

  @Autowired
  PersonService personService;

  @Autowired
  AddressService addressService;

  @Autowired
  PersonRep personRep;

  /*@Before
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
    //先删除所有对象
    deleteAllPersons();

    //添加对象
    addOnePerson();
  }
*/
  @Test
/*  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  @Rollback(false)*/
  /***数据不回滚*/
  public void addOnePerson() {
    int val = new Random(100).nextInt();
    PersonEntity personEntity = new PersonEntity();
    personEntity.setName("test" + val);
    personEntity.setDesct("show desc3");

    AddressEntity addressEntity = new AddressEntity();
    addressEntity.setName("xinjiekou" + val);
    addressEntity.setDest("desc from xinjiekou3");
//    addressEntity.setPerson(personEntity);

    personEntity.addAddress(addressEntity);

    /*addressEntity = addressService.update(addressEntity);
    personEntity = addressEntity.getPerson();

    Assert.isNull(addressEntity, "the addressEntity is null");
    Assert.isNull(personEntity, "the personEntity is null");*/
//    personEntity.setName("latest name");
//    personRep.save(personEntity);
    addressEntity= addressService.add(addressEntity);
//    personEntity=  personService.add(personEntity);

    System.out.println(addressEntity.getPerson().getId());
  }

  @Test
  public void testUpdate() {
//    personService.update();
  }

  @Test
  public void addAddress() {
//    rep.deleteAll();
//    PersonEntity personEntity = new PersonEntity();
//    personEntity.setName("test2");
//    personEntity.setDesct("show desc");
//
//    AddressEntity addressEntity = new AddressEntity();
//    addressEntity.setName("xinjiekou");
//    addressEntity.setDest("desc from xinjiekou");
//
//    addressEntity.setPerson(personEntity);
//    addressRep.save(addressEntity);
  }

  /*@Test
  @Transactional
  public void deleteAllPersons() {
    rep.deleteAll();
  }


  @Test
  public void getOneUser() {
    List<PersonEntity> list = rep.findAll();
    System.out.println("number of person: " + list.size());
  }*/


}
