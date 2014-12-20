package myclass.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class JSON<L extends Object & java.util.List<Object>,M extends AbstractMap<String, Object> & java.util.Map<String, Object>>{

	private static final char
	MINUS = '-',
	DOT = '.',
	COMMA = ',',
	SPACE = ' ',
	DB_QUOT = '"',
	BACK = '\\',
	TRUE = 't',
	FALSE = 'f',
	NULL = 'n',
	OBJECT_START = '{',
	OBJECT_END = '}',
	ARRAY_START = '[',
	ARRAY_END = ']',
	NUM_0 = '0',
	NUM_1 = '1',
	NUM_2 = '2',
	NUM_3 = '3',
    NUM_4 = '4',
	NUM_5 = '5',
	NUM_6 = '6',
	NUM_7 = '7',
	NUM_8 = '8',
	NUM_9 = '9';

	/**
	 * JSON文字列のエンコード
	 * @param s {"x":0,"text":"text"}のようなもの
	 * @return
	 */
	public static String toUnicode(final String s) {
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


	/**
	 * JSON文字列からJAVAオブジェクトを生成
	 * @param s JSON
	 * @param i 検索を開始したいindex
	 * @param len JSON文字列のlength
	 * @return
	 */
	private static Object parse(final String s, int[] i, final int len) {
		//if(i==null){i =new int[]{0};}

		for(; i[0] < len; ++i[0]){
			final char c = s.charAt(i[0]);

			if(isNumber(c) || c == MINUS){ return parseNumber(s, i, len);}

			switch (c) {

			case DB_QUOT : return parseString(s, i, len);
			case TRUE: i[0]+=4; return true;
			case FALSE: i[0]+=5; return false;
			case NULL: i[0]+=4; return null;
			case ARRAY_START: return parseArray(s, i, len);
			case OBJECT_START: return parseObject(s, i, len);

			default: continue;
			}
		}

		return s;
	}

	private static Object parseNumber(final String s, int[] i, final int len) {
		//if(i==null){i =new int[]{0};}
		final int start = i[0]++;
		boolean isFloat = false;
		for(; i[0] < len; ++i[0]){
			final char c = s.charAt(i[0]);
			if(!isFloat && c == DOT){ isFloat = true; continue; }
			if(c == SPACE || c == COMMA || c == ARRAY_END || c == OBJECT_END){
				if (isFloat) {
					return  Float.parseFloat(s.substring(start, i[0]));
				}else {
					return Integer.parseInt(s.substring(start, i[0]));
				}
				//return (isFloat) ? Float.parseFloat(s.substring(start, i[0])) : Integer.parseInt(s.substring(start, i[0])) ;
			}
		}
		if (isFloat) {
			return  Float.parseFloat(s.substring(start, i[0]));
		}else {
			return Integer.parseInt(s.substring(start, i[0]));
		}
		//return (isFloat ? Float.parseFloat(s.substring(start, i[0])) : Integer.parseInt(s.substring(start, i[0])) );
	}

	private static String parseString(final String s, int[] i, final int len) {
		//if(i==null){i =new int[]{0};}
		StringBuffer sb = new StringBuffer();
		++i[0];
		for(; i[0] < len; ++i[0]){
			final char c = s.charAt(i[0]);
			switch (c) {
			case BACK: sb.append(s.charAt(++i[0])); continue;
			case DB_QUOT: ++i[0]; return sb.toString();
			default: sb.append(c); continue;
			}
		}
		throw new RuntimeException("JSON:StringFormatException");
	}

	/**
	 * JSON parseする
	 * @param s JSON文字列
	 * @return parseされたJAVAオブジェクト
	 */
	public static Object parse(final String s){
		return parse(s, new int[]{0}, s.length());
	}

	private static HashMap<String, Object> parseObject(final String s, int[] i, final int len) {
		//if(i==null){i =new int[]{0};}
		++i[0];
		HashMap<String, Object> o = new HashMap<String, Object>();
		if (isEmptyObject(s, i, len)) { return o; }

		do{
			o.put(parseString(s, i, len),parse(s, addIndex(i), len));
		}while(hasNextKey(s, i, len));

		return o;
	}

	private static ArrayList<Object> parseArray(final String s, int[] i, final int len) {
		//if(i==null){i =new int[]{0};}
		++i[0];
		ArrayList<Object> array = new ArrayList<Object>();
		if (isEmptyArray(s, i, len)) {
			return array;
		}

		do{
			array.add(parse(s, i, len));
		}while(hasNextElement(s, i, len));

		return array;

	}


	private static boolean isEmptyObject(final String s, int[] i, final int len) {
		int copy = i[0];
		while(copy < len){
			switch (s.charAt(copy++)) {

			case DB_QUOT: return false;
			case OBJECT_END: i[0]=copy; return true;

			default:continue;
			}
		}

		throw new RuntimeException("JSON:ObjectFormatException");
	}

	private static boolean isEmptyArray(final String s, int[] i, final int len) {
		int copy = i[0];

		while(copy < len){
			final char c = s.charAt(copy++);
			if (isNumber(c) || c == MINUS || c == DB_QUOT || c == ARRAY_START || c == OBJECT_START || c == TRUE || c == FALSE || c == NULL ) {
				return false;
			}else if(c == ARRAY_END){
				i[0] = copy;
				return true;
			}
		}

		throw new RuntimeException("JSON:ArrayFormatException");
	}

	private static boolean hasNextKey(final String s, int[] i, final int len) {
		while(i[0] < len){
			switch (s.charAt(i[0]++)) {
			case COMMA: return true;
			case OBJECT_END: return false;
			default: continue;
			}
		}
		throw new RuntimeException("JSON:ObjectFormatException");
	}
	private static boolean hasNextElement(final String s, int[] i, final int len) {
		while(i[0] < len){
			switch (s.charAt(i[0]++)) {
			case COMMA: return true;
			case ARRAY_END: return false;
			default: continue;
			}
		}
		throw new RuntimeException("JSON:ArrayFormatException");
	}

	private static int[] addIndex(int[] i) {
		++i[0];
		return i;
	}
	private static int[] addIndex(int[] i, int add) {
		i[0] += add;
		return i;
	}

	private static boolean isNumber(final char c){
		return
				c == NUM_0 ||
				c == NUM_1 ||
				c == NUM_2 ||
				c == NUM_3 ||
				c == NUM_4 ||
				c == NUM_5 ||
				c == NUM_6 ||
				c == NUM_7 ||
				c == NUM_8 ||
				c == NUM_9 ;
	}



}
