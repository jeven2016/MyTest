package greeting;

import akka.actor.UntypedActor;

/**
 * Created by root on 16-4-7.
 */
public class LogPrinter extends UntypedActor {
	@Override
	public void onReceive(Object o) throws Exception {
		if (o instanceof LogMsg) {
			System.out.println("log :" + ((LogMsg) o).getMsg());
		}
	}
}
