package greeting;

import java.io.Serializable;

/**
 * Created by root on 16-4-7.
 */
public class Greeting implements Serializable {
	private String msg;

	public Greeting(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
