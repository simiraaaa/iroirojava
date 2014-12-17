package myclass.util;

public class JSON{
	private static final char
	DB_QUOT = '"',
	BACK = '\\';

	public static String stringify(final String s) {
		byte status = 0;
		int start = 0;
		final int
		OUT = 0,
		IN = 1,
		ESC = 2;
		StringBuffer sb =new StringBuffer(s);
		int len = sb.length();
		for (int i = 0; i < len; ++i) {
			final char c = sb.charAt(i);
			switch (status) {
			case OUT:
				if(c == DB_QUOT){
					status = IN;
					start = i+1;
				}
				break;
			case IN:
				switch (c) {
				case DB_QUOT:
					final String before = sb.substring(start, i);
					final String after = Convert.toUnicode(before);
					sb.replace(start, i, after);
					status = OUT;
					int add =after.length()-before.length();
					i+=add;
					len +=add;
					break;

				case BACK:
					status = ESC;
					break;
				}
				break;
			case ESC:
				if(c != BACK){ status=IN; }
				break;
			}
		}
		return sb.toString();
	}

}
