package util;

import java.util.regex.Pattern;

public class Person {

	private String cuid;

	private static final String usernameRegex = "[a-zA-Z]{4}[0-9]{4}";

	private static final Pattern usernamePattern = Pattern
			.compile(usernameRegex);

	public Person(final String cuid) {
		this.cuid = cuid;
	}

	public String getCuid() {
		return cuid;
	}

	public static boolean isCuidValid(final String cuid) {
		return cuid != null && usernamePattern.matcher(cuid).matches();
	}

};