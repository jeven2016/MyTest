package ejb3.server;

import javax.annotation.Resource;
import javax.ejb.*;

@Remote(IHelloWorldRemote.class)
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)  //use CMT

/**
 * 使用容器管理事务，由容器决定事务提交和回滚
 */
public class TransactionMgnt implements IHelloWorldRemote{

  @Resource
  private SessionContext context;


  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public String say(String msg){

    if(msg.equals("callback")){
      context.setRollbackOnly(); //设置回滚标志，在事务结束后进行回滚
      return "you callback now.";
    }
    else{
    }
    return "submit default";
  }

}
