package myclass.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLoader extends HttpServlet {

    private static String PATH = "/serv/";
    private static int PATH_LENGTH = PATH.length();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        doGet(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO 自動生成されたメソッド・スタブ
        ServletWrapper servletWrapper = null;
        /**
         * いずれの例外も404へ
         */
        try {
            servletWrapper = load(req);
        } catch (InstantiationException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        if (servletWrapper == null) {
            return;
        }
        servletWrapper.action(req, res);
    }

    public static ServletWrapper load(HttpServletRequest req) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        String classPath = req.getServletPath().substring(PATH_LENGTH);
        return ((ServletWrapper) Class.forName(classPath).newInstance());
    }
}
