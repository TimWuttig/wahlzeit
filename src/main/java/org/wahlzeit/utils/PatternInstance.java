package org.wahlzeit.utils;

import java.lang.annotation.Repeatable;


public @interface PatternInstance {
	String patternName();
	String[] participants();	
}
