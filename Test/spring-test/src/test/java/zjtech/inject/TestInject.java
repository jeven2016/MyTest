package zjtech.inject;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = Config.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInject {
    @Autowired
    private ComponetBean bean;

    @Autowired
    @Qualifier("B")
    private B<Integer,String> b;

    @Test
    public void testRetrievingB() {
        Assert.assertNotNull(b);
        Assert.assertNotNull(bean);
    }
}

@Configuration
@ComponentScan("zjtech.inject")
class Config implements ApplicationContextAware {
    ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
