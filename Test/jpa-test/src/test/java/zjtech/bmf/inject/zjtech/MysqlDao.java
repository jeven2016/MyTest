package zjtech.bmf.inject.zjtech;

/**
 * Created by root on 14-11-21.
 */
public class MysqlDao {
/*
  @Test
  public void saveToH2Database() {
    SuperAdminEntity SuperAdminEntity = new SuperAdminEntity();
    SuperAdminEntity.setName("wzj5");
    SuperAdminEntity.setDescription("wzj test");
    SuperAdminEntity.setDeprecated(false);
    SuperAdminEntity.setPassword("111");
    SuperAdminEntity.setLastLogin(new Timestamp(Calendar.getInstance().getTime().getTime()));

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("h2Unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(SuperAdminEntity);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  @Test
  public void saveToMysql() {
    SuperAdminEntity SuperAdminEntity = new SuperAdminEntity();
    SuperAdminEntity.setName("wzj5");
    SuperAdminEntity.setDescription("wzj test");
    SuperAdminEntity.setDeprecated(false);
    SuperAdminEntity.setPassword("111");
    SuperAdminEntity.setLastLogin(new Timestamp(Calendar.getInstance().getTime().getTime()));

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(SuperAdminEntity);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  //  @Test
  public void find() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dmfPerUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    SuperAdminEntity SuperAdminEntity = entityManager.find(SuperAdminEntity.class, 1);
    System.out.println(SuperAdminEntity.getName());

   *//* CountryEntity countryEntity = entityManager.find(CountryEntity.class, 1);
    System.out.println(countryEntity.getName());*//*

    entityManager.close();
    entityManagerFactory.close();
  }


  public void set() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    entityManager.getTransaction().begin();

    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }*/
}
