package test.single_1_N;

import zjtech.bmf.inject.zjtech.entity.AddressEntity;
import zjtech.bmf.inject.zjtech.rep.AddressRep;
import zjtech.bmf.service.AddressService;
import zjtech.bmf.inject.zjtech.entity.PersonEntity;
import zjtech.bmf.inject.zjtech.rep.PersonRep;
import zjtech.bmf.service.PersonService;
import zjtech.bmf.inject.zjtech.util.MysqlJpaConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@ContextConfiguration(classes = MysqlJpaConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class PersonTest_Single {
  @PersistenceUnit
  public EntityManagerFactory entityManagerFactory;
  ApplicationContext context;
  @Autowired
  PersonService personService;

  @Autowired
  AddressService addressService;

  @Autowired
  PersonRep personRep;

  @Autowired
  AddressRep addressRep;

/*  @Before
//  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfiguration.class);

    //先删除所有对象
    deleteAllPersons();

    //添加对象
    addOnePerson();
  }*/

  /**
   * 测试单向1-N映射的save操作
   * 当添加一个Person(1)后会级联保存Address(N)
   */
  @Test
//  @Transactional
  public void addOnePerson() {
    int val = new Random(100).nextInt();
    PersonEntity personEntity = new PersonEntity();
    personEntity.setName("test" + val);
    personEntity.setDesct("show desc" + val);

    AddressEntity addressEntity = new AddressEntity();
    addressEntity.setName("xinjiekou" + val);
    addressEntity.setDest("desc from xinjiekou" + val);

    //关联一个地址
    personEntity.addAddress(addressEntity);

    //保存
    personEntity = personService.add(personEntity);

    //获取获取游离状态的对象
    validateAddResult(personEntity);
  }

  @Test
  public void updatePerson() {
    int val = new Random(100).nextInt();
    int id = 12;
    PersonEntity personEntity = personService.findOne(id);

    AddressEntity addressEntity_ = new AddressEntity();
    addressEntity_.setName("from update~7");
    addressEntity_.setDest("desc~ ");

    HashSet<AddressEntity> addrSet = new HashSet<>();
    addrSet.add(addressEntity_); //新创建的集合类无法保存
    personEntity.setAddress(addrSet);

   /* personEntity.getAddress().clear();
    personEntity.getAddress().add(addressEntity_single);*/

    personEntity.setName("update..~7");
    personEntity.setDesct("update desc~");

    personEntity = personService.update(personEntity);
    validateAddResult(personEntity);
  }

  @Test
//  @Transactional
  public void updatePerson_ByJPAEntity() {
    int val = new Random(100).nextInt();
    int id = 12;
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    PersonEntity personEntity = entityManager.find(PersonEntity
            .class, id);

    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();


    AddressEntity addressEntity_ = new AddressEntity();
    addressEntity_.setName("from update~3");
    addressEntity_.setDest("desc~ ");
/*
    HashSet<AddressEntity_Single> addrSet = new HashSet<>();
    addrSet.add(addressEntity_single); //新创建的集合类无法保存
    personEntity.setAddress(addrSet);*/

    personEntity.getAddress().clear();
    personEntity.getAddress().add(addressEntity_);

    personEntity.setName("update..~3");
    personEntity.setDesct("update desc~");

    entityManager.merge(personEntity);
    transaction.commit();
    validateAddResult(personEntity);
  }

  @Test
  public void deleteSomeAddresses() {
    int id = 12;
    PersonEntity personEntity = personService.findOne(id);
    Collection<AddressEntity> addressCol = personEntity.getAddress();

    addressRep.delete(addressCol);
  }


  @Test
  public void deletePerson() {
    personService.deleteById(11);
  }

  private void validateAddResult(PersonEntity personEntity) {
    Assert.isTrue(personEntity != null);
    Assert.isTrue(!personEntity.getAddress().isEmpty());

    Assert.isTrue(personEntity.getId() != 0);

    //Addresss实体的ID不为默认值
    boolean isInvalidId = personEntity.getAddress().stream().anyMatch(
            (addressEntity1) -> {
              return addressEntity1.getId() == 0;
            });
    Assert.isTrue(!isInvalidId);
  }

  @Test
  public void addOnePerson_ByJPAEntity() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    int val = new Random(100).nextInt();
    PersonEntity personEntity = new PersonEntity();
    personEntity.setName("test" + val);
    personEntity.setDesct("show desc" + val);

    AddressEntity addressEntity = new AddressEntity();
    addressEntity.setName("xinjiekou" + val);
    addressEntity.setDest("desc from xinjiekou" + val);

    //关联一个地址
    personEntity.addAddress(addressEntity);

    //保存
//    personEntity = personService.add(personEntity);
    entityManager.persist(personEntity);
    transaction.commit();

//    entityManager.refresh(personEntity);

    //获取获取游离状态的对象
    validateAddResult(personEntity);
  }


}
