
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

<%
StringBuffer stringBuffer = new StringBuffer();
for(int i = 0;i<1000000;i++){if(Sosuu.isSosuu(i)){stringBuffer.append(i+",<br />");};}
%>
<title>Insert title here</title>
</head>
<body>
<%=stringBuffer.toString() %>
</body>
</html>