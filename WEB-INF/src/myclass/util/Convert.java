package myclass.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import myclass.wrap.MyString;

/**
 * 文字列の変換とかする <br>
 * myclass.Escapeのメソッドも移植
 *
 * @author yuki
 *
 */
public class Convert {

    private static final char C0 = '0';
    private static final String HEAD = "\\u";

    private static final int UNICODE_LENGTH = 4, UNICODE_RATE = 6;

    private String converted = null;

    public Convert() {
        // TODO 自動生成されたコンストラクター・スタブ
    }

    public Convert(Object obj) {
        set(obj);
    }

    /**
     * 変換後の文字列を返します
     *
     * @return String 変換後の文字列
     */
    @Override
    public String toString() {
        // TODO 自動生成されたメソッド・スタブ
        return converted;
    }

    /**
     * 変換後の文字列を返します。
     *
     * @return
     */
    public String get() {
        return converted;
    }

    /**
     * 変換する文字列をセットします。
     *
     * @param s
     * @return
     */
    public Convert set(Object obj) {

        converted = MyString.toString(obj);
        return this;
    }

    /**
     * 文字列をunicode文字列に変換
     *
     * @param obj
     *            unicodeにする文字列
     * @param toUpper
     *            trueで大文字<br>
     *            falseで小文字
     * @return
     */
    public static String toUnicode(Object obj, boolean noBase62, boolean toUpper) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        final char[] chars = obj.toString().toCharArray();
        final int len = chars.length;
        StringBuilder sb = noBase62 ? new StringBuilder() : new StringBuilder(len * UNICODE_RATE);
        for (int i = 0; i < len; ++i) {
            final char c = chars[i];
            if (noBase62 && Base62.isBase62(c)) {
                sb.append(c);
                continue;

            }
            final String s16 = Integer.toHexString((int) c);
            sb.append(HEAD);
            for (int j = UNICODE_LENGTH - s16.length(); j > 0; --j) {
                sb.append(C0);
            }
            sb.append(s16);
        }
        return (toUpper) ? sb.toString().toUpperCase() : sb.toString();
    }

    /**
     * 文字列をunicode文字列に変換
     *
     * @param toUpper
     *            trueで大文字<br>
     *            falseで小文字
     * @param noBase62
     *            trueで[0-9a-zA-Z]を無視する
     * @return
     */
    public Convert toUnicode(boolean noBase62, boolean toUpper) {
        converted = toUnicode(converted, noBase62, toUpper);
        return this;
    }

    /**
     * 文字列をunicode文字列に変換
     *
     * @param s
     *            unicodeにする文字列
     */
    public static String toUnicode(Object s) {
        return toUnicode(s, false, false);
    }

    /**
     * 文字列をUnicode文字列に変換する
     *
     * @param s
     * @param noBase62
     *            trueで[0-9a-zA-Z]を無視する
     * @return
     */
    public static String toUnicode(Object s, boolean noBase62) {
        return toUnicode(s, noBase62, false);
    }

    /**
     * 文字列をUnicode文字列に変換する
     *
     * @param noBase62
     *            trueで[0-9a-zA-Z]を無視する
     * @return
     */
    public Convert toUnicode(boolean noBase62) {
        converted = toUnicode(converted, noBase62);
        return this;
    }

    /**
     * 文字列をunicode文字列に変換
     *
     * @return
     */
    public Convert toUnicode() {
        converted = toUnicode(converted, false, false);
        return this;
    }

    /**
     * UTF-8でURLエンコードをする
     *
     * @param s
     *            エスケープ対象の文字列
     * @return
     */
    public static String encodeURL(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        try {
            return URLEncoder.encode(obj.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            System.out.println("エンコードに失敗しました");
            return obj.toString();
        }
    }

    /**
     * UTF-8でURLエンコード
     *
     * @return
     */
    public Convert encodeURL() {
        converted = encodeURL(converted);
        return this;
    }

    /**
     * URLデコードをUTF-8でする
     *
     * @param obj
     * @return
     */
    public static String decodeURL(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        try {
            return URLDecoder.decode(obj.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            System.out.println("デコードに失敗しました");
            return obj.toString();
        }
    }

    /**
     * utf8をデコード
     *
     * @return
     */
    public Convert decodeURL() {
        converted = decodeURL(converted);
        return this;
    }

    /**
     * HTMLエスケープを実行します。
     *
     * @param str
     *            HTMLエスケープする文字列
     * @return HTMLエスケープされた文字列
     */
    public static String escapeHtml(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }

        return obj.toString().//
        replaceAll("&", "&amp;").//
        replaceAll("<", "&lt;").//
        replaceAll(">", "&gt;").//
        replaceAll("\"", "&quot;").//
        replaceAll(",", "&#44;");
    }

    /**
     * HTMLエスケープ
     *
     * @return
     */
    public Convert escapeHtml() {
        converted = escapeHtml(converted);
        return this;
    }

    /**
     * HTMLのunescape
     *
     * @param str
     * @return
     */
    public static String unespaceHtml(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        return obj.toString().//
        replaceAll("&", "&amp;").//
        replaceAll("<", "&lt;").//
        replaceAll(">", "&gt;").//
        replaceAll("\"", "&quot;").//
        replaceAll(",", "&#44;");
    }

    /**
     * HTMLアンエスケープ
     *
     * @return
     */
    public Convert unespaceHtml() {
        converted = unespaceHtml(converted);
        return this;
    }

    /**
     * SQLエスケープを実行します。
     *
     * @param str
     *            SQLエスケープする文字列
     * @return SQLエスケープされた文字列
     */
    public static String escapeSql(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        return //
        obj.toString()//
        .replaceAll("'", "''")//
        .replaceAll("\\", "\\\\");
    }

    /**
     * SQLエスケープ
     *
     * @return
     */
    public Convert escapeSql() {
        converted = escapeSql(converted);
        return this;
    }

    public static String escapeWildcard(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        return obj.toString().replaceAll("%", "\\\\%").//
        replaceAll("_", "\\\\_");
    }

    public static String escapeWildcard(Object obj, Object e) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }
        return obj.toString().replaceAll("%", e + "%").replaceAll("_", e + "_");
    }

    /**
     * 改行の置換を実行します。
     *
     * @param str
     *            置き換える文字列
     * @return 改行された文字列
     */
    public static String escapeTextarea(Object obj) {
        if (Compare.isEmpty(obj)) {
            return MyString.toString(obj);
        }

        return //
        obj.toString()//
        .replaceAll("\r\n", "<br />")//
        .replaceAll("\n", "<br />");
    }

    /**
     * 改行文字をbrタグに置換
     *
     * @return
     */
    public Convert escapeTextarea() {
        converted = escapeTextarea(converted);
        return this;
    }

    /**
     * 置換<br>
     * 第一引数は置換対象の文字列<br>
     * 第二匹数以降は2個ずつfrom toの順番になるようにしてください
     *
     * @param s
     * @param fromto
     * @return
     */
    public static String replaceAll(String s, String... fromto) {
        if (Compare.isEmpty(s)) {
            return s;
        }
        int len = fromto.length;
        if (Compare.isOdd(len)) {
            --len;
        }
        for (int i = 0; i < len; ++i) {
            s = s.replaceAll(fromto[i], fromto[++i]);
        }
        return s;
    }

    /**
     * 置換<br>
     * 第一引数は置換対象の文字列<br>
     * 第二匹数以降は2個ずつfrom toの順番になるようにしてください
     *
     * @param s
     *            置換対象
     * @param fromto
     *            2個ずつfrom toの順番になるようにしてください
     * @return
     */
    public static String replaceAll(Object s, String... fromto) {
        return replaceAll(MyString.toString(s), fromto);
    }

    /**
     * 置換<br>
     *
     * @param fromto
     *            2個ずつfrom toの順番になるようにしてください
     * @return
     */
    public Convert replaceAll(String... fromto) {
        converted = replaceAll(converted, fromto);
        return this;
    }

}
