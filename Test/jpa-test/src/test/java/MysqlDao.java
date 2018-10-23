/**
 * Created by root on 14-11-21.
 */
public class MysqlDao {
/*
//  @Test
  public void save() {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("wzj");
    userEntity.setDescription("wzj test");
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setName("count1");
    countryEntity.setDescription("desc country1");
    userEntity.setCountryEntity(countryEntity);

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(userEntity);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  @Test
  public void find() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    UserEntity userEntity = entityManager.find(UserEntity.class, 1);
    System.out.println(userEntity.getName());

   *//* CountryEntity countryEntity = entityManager.find(CountryEntity.class, 1);
    System.out.println(countryEntity.getName());*//*

    entityManager.close();
    entityManagerFactory.close();
  }

  @Test
  public void set(){
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    CountryEntity countryEntity = entityManager.find(CountryEntity.class, 1);
    countryEntity.setName(countryEntity.getName()+"k1");
    countryEntity.setDescription(countryEntity.getDescription()+" desc");

    entityManager.getTransaction().begin();
    entityManager.merge(countryEntity);

    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }*/
}
