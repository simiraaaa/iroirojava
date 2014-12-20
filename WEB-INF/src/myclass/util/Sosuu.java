package myclass.util;

public class Sosuu {

	final static private int
	ONE = 9,
	TWO = 99,
	THREE = 999,
	FOUR = 9999,
	FIVE = 99999,
	SIX = 999999,
	SEVEN = 9999999,
	EIGHT = 99999999,
	NINE = 999999999;

	private static int[] SOSUU_LIST = null,

	MAX_TABLE = {
		1993,
		20231,
		206411,
		2097671,
		21242369,
		53877419,
		109560533,
		161565329
	},

	KETA_TABLE = {
		1000,
		10000,
		100000,
		1000000,
		10000000,
		25000000,
		50000000,
		75000000
	},

	INDEX_TABLE = {
		100,
		1000,
		10000,
		100000,
		1000000,
		2500000,
		5000000,
		7500000
	},

	MAX_TABLE2 = {
		557,
		7933,
		104759,
		1299743,
		15485917,
		41161781,
		86028221,
		132276719
	};

	/**
	 * x個目の素数を返す
	 * @param x
	 * @return
	 */
	public static int get(final int x) {
		checkIndexList(x);
		return SOSUU_LIST[x-1];
	}


	/**
	 * 素数のx桁目の文字を返す
	 * <br>9を指定した場合3が返る
	 * <br>2(1),3(2),5(3),7(4),9(5),11(6,7),13(8,9)
	 * <br>数値は素数(桁数)
	 *
	 * @param x
	 * @return
	 */
	public static char getChar(final int x) {
		if(x < 1){
			throw new RuntimeException("引数が1未満");
		}
		checkKetaList(x);
		for(int i =0,k = 0;; ++i){
			if((k += getKeta2(SOSUU_LIST[i])) >= x ){
				i = SOSUU_LIST[i];
				return Integer.toString(i).charAt(getKeta2(i) - 1 -(k-x));
			}
		}
	}

	private static void checkKetaList(final int x) {
		for(int i = 0, len = MAX_TABLE.length;i < len ;++i){
			if(KETA_TABLE[i] > x){createList(MAX_TABLE[i]);return;}
		}
		createList(170000000);
		return;
	}

	private static void checkIndexList(final int x) {
		for(int i = 0, len = MAX_TABLE.length;i < len ;++i){
			if(INDEX_TABLE[i] > x){createList(MAX_TABLE2[i]);return;}
		}
		createList(170000000);
		return;
	}

	private static void createList(final int x) {
		if (SOSUU_LIST == null) {
			SOSUU_LIST = getSosuuList(x);
		}else if (SOSUU_LIST[SOSUU_LIST.length-1] < x) {
			SOSUU_LIST = getSosuuList(x);
		}
	}


	/**
	 * 素数かどうかを判定する
	 * @param x
	 * @return
	 */
	public static boolean isSosuu(final int x) {
		final int r= (int)Math.sqrt((double)x);

	    if( x == 2 ) return true;
	    if( x < 2 || x % 2 == 0 ) return false;
	    for(int n = 3; n <= r; n += 2 ){
	        if( x % n == 0 ) return false;
	    }
	    return true;
	}

	/**
	 * 素数かどうかを判定する
	 * @param x
	 * @return
	 */
	public static boolean isSosuu(final long x) {
		final long r= (long)Math.sqrt((double)x);

	    if( x == 2 ) return true;
	    if( x < 2 || x % 2 == 0 ) return false;
	    for(long n = 3; n <= r; n += 2 ){
	        if( x % n == 0 ) return false;
	    }
	    return true;
	}

	/**
	 * x以下の最大の素数を返す
	 * @param x
	 * @return
	 */
	public static int getMax(int x) {
		if(x<2){ throw new RuntimeException("引数が1未満") ;}
		else if (x < 3) {
			return 2;
		}
		for(;;--x){
			if (isSosuu(x)) {
				return x;
			}
		}
	}
	/**
	 * x以下の最大の素数を返す
	 * @param x
	 * @return
	 */
	public static long getMax(long x) {
		if(x<2){ throw new RuntimeException("引数が1未満") ;}
		else if (x < 3) {
			return 2;
		}
		for(;;--x){
			if (isSosuu(x)) {
				return x;
			}
		}
	}

	/**
	 * xまでの素数の配列を返す
	 * @param x
	 * @return
	 */
	public static int[] getSosuuList(final int x) {
		if (x<2) {
			throw new RuntimeException("引数が1未満");
		}else if (x < 3) {
			return new int[]{2};
		}else if (x < 5) {
			return new int[]{2,3};
		}else if(x < 7){
			return new int[]{2,3,5};
		}


		int n = 3;
		final int end = (int)Math.sqrt((double)x);
		boolean[] table = new boolean[x];
		table[0]=true;
		for(;n<=end; n+=2){
			for(int add = n+n, i=n+add; i<=x;i+=add){
				table[i-1] = true;
			}
		}
		int c = 1;
		for(n = 3; n <= x; n+=2){
			if(table[n-1]){continue;}
			++c;
		}
		int[] sosuus = new int [c];
		sosuus[0]=2;
		for(n = 3,c = 0; n<= x;n+=2){
			if(table[n-1]){continue;}
			sosuus[++c] = n;
		}
		return sosuus;
	}


	private static int getKeta2(final int x) {
		if (x <= ONE) {
			return 1;
		}else if (x <= TWO) {
			return 2;
		}else if (x <= THREE) {
			return 3;
		}else if (x <= FOUR) {
			return 4;
		}else if (x <= FIVE) {
			return 5;
		}else if (x <= SIX) {
			return 6;
		}else if (x <= SEVEN) {
			return 7;
		}else if (x <= EIGHT) {
			return 8;
		}else if (x <= NINE) {
			return 9;
		}else{
			return 10;
		}

	}


}
