package greeting;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.dsl.Inbox;

/**
 * Created by root on 16-4-7.
 */
public class DemoMain {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("greeting");

		// 创建一个到greeter Actor的管道
		ActorRef greeter = system.actorOf(Props.apply(Greeter.class), "greeter");
		greeter.tell(new Greeting("a test"),greeter);
		//system.shutdown();
	}
}
