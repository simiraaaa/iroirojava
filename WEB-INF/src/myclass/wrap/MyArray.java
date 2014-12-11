package myclass.wrap;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ArrayList、配列操作を楽にする
 * @author yuki
 *
 */
public class MyArray {

	public static<E> ArrayList<E> adds(ArrayList<E> arr,E... os) {
		for(int i =0,l=arr.size();i<l;){arr.add(os[i++]);}
		return arr;
	}

	public static<E> ArrayList<E> create(E... os) {
		return adds(new ArrayList<E>(), os);
	}

	/**
	 * ArrayListを結合
	 * @param arr
	 * @param s
	 * @return
	 */
	public static<E> String join(ArrayList<E> arr,final String s) {
		StringBuffer joined=new StringBuffer();
		for(int i =0,l=arr.size();i<l;){
			joined.append(arr.get(i++));
			if(i!=l-1){joined.append(s);}
		}
		return joined.toString();
	}


	/**
	 * 配列をじょいん
	 * @param arr
	 * @param s
	 * @return
	 */
	public static<E> String join(E[] arr,final String s) {
		StringBuffer joined=new StringBuffer();
		for(int i =0,l=arr.length;i<l;){
			joined.append(arr[i++]);
			if(i!=l-1){joined.append(s);}
		}
		return joined.toString();
	}

	/**
	 * ArrayListをただの配列に変換
	 * @param arr
	 * @return
	 */
	public static <E> E[] convert(ArrayList<E> arr){
		int size= arr.size();
		Object[] es=new Object[size];
		for(int i = size-1;i>=0;--i){
			es[i]=arr.get(i);
		}
		return (E[]) es;
	}


	/**
	 * 配列をArrayListに
	 * @param es
	 * @return
	 */
	public static <E> ArrayList<E> convert(E[] es){
		return create(es);
	}

	/**
	 * 第一引数に第二引数を付け加えた新しい配列を返す
	 * @param es1
	 * @param es2
	 * @return
	 */
	public static<E> E[] add(E[] es1,E[] es2) {
		int i1 = es1.length;
		int i2 = es2.length+i1;

		Object[] es3 = new Object[i2];
		int i = 0;
		for(;i < i1;++i){
			es3[i]=es1[i];
		}
		for(int j = 0;i<i2;++j,++i){
			es3[i]=es2[j];
		}
		return (E[]) es3;
	}
}
