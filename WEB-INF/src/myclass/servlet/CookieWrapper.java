package myclass.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myclass.util.Convert;

public class CookieWrapper {

    private Map<String, String> cookies = null;
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;
    private static final int MAXAGE = 60 * 60 * 24 * 365;

    private static String enc(String s) {
        return Convert.encodeURL(s);
    }

    private static String dec(String s) {
        return Convert.decodeURL(s);
    }

    public CookieWrapper(HttpServletRequest req, HttpServletResponse res) {
        setHttpServlets(req, res);
    }

    public CookieWrapper(HttpServletRequest req) {
        setRequest(req);
    }

    public CookieWrapper(HttpServletResponse res) {
        setResponse(res);
    }

    public CookieWrapper setHttpServlets(HttpServletRequest req, HttpServletResponse res) {
        setRequest(req).setResponse(res);
        return this;
    }

    public CookieWrapper setResponse(HttpServletResponse res) {
        response = res;
        return this;
    }

    public CookieWrapper setRequest(HttpServletRequest req) {
        request = req;
        return this;
    }

    /**
     * クッキーをresponseに追加する
     *
     * @param name
     * @param value
     * @return
     */
    public CookieWrapper addCookie(String name, String value) {
        addCookie(name, value, MAXAGE, "/");
        return this;
    }

    /**
     * クッキーをresponseに追加する<br>
     * 0秒を設定するとそのクッキーを削除
     *
     * @param name
     * @param value
     * @param maxage
     * @return
     */
    public CookieWrapper addCookie(String name, String value, int maxage) {
        addCookie(name, value, maxage, "/");
        return this;
    }

    /**
     * クッキーをresponseに追加する <br>
     * 0秒を設定するとそのクッキーを削除
     *
     * @param name
     * @param value
     * @param maxage
     * @param path
     * @return
     */
    public CookieWrapper addCookie(String name, String value, int maxage, String path) {
        addCookie(response, name, value, maxage, path);
        return this;
    }

    /**
     * クッキーを複数追加する<br>
     * 0秒を設定するとそのクッキーを削除
     *
     * @param kv
     * @return
     */
    public CookieWrapper addCookies(String... kv) {
        addCookies(response, kv, MAXAGE, "/");
        return this;
    }

    /**
     * クッキーを複数追加する<br>
     * 0秒を設定するとそのクッキーを削除
     *
     * @param kv
     * @param maxage
     * @return
     */
    public CookieWrapper addCookies(String[] kv, int maxage) {
        addCookies(response, kv, maxage, "/");
        return this;
    }

    /**
     * クッキーを複数追加する<br>
     * 0秒を設定するとそのクッキーを削除
     *
     * @param kv
     * @param maxage
     * @param path
     * @return
     */
    public CookieWrapper addCookies(String[] kv, int maxage, String path) {
        addCookies(response, kv, maxage, path);
        return this;
    }

    /**
     * クッキーを一つだけresponseに追加する
     *
     * @param res
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse res, String name, String value) {
        addCookie(res, name, value, MAXAGE, "/");
    }

    /**
     * クッキーを一つだけresponseに追加する
     *
     * @param res
     * @param name
     * @param value
     * @param maxage
     */
    public static void addCookie(HttpServletResponse res, String name, String value, int maxage) {
        addCookie(res, name, value, maxage, "/");
    }

    /**
     * クッキーを一つresponseに追加する
     *
     * @param res
     * @param name
     * @param value
     * @param maxage
     * @param path
     */
    public static void addCookie(HttpServletResponse res, String name, String value, int maxage,
            String path) {
        Cookie c = new Cookie(enc(name), enc(value));
        c.setPath(path);
        c.setMaxAge(maxage);
        res.addCookie(c);
    }

    /**
     * クッキーを複数追加
     *
     * @param res
     * @param kv
     */
    public static void addCookies(HttpServletResponse res, String... kv) {
        addCookies(res, kv, MAXAGE, "/");
    }

    /**
     * クッキーを複数追加
     *
     * @param res
     * @param kv
     * @param maxage
     */
    public static void addCookies(HttpServletResponse res, String[] kv, int maxage) {
        addCookies(res, kv, maxage, "/");
    }

    /**
     * クッキーを複数追加
     *
     * @param res
     * @param kv
     * @param maxage
     * @param path
     */
    public static void addCookies(HttpServletResponse res, String[] kv, int maxage, String path) {
        for (int i = 0, len = kv.length; i < len; ++i) {
            addCookie(res, kv[i], kv[++i], maxage, path);
        }
    }

    private CookieWrapper addCookie(Cookie c) {
        response.addCookie(c);
        return this;
    }

    /**
     * cookiesからブラウザにクッキーを追加する。
     *
     * @param maxage
     * @return
     */
    public CookieWrapper addCookies(int maxage) {
        for (String k : cookies.keySet()) {
            Cookie c = new Cookie(enc(k), enc(cookies.get(k)));
            c.setPath("/");
            // c.setSecure(true);
            c.setMaxAge(maxage);
            addCookie(c);
        }
        return this;
    }

    /**
     * cookiesからブラウザにクッキーを追加する。
     *
     * @return
     */
    public CookieWrapper addCookies() {
        addCookies(MAXAGE);
        return this;
    }

    /**
     * requestからクッキーをString,Stringで取得
     *
     * @return
     */
    public Map<String, String> getCookies() {
        if (cookies == null) {
            return cookies = getCookies(request);
        }
        return cookies;
    }

    /**
     * requestからクッキーをString,Stringで取得
     *
     * @param req
     * @return
     */
    public static Map<String, String> getCookies(HttpServletRequest req) {
        Cookie[] cs = req.getCookies();
        if (cs == null)
            return null;
        Map<String, String> cookies = new HashMap<String, String>();
        for (Cookie c : cs) {
            cookies.put(dec(c.getName()), dec(c.getValue()));
        }
        return cookies;
    }

}
