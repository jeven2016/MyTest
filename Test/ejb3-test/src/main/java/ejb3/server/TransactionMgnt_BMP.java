package ejb3.server;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * 使用编程式管理事务
 */
//@Remote(IHelloWorldRemote.class)
//@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)  //use CMT
public class TransactionMgnt_BMP{ //implements IHelloWorldRemote {
   /**
  //1. 注入方式获取transaction
  @Resource
  private UserTransaction transaction;

  @Resource
  private SessionContext sessionContext;

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public String say(String msg) {

    try {
      transaction.begin();
      if (msg.equals("callback")) {
        transaction.rollback();
        return "you callback now.";
      }
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "submit default";
  }


  //2. JNDI查找
  public void tranByJNDI() {
    Properties properties = new Properties();

    try {
      InitialContext initialContext = new InitialContext(properties);
      UserTransaction userTransaction = (UserTransaction) initialContext.lookup("java:comp/env/UserTransaction");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //3. EJBContext 获取
  public void tranByEJBContext() {
    sessionContext.getUserTransaction();

    //对于BMP 不能调用setRollbackonly
  }
  **/
}
