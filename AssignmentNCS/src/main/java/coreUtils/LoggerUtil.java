package coreUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil {
	static Logger log = Logger.getLogger(Thread.currentThread().getStackTrace().getClass());

	public LoggerUtil() {
		PropertyConfigurator.configure("src//test//resources//log4j.properties");
		System.out.println("Updated logger configuration");
	}

	public static void info(String message) {
		log.info(message);
	}

	public static void warn(String message) {
		log.warn(message);
	}

	public static void error(String message) {
		log.error(message);
	}

	public static void fatal(String message) {
		log.fatal(message);
	}

	public static void debug(String message) {
		log.debug(message);
	}
}
