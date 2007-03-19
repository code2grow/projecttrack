package org.ptrack.domain.hibernate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetUtil {

	public static Set make(String string, String string2, String string3, String string4, String string5, String string6) {
		Set result = new HashSet();
		result.add(string);
		result.add(string2);
		result.add(string3);
		result.add(string4);
		result.add(string5);
		result.add(string6);
		return result;
	}

	public static Set make(String string, String string2, String string3, String string4, String string5) {
		Set result = new HashSet();
		result.add(string);
		result.add(string2);
		result.add(string3);
		result.add(string4);
		result.add(string5);
		return result;
	}
	public static Set make(String string, String string2, String string3, String string4) {
		Set result = new HashSet();
		result.add(string);
		result.add(string2);
		result.add(string3);
		result.add(string4);
		return result;
	}

	public static Set make(String string, String string2, String string3) {
		Set result = new HashSet();
		result.add(string);
		result.add(string2);
		result.add(string3);
		return result;
	}

	public static Set make(String string) {
		return Collections.singleton(string);
	}

	

	

}
