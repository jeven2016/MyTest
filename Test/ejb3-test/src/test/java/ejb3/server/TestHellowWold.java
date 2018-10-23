/*
 * Copyright (c) ZJTech.com ,ALL RIGHTS RESERVED.
 */

package ejb3.server;

import junit.framework.Assert;
import org.junit.Test;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by root on 3/23/14.
 */
public class TestHellowWold {

  public static void main(String[] args) {
    TestHellowWold th = new TestHellowWold();
    th.smain();
  }

  public void smain() {
    try {
      InitialContext ctx = new InitialContext(GlassfishProp.getProp());
      NamingEnumeration<NameClassPair> enumeration = ctx.list("");
      while (enumeration.hasMoreElements()) {
        NameClassPair ne = enumeration.next();
        System.out.println(ne.getName());

      }

//      IHelloWorldRemote remote = (IHelloWorldRemote) ctx.lookup("HelloWorld");
      IHelloWorldRemote remote = (IHelloWorldRemote) ctx.lookup("HelloWorld#ejb3.server.IHelloWorldRemote");
      String msg = remote.say("client calls");
      System.out.println(msg);
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  static Properties getProp() {
    Properties props = new Properties();
    props.setProperty("java.naming.factory.initial",
            "com.sun.enterprise.naming.SerialInitContextFactory");
    props.setProperty("java.naming.factory.url.pkgs",
            "com.sun.enterprise.naming");
    props.setProperty("java.naming.factory.state",
            "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

// optional. Defaults to localhost. Only needed if web server is running
// on a different host than the appserver
    props.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");

// optional. Defaults to 3700. Only needed if target orb port is not 3700.
    props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
    return props;
  }

}
