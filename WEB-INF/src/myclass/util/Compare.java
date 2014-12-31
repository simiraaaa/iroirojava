package myclass.util;


/**
 * 比較が便利になる
 * @author yuki
 *
 */
public class Compare {

    /**
     * 二つの数値を比較して 小さい方を返す
     *
     * @param x
     * @param y
     * @return
     */
    public final static int small(int x, int y) {
        return x < y ? x : y;
    }

    /**
     * 二つの数値を比較して 大きい方を返す
     *
     * @param x
     * @param y
     * @return
     */
    public final static int big(int x, int y) {
        return x > y ? x : y;
    }

    /**
     * 配列の中で一番小さい値を返す
     *
     * @param x
     * @return
     */
    public final static int small(int[] x) {
        int s = Integer.MAX_VALUE;
        for (int i = 0, l = x.length; i < l; ++i) {
            s = small(s, x[i]);
        }
        return s;
    }

    /**
     * 配列の中で一番大きい値を返す
     *
     * @param x
     * @return
     */
    public final static int big(int[] x) {
        int s = Integer.MIN_VALUE;
        for (int i = 0, l = x.length; i < l; ++i) {
            s = big(s, x[i]);
        }
        return s;
    }

    /**
     * 文字列がnullから空の場合true
     *
     * @param s
     * @return
     */
    public final static boolean isEmpty(String s) {
        return (s == null) || s.isEmpty();
    }

}
