
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="myclass.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="test.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%!
private HttpServletRequest request=null;
private HttpServletResponse response=null;
private String getParam(String n){
	return this.request.getParameter(n);
}
private boolean isNull(String s){
	if(s==null)return true;
	return "".equals(s);
}

private String isNullSet(String s,String set){
	if(isNull(s)){return set;}
	return s;
}
%>

<%
this.request=request;
this.response=response;

//クッキーを扱うクラスのインスタンスを生成
MyCookie ck=new MyCookie(request,response);

//ブラウザから送信されたデータを取得
String id=getParam("id");
String name=getParam("name");
String comment=getParam("comment");

//ブラウザからデータが送信されていない時と""の時、代わりの値を入れる。
id=isNullSet(id,"0");
name=isNullSet(name,"name");
comment=isNullSet(comment,"comment");
//クライアントのクッキーを取得
Map<String,String> cookies= ck.getCookies();

//クッキーが空の場合の判定
boolean isCookiesNull = cookies==null;
int accessCount;
//クッキーが空のときの処理
if(isCookiesNull){
	//アクセスカウンターを1に設定する。
	//クッキーを一つだけセットしてクライアントに保存
	accessCount=1;
	ck.setCookie("accessCount", accessCount+"").addCookie();

	//クッキーを複数セットしてクライアントに保存
	//クッキー名,クッキーの値を交互にセット
	ck.setCookies(
			"id",id,
			"name",name,
			//commentはクッキー予約語なのでcome
			"come",comment
			).addCookies();
}else{
	//クッキーがあるとき
	accessCount=Integer.parseInt(cookies.get("accessCount"))+1;

	//コメントを増やす
	//クッキーの値に使えない文字がある(\r\n , 半角スペース)など
	comment=comment+comment;
	//idがクッキーと同じ値が入れられているとき+1
	if(cookies.get("id").equals(id)){
		id=(Integer.parseInt(id)+1)+"";
	}

	ck.setCookies(
			"accessCount",accessCount+"",
			"id",id,
			"name",name,
			//commentはクッキー予約語なのでcome
			"come",comment
			).addCookies();
}







%>


<title>Insert title here</title>
</head>
<body>
test2
<form action="cookietest.jsp" method="POST">
id(int):<input type="text" name="id" value="<%=id%>"/><br />
name(String):<input type="text" name="name" value="<%=name%>"/><br />
comment:<textarea rows="15" cols="55" name="comment" > <%= comment%> </textarea><br />
access:<%=accessCount %>
<input type="submit" />
</form>
</body>
</html>