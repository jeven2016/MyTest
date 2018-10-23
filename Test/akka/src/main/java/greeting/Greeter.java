package greeting;

import akka.actor.UntypedActor;

/**
 * Created by root on 16-4-7.
 */
public class Greeter extends UntypedActor {
	@Override
	public void onReceive(Object o) throws Exception {
		if (o instanceof Greeting) {
			System.out.println("Greeter got :" + ((Greeting) o).getMsg());

			//发一个新的消息告知记录日志
			getSender().tell(new LogMsg().setMsg(((Greeting) o).getMsg()));
		}
	}
}
