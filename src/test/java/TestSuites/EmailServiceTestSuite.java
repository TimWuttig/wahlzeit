/*
 * EmailServiceTestSuite
 * 
 * 1.0
 * 
 * 26.11.2018
 * 
 * Copyright (c) by Tim Wuttig
 */

package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.mailing.EmailServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	EmailAddressTest.class,
	EmailServiceTest.class
})
public class EmailServiceTestSuite {

}
