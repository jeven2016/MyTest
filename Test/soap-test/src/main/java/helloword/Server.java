package helloword;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "www.ss.com")
public class Server {

    @WebMethod
    public String sayHello(String msg) {
        return "server got: " + msg;
    }
}
