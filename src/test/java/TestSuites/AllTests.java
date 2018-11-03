package TestSuites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
		"org.wahlzeit.handlers",
		"org.wahlzeit.model",
		"org.wahlzeit.model.persistence",
		"org.wahlzeit.services",
		"org.wahlzeit.services.mailing",
		"org.wahlzeit.testEnvironmentProvider",
		"org.wahlzeit.utils"})

public class AllTests {

}
