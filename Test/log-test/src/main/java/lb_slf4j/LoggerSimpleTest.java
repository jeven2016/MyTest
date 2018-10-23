package lb_slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSimpleTest {
	static Logger logger = LoggerFactory.getLogger(LoggerSimpleTest.class);

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			logger.warn("hello {}", i);
		}
	}
}
