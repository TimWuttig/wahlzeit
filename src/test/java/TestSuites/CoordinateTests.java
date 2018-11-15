package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.model.CartesianCoordinateTest;
import org.wahlzeit.model.SphericCoordinateTest;

@RunWith(Suite.class)
@SuiteClasses({
	CartesianCoordinateTest.class,
	SphericCoordinateTest.class
})
public class CoordinateTests {

}
