/*
 * Copyright (c) ZJTech.com ,ALL RIGHTS RESERVED.
 */

package ejb3.server;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote({IHelloWorldRemote.class})
@Local(IHelloWorldLocal.class)
@Stateless(name="HelloWorld",mappedName = "HelloWorld")
public class HelloWorldEJB implements IHelloWorldRemote {
  @Override
  public String say(String msg) {
    return "you just say "+msg+". This feedback is from EJB server";
  }
}
