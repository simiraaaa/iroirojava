package test;

import java.util.HashSet;

public class testSosuu {

	final static private int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,99999999, 999999999, Integer.MAX_VALUE };
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

	public static void main(String[] args) {

		long st = System.currentTimeMillis();
		System.out.println((System.currentTimeMillis() - st)+"ms");

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

	private static int getMaxL(final int x) {
		return SOSUU_LIST[x+1];
	}


	/**
	 * 素数かどうかを判定する
	 * @param x
	 * @return
	 */
	public static boolean isNotSosuu(final int x) {
		final int r=(int) Math.sqrt((double)x);

	    for(int n = 3; n <= r; n += 2 ){
	        if( x % n == 0 ) return true;
	    }
	    return false;
	}

	public static boolean isSosuu(final int x) {
		final int r= (int)Math.sqrt((double)x);

	    if( x == 2 ) return true;
	    if( x < 2 || x % 2 == 0 ) return false;
	    for(int n = 3; n <= r; n += 2 ){
	        if( x % n == 0 ) return false;
	    }
	    return true;
	}
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
	 * xまでの最大の素数を返す
	 * @param x
	 * @return
	 */
	private static int getSosuuMax(final int x) {
		if (x < 2) {
			return 0;
		}else if (x < 3) {
			return 1;
		}else if (x < 4) {
			return 2;
		}
		int[] sosuus = new int[30000000];
		int input_length=0;
		int n = 5;
		sosuus[0] = 3;
		for(;n <= x;n+=2){
			if(isNotSosuu(n,sosuus)) continue;
			sosuus[++input_length] = n;
		}

		return sosuus[input_length];
	}

	/**
	 * xまで素数が何個あるか返す(x含む)
	 * @param x
	 * @return
	 */
	private static int getSosuuCount(final int x) {
		if (x < 2) {
			return 0;
		}else if (x < 3) {
			return 1;
		}else if (x < 4) {
			return 2;
		}
		int[] sosuus = new int[30000000];
		int input_length=0;
		int index = 2;
		int n = 5;
		sosuus[0] = 3;
		for(;n <= x;n+=2){
			if(isNotSosuu(n,sosuus)) continue;
			sosuus[++input_length] = n;
			++index;

		}

		return index;
	}


	private static String getSosuu1S(final int x) {
		if (x == 1) {
			return "2";
		}else if (x == 2) {
			return "3";
		}
		int ban = 2;
		int i=5;
		int keta =0;
		for(;;i+=2){
			if(isNotSosuu(i)){continue;}
			ban+=(keta=getKeta2(i));
			if(ban>=x){
				return (Integer.toString(i));//.charAt(keta-1-(ban-x));
			}

		}
	}
	private static char getSosuu1(final int x) {
		if (x == 1) {
			return '2';
		}else if (x == 2) {
			return '3';
		}
		int ban = 2;
		int i=5;
		int keta =0;
		for(;;i+=2){
			if(isNotSosuu(i)){continue;}
			ban+=(keta=getKeta2(i));
			if(ban>=x){
				return (Integer.toString(i)).charAt(keta-1-(ban-x));
			}

		}
	}

	/**
	 * x番目の素数を返す
	 * @param x
	 * @return
	 */
	private static int getSosuu(final int x) {
		if (x == 1) {
			return 2;
		}else if (x == 2) {
			return 3;
		}
		int ban = 2;
		int i=5;
		for(;;i+=2){
			if(isNotSosuu(i)){continue;}
			if(++ban>=x){
				return i;
			}

		}
	}
	private static char getSosuu1_2(final int x) {
		if (x == 1) {
			return '2';
		}else if (x == 2) {
			return '3';
		}
		int[] sosuus = new int[x];
		int input_length = 0;
		int index = 2;
		int n = 5;
		sosuus[0] = 3;
		for(;;n+=2){
			if(isNotSosuu(n,sosuus)) continue;
			sosuus[++input_length] = n;
			if((index+=getKeta2(n)) >= x){
				return (Integer.toString(n)).charAt(getKeta2(n) - 1 - (index - x));
			}
		}
	}
	/**
	 * x番目の素数を返す
	 * @param x
	 * @return
	 */
	private static int getSosuu2(final int x) {
		if (x == 1) {
			return 2;
		}else if (x == 2) {
			return 3;
		}
		int[] sosuus = new int[x];
		int input_length = 0;
		int index = 2;
		int n = 5;
		sosuus[0] = 3;
		for(;;n+=2){
			if(isNotSosuu(n,sosuus)) continue;
			sosuus[++input_length] = n;
			if(++index >= x){
				return n;
			}
		}
	}


	/**
	 * x番目の素数を返す高速化
	 * @param x
	 * @return
	 */
	private static int getSosuu2x(final int x) {
		if (x == 1) {
			return 2;
		}else if (x == 2) {
			return 3;
		}else if (x == 3) {
			return 5;
		}
		int[] sosuus = new int[x];
		int input_length = 0;
		int index = 3;
		sosuus[0] = 5;
		int n = 7;
		boolean is3 = false;
		for(;;is3 = !is3,n+=(is3)?4:2){
			if(isNotSosuu(n,sosuus)) continue;
			sosuus[++input_length] = n;
			if(++index >= x){ return n; }
		}
	}
	private static String getSosuu1S_2(final int x) {
		if (x == 1) {
			return "2";
		}else if (x == 2) {
			return "3";
		}
		int[] sosuus = new int[x];
		int input_length = 0;
		int index = 2;
		int n = 5;
		sosuus[0] = 3;
		for(;;n+=2){
			if(isNotSosuu(n,sosuus)) continue;
			sosuus[++input_length] = n;
			if((index+=getKeta2(n)) >= x){
				return (Integer.toString(n));//.charAt(getKeta2(n) - 1 - (index - x));
			}
		}
	}


	private static char getSosuu1_3(final int x) {
		if (x == 1) {
			return '2';
		}else if (x == 2) {
			return '3';
		}
		int index = 2;
		int n = 5;
		HashSet<Integer> hs = new HashSet<Integer>();
		setHashSet(hs, 3, x);
		for(;;n+=2){
			if(hs.contains(n)){continue;}
			setHashSet(hs, n, x);
			if((index+=getKeta2(n)) >= x){
				return (Integer.toString(n)).charAt(getKeta2(n) - 1 - (index - x));
			}
		}
	}

	private static String getSosuu1S_3(final int x) {
		if (x == 1) {
			return "2";
		}else if (x == 2) {
			return "3";
		}
		int index = 2;
		int n = 5;
		HashSet<Integer> hs = new HashSet<Integer>();
		setHashSet(hs, 3, x);
		for(;;n+=2){
			if(hs.contains(n)){continue;}
			setHashSet(hs, n, x);
			if((index+=getKeta2(n)) >= x){
				return (Integer.toString(n));//.charAt(getKeta2(n) - 1 - (index - x));
			}
		}
	}

	/**
	 * xまでの最大の素数を返す最速
	 * @param x
	 * @return
	 */
	private static int getSosuu4Max(final int x) {
		if (x<2) {
			return -1;
		}else if (x < 3) {
			return 2;
		}else if (x < 5) {
			return 3;
		}else if(x < 7){
			return 5;
		}


		int n = 3;
		final int end = (int)Math.sqrt((double)x);
		boolean[] table = new boolean[x];
		for(;n<=end; n+=2){
			for(int add = n+n, i=n+add; i<=x;i+=add){
				table[i-1] = true;
			}
		}
		for(int i=x-1;;--i){
			int j = i+1;
			if(j%2==0){continue;}
			if(table[i])continue;
			return j;
		}
	}

	/**
	 * xまでの素数の配列を返す
	 * @param x
	 * @return
	 */
	private static int[] getSosuuList(final int x) {
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

	/**
	 * xまでの最大の素数を返す
	 * @param x
	 * @return
	 */
	private static int getSosuu3Max(final int x) {
		if (x == 1) {
			return 2;
		}else if (x == 2) {
			return 3;
		}
		int n = 5;
		final int end = (int)Math.sqrt((double)x);
		int last =0;
		HashSet<Integer> hs = new HashSet<Integer>();
		setHashSet(hs, 3, x);
		for(;n<=x;n+=2){
			if(hs.contains(n)){continue;}
			last=n;
			if(n<=end)setHashSet(hs, n, x);
		}
		return last;
	}

	private static void setHashSet(HashSet<Integer> hs, int n, final int end) {
		final int add = n + n;
		n+=add;
		for(;n <= end; n+=add){
			hs.add(n);
		}
	}

	private static boolean isSosuu(final int x, int[] sosuus) {
		int i = -1;
		int r = (int)Math.sqrt((double)x);
		int s ;
		while((s=sosuus[++i])<=r){
			if(x % s == 0) return false;
		}
		return true;
	}

	private static boolean isNotSosuu(final int x, int[] sosuus) {
		int i = -1;
		int r = (int)Math.sqrt((double)x);
		int s ;
		while((s=sosuus[++i])<=r){
			if(x % s == 0) return true;
		}
		return false;
	}


	//2倍速い
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

	private static int getKeta(final int x) {
		for (int i=0; ;++i)
            if (x <= sizeTable[i])
                return i+1;
	}

}
