package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.model.BikeManagerTest;
import org.wahlzeit.model.BikeTest;
import org.wahlzeit.model.BikeTypeTest;

@RunWith(Suite.class)
	@SuiteClasses({
		BikeTest.class,
		BikeTypeTest.class
	})
public class BikeTests {
	
}
