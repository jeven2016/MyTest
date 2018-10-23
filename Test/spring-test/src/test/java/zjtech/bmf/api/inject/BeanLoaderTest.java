package zjtech.bmf.api.inject;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zjtech.entity.Holder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring.xml")
public class BeanLoaderTest {

  @Autowired
  Holder holder;

  @Test
  public void testLoadeBeanBySpring() {
    Assert.assertNotNull(holder);
    Assert.assertEquals(holder.isManNull(), false);
    Assert.assertEquals(holder.isPersonNull(), false);

    //直接new,非通过spring 容器获得
    Holder holder = new Holder();
    Assert.assertEquals(holder.isManNull(), true);
    Assert.assertEquals(holder.isPersonNull(), true);
  }
}
