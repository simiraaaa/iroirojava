package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class Test implements InterfaceTest{

	public Test Hello() {
		log("hello_world");
		HashMap<String, String> map=new HashMap<String, String>();
		TestMap.puts(map, "test","test","test1","1","test2","2");
		HashMap<String, String> map2=TestMap.create("t1","1","t2","2","t3","3","t4","4");
		for(String s: map.keySet()){
			log(s+":"+map.get(s));
		}
		for(String s: map2.keySet()){
			log(s+":"+map2.get(s));
		}
		return this;
	}
	public Test() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public void loop() {
		for(int i=-1; ++i<10;)log(i);
		log("");
		for(int i=0; i++<10;)log(i);

		//org.apache.jasper.compiler.tagplugin.TagPluginContext.generateJavaSource("");
	}
	public Object Switch(String key,HashMap<String, Object> map) {
		return new String[][][]{};
	}

	private void log(Object o) {
		System.out.println(o);
	}
	class T2{
		private String[] ss;
		public T2(String[] ss) {
			this.ss=ss;
		}
		public T2(String[] ss,int t) {
			this.ss=ss;
		}
	}

}
