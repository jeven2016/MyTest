package zjtech.bmf.inject.zjtech;

import zjtech.bmf.inject.zjtech.util.JpaConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = JpaConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class JpaConfigurationTestTxnTemplate {
  ApplicationContext context;
/*

  @Autowired
  SuperAdminRep rep;

  @Autowired
  ISuperAdminCRUD crud;

  @Autowired
  SuperAdminService srv;

  @Before
  public void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
  }

  @Test
  public void addOneUserByService() {
    SuperAdminEntity superAdminEntity = new SuperAdminEntity();
    superAdminEntity.setName("wzj2");
    superAdminEntity.setPassword("11");
    superAdminEntity.setDescription("a test");
    superAdminEntity.setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
    rep.saveByTxTemplate(superAdminEntity);
  }

*/

}
