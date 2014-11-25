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
//Test t=new Test();
//t.loop();
//ClassCreateTest cct=new ClassCreateTest();
//cct.main();
String[] a=InterfaceTest.kenStrings;
System.out.println(a);
ClassNestTest CNT=new ClassNestTest();
CNT.callTest();
CNT.getNest().getNest2().Test();
%><%!private class Class1 {String a=""; class Class12{void are(){L("are");}}}%><%
Class1 class1 =new Class1();
class1.a="111a";

%><%!private Object createObject(Object o){return o;}%><%
Object obj2=createObject(
		new Object(){
			String a="asjdl";
			}
		);
Object obj3=new Object(){};
new Class1().new Class12().are();
{
	int i=0;
	L(i);
};

String k="key";
new Object(){
	String[] keys={"a","b"};
	Object[] values={"v1","v2"};


};
new ClassNestTest().new Nest().Test();
{L(a);};
{int i=100;
	{int j=99;}
	int j=299;
	L(j+i);
}
%><%!private void L(Object o){System.out.println(o);}%><%
this.L(this);L(class1.a);L(new Object(){String a="1112";}.a);



Object objstr="TEST";
String str="TEST";
L(objstr.equals(str));
L(objstr.equals("TEST"));
Object objint=1;
int i=1;
L(objint.equals(i));
L((objint+"").equals("1"));
L(1==(Integer)objint);
%><%!
private boolean And(Object... o){
	for(int i=0,l=o.length;++i<l;){
		if(o[i]==null){if(o[i-1]!=null)return false;}
		else if(!o[i].equals(o[i-1]))return false;
	}
	return true;
}%>
<%
%><%!
private boolean Or(Object... o){
	for(int i=0,li=o.length;i<li;++i){
		for(int j=0,lj=o.length;j<lj;++j){
			if(j!=i){
				if(o[j]==null){
					if(o[i]==null)return true;
				}else if(o[j].equals(o[i]))return true;
			}
		}
	}
	return false;
}%>
<%

L(And(null,null,null,null));
L(And(1,1,1,1,1,2,1));
L(And("1","",""));

L(Or(1,"2",null,2,null,"3"));
int o=500;
String te="";
L(o+(o=50)+o);
Object[] arr = new Object[5];
L(arr.length);
%><%!private void testArr(String test, String... ttt){
	System.out.println(ttt[0]);

}
	private int add(int a, int b)
	{
	    while (b != 0)
	    {
	        int c = (a & b) << 1;
	        a ^= b;
	        b = c;
	    }
	    return a;
	}%><%
testArr("a", "aaa");
testArr("a", new String[]{"aaaa","aaa"});

long st=System.currentTimeMillis();
for(int j=0;j<100000000;j++){
	//int l=add(100, 200);
	int l=100+200;
}

L(System.currentTimeMillis()-st);
%>


<title>Insert title here</title>
</head>
<body>
test
</body>
</html>