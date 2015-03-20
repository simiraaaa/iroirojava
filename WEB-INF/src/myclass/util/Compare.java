package myclass.util;

/**
 * 比較が便利になる
 *
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
     * 偶数の場合true
     *
     * @param x
     * @return
     */
    public static boolean isEven(int x) {
        return x % 2 == 0;
    }

    /**
     * 奇数の場合true
     *
     * @param x
     * @return
     */
    public static boolean isOdd(int x) {
        return x % 2 == 1;
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
     * 文字列がnullか空の場合true
     *
     * @param s
     * @return
     */
    public final static boolean isEmpty(Object s) {
        return (s == null) || s.toString().isEmpty();
    }

    /**
     * 文字列がnullか空の場合true
     *
     * @param s
     * @return
     */
    public final static boolean isEmpty(CharSequence s) {
        return (s == null) || s.length() == 0;
    }

    /**
     * 文字列がnullか空の場合true
     *
     * @param s
     * @return
     */
    public final static boolean isNotEmpty(CharSequence s) {
        return (s != null) && s.length() != 0;
    }

    /**
     * 文字列がnullか空の場合true
     *
     * @param s
     * @return
     */
    public final static boolean isNotEmpty(Object s) {
        return (s != null) && s.toString().length() != 0;
    }

    /**
     * すべての文字列がnullか空の場合true
     *
     * @param ss
     * @return
     */
    public static boolean isAllEmpty(Object... ss) {
        for (int i = 0, len = ss.length; i < len; ++i) {
            if (isEmpty(ss[i]))
                continue;
            return false;
        }
        return true;
    }

    /**
     * すべての文字列のひとつでも空かnullの場合true
     *
     * @param ss
     * @return
     */
    public static boolean isAnyEmpty(Object... ss) {
        for (int i = 0, len = ss.length; i < len; ++i) {
            if (isEmpty(ss[i]))
                return true;
        }
        return false;
    }

    /**
     * 文字列の長さがmaxより大きい場合true<br>
     * nullの場合は必ずfalseになります
     *
     * @param s
     * @param max
     * @return
     */
    public static boolean isOverLength(int max, CharSequence s) {

        return s != null && s.length() > max;
    }

    /**
     * 文字列の長さがmaxより大きい場合true<br>
     * nullの場合は必ずfalseになります
     *
     * @param s
     * @param max
     * @return
     */
    public static boolean isOverLength(int max, Object s) {
        return s != null && s.toString().length() > max;
    }

    /**
     * すべての文字列の長さがmaxより大きい場合true<br>
     * 配列内にひとつでもnullがある場合falseを返します
     *
     * @param max
     * @param ss
     * @return
     */
    public static boolean isAllOverLength(int max, CharSequence... ss) {
        if (ss == null) {
            return false;
        }
        for (int i = 0, len = ss.length; i < len; ++i) {
            CharSequence charSequence = ss[i];
            if (charSequence != null && charSequence.length() > max) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 文字列の長さが一つでもmaxより大きい場合true<br>
     * 配列内にnullがある場合は無視されます
     *
     * @param max
     * @param ss
     * @return
     */
    public static boolean isAnyOverLength(int max, CharSequence... ss) {
        if (ss == null) {
            return false;
        }
        for (int i = 0, len = ss.length; i < len; ++i) {
            CharSequence cs = ss[i];
            if (cs == null) {
                continue;
            }
            if (cs.length() > max)
                return true;
        }
        return false;
    }

    /**
     * 文字列がその正規表現と一致するか
     *
     * @param str
     * @param regex
     * @return
     */
    public static boolean isMatches(String regex, String str) {
        return str != null && str.matches(regex);
    }

    /**
     * 文字列がその正規表現と一致するか
     *
     * @param str
     * @param regex
     * @return
     */
    public static boolean isMatches(String regex, Object str) {
        return str != null && str.toString().matches(regex);
    }

    /**
     * すべての文字列が正規表現とマッチするか<br>
     * String配列がnullもしくは要素に一つでもnullが含まれる場合falseを返します
     *
     *
     * @param regex
     * @param ss
     * @return
     */
    public static boolean isAllMatches(String regex, String... ss) {

        if (ss == null) {
            return false;
        }

        for (int i = 0, len = ss.length; i < len; ++i) {
            String s = ss[i];
            if (s != null && s.matches(regex)) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * いずれかの文字列が正規表現とマッチするか<br>
     * String配列がnullの場合false<br>
     * 要素にnullが含まれる場合その要素は無視されます
     *
     * @param regex
     * @param ss
     * @return
     */
    public static boolean isAnyMatches(String regex, String... ss) {
        if (ss == null) {
            return false;
        }
        for (int i = 0, len = ss.length; i < len; ++i) {
            String s = ss[i];
            if (s == null) {
                continue;
            }
            if (s.matches(regex))
                return true;
        }
        return false;
    }

    /**
     * sがいずれかの文字列と一致するか
     *
     * @param s
     *            comparedと比較
     * @param compared
     *            sと比較
     * @return どれとも一致しなかった場合false<br>
     *         何か一つでも一致した場合はtrue<br>
     *         comparedがnullの場合sがnullの場合はfalse
     */
    public static boolean isAnyEquals(String s, String... compared) {
        if (compared == null || s == null) {
            return false;
        }
        for (int i = 0, len = compared.length; i < len; ++i) {
            if (s.equals(compared[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 全ての文字列が同一か
     *
     * @param compared
     * @return
     */
    public static boolean isAllEquals(String... compared) {
        if (compared == null || compared.length < 2) {
            return false;
        }

        String key = compared[0];
        for (int i = 1, len = compared.length; i < len; ++i) {

            if (key != null && !key.equals((key = compared[i]))) {
                return false;
            }
        }
        return true;
    }

}
