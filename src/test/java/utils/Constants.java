package utils;


public class Constants {
	public static final String PORT_FOR_ANDROID = "4723";
	public static final String PORT_FOR_IOS = "4723";
	public static final String HOST = "127.0.0.1";
	public static final String configFile=System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties";
	public static final String androidAppPath=System.getProperty("user.dir")+"\\src\\test\\resources\\app\\SauceLabs.apk";
	public static final String REPORT_FOLDER="/test-output";
	public static final String OLD_ALLURE_RESULT="/allure-results/";
	public static final String OLD_ALLURE_REPORT="/allure-report/";
	public static final String TEST_RESULT_JSON="/target/json-report/cucumber.json";
}
