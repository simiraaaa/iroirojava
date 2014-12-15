package myclass.util;


public class Convert {

	private static final char c0 = '0';
	private static final String
	HEAD = "\\u";

	private static final int
	UNICODE_LENGTH = 4,
	UNICODE_RATE = 6;


	public static String toUnicode(final String s,boolean toUpper){
		final int len = s.length();
		StringBuilder sb = new StringBuilder(len * UNICODE_RATE);
		for(int i = 0; i < len; ++i){
			final String s16 = Integer.toHexString(Character.codePointAt(s, i));
			sb.append(HEAD);
			for(int j = UNICODE_LENGTH - s16.length(); j > 0; --j){
				sb.append(c0);
			}
			sb.append(s16);
		}
		return (toUpper) ? sb.toString().toUpperCase() : sb.toString();
	}

	public static String toUnicode(final String s){
		return toUnicode(s, false);
	}

}
