
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.ArrayList"%>
<%@page import="test.ClassNestTest.Nest"%>
<%@page import="java.sql.Array"%>
<%@page import="java.util.HashMap"%>
<%@page import="myclass.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="test.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%!
enum enumtest{
test1(),
test2(),
test3();
private enumtest(){}


public enumtest getThis(){System.out.println(this); return this;}
public void testMethod(){}
}
enum enumtest2{
	;
	public enumtest2 L(Object o){System.out.println(o); return this;}
}
private void L(Object o){System.out.println(o);}
%>
<%

MyColor mc=new MyColor();
float[] is=new float[5];
L(is[4]);
enumtest[] e= enumtest.values();
MyMethod mm=new MyMethod();
mm.field=123;
Method mth= mm.getMethod(mm, "nanimosinai",int.class);
{long st=System.currentTimeMillis();
for(int i=0;i<100;i++){

}
L(System.currentTimeMillis()-st);

}

//System.out.println(mth.toString());
//ClassCreateTest cct=new ClassCreateTest();
//long st=System.currentTimeMillis();
//cct.main();
//System.out.println(System.currentTimeMillis()-st);

%>


<title>Insert title here</title>
</head>
<body>
test2
</body>
</html>