package myclass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyServlet extends HttpServlet{
	protected String methodType = null;
	protected HttpServletRequest request=null;
	protected HttpServletResponse response=null;

	protected void doGet(HttpServletRequest req,HttpServletResponse res)  throws ServletException, IOException  {
		methodType = req.getMethod();
		setting(req,res);
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse res)  throws ServletException, IOException  {
		methodType = req.getMethod();
		setting(req,res);
	}
	protected void setting(HttpServletRequest req,HttpServletResponse res)  throws ServletException, IOException  {

		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		action(request=req, response=res);
	}

	abstract protected void action(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException;

	protected String[] getParams(String s) {
		return request.getParameterValues(s);
	}
	protected String getParam(String s){
		return request.getParameter(s);
	}

	protected int getIntParam(String s) {
		return Integer.parseInt(getParam(s));
	}

}
