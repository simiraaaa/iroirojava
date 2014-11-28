<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="myclass.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%

IH31DB db=new IH31DB();

//insert文を実行
db.action("insert into T_CUSTOMER (F_CUSTOMERNAME1) values ('testname')");

db.setPrepareObjects("testname")//?にあらかじめセットする
.action("select * from T_CUSTOMER where ?=F_CUSTOMERNAME1",//第一引数 SQL文
"F_CUSTOMERID",//第二引数 Mapのキーになるフィールド名(下のループの 変数k)
"F_CUSTOMERNAME1","F_CUSTOMERNAME2");//第三引数以降はMAPのデータ

//SELECT文の結果が入ってるMAPを取得
//型は必ず<Object,HashMap<String,Object>>
//HashMap<Object,HashMap<String,Object>>でも可
Map<Object,HashMap<String,Object>> map = db.getMap();

if(map!=null){//mapはselect文の列が0件の時nullになる
	for(Object k : map.keySet()){
		System.out.println("Map:"+map.get(k).get("F_CUSTOMERNAME1"));
	}
}

//delete文を実行
db.action("delete T_CUSTOMER where F_CUSTOMERID=1");

//------ArrayListでやる場合

db.setPrepareObjects("testname")//?にあらかじめセットする
.action("select * from T_CUSTOMER where ?=F_CUSTOMERNAME1",//第一引数 SQL文
"[]",//第二引数 "[]"と書くとArrayList
"F_CUSTOMERNAME1","F_CUSTOMERNAME2");//第三引数以降はListのデータ

//SELECT文の結果が入ってるArrayListを取得
//型は必ず<HashMap<String,Object>>
ArrayList<HashMap<String,Object>> list = db.getList();

if(list!=null){//listはselect文の列が0件の時nullになる
	for(int i=0, l=list.size(); i < l; ){
		System.out.println("ArrayList:"+list.get(i++).get("F_CUSTOMERNAME1"));
	}
}

//delete文を実行
db.action("delete T_CUSTOMER where F_CUSTOMERID=1");

%>


<title>Insert title here</title>
</head>
<body>
test2
</body>
</html>