package myclass.wrap;

public class MyString {

    /**
     * ObjectをStringに変換します<br>
     * ただしnullの場合は文字列の"null"ではなくnullを返します
     *
     * @param o
     * @return
     */
    public static String toString(Object o) {
        return o == null ? null : o.toString();
    }
}
