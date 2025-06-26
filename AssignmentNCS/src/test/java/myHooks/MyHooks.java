package myHooks;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import coreUtils.BrowserFactory;
import coreUtils.TakeScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MyHooks extends BrowserFactory{
	TakeScreenshot takescreenshot = new TakeScreenshot();
	@Before
	public void initialize() {
		DOMConfigurator.configure("src//test//resources//config//log4j.xml");
		PropertyConfigurator.configure("src//test//resources//config//log4j.properties");
		initialization();
	}
	
	@After
	public void tearDown(Scenario result) {
		takescreenshot.takeScreenShotOnException(result);
		closeBrowserSession();
		if ((result.getStatus().name()!="PASSED")) { 
			throw new RuntimeException (result.getName() + " got failed");
		}
	}

}
