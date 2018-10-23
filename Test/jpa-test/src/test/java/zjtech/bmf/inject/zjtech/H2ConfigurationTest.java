package zjtech.bmf.inject.zjtech;

import zjtech.bmf.inject.zjtech.util.HB2ApplicationConfig;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = HB2ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class H2ConfigurationTest {
  ApplicationContext context;
/*

  @Autowired
  SuperAdminRep rep;

  @Autowired
  ISuperAdminCRUD crud;

  @Before
  public void setUp() {
    context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
  }

  @Test
  public void addOneUser() {
    SuperAdminEntity superAdminEntity = rep.findOneUser();
    if (superAdminEntity == null) {
      superAdminEntity = new SuperAdminEntity();
      superAdminEntity.setName("wzj");
      superAdminEntity.setPassword("11");
      superAdminEntity.setDescription("a test");
      superAdminEntity.setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
      rep.save(superAdminEntity);
    }
  }

  @Test
  public void getOneUser(){
    SuperAdminEntity superAdminEntity = rep.findOneUser();
    //System.out.println("name = " + superAdminEntity.getName());
  }
*/


}
