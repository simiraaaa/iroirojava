package myclass.wrap;

import java.util.HashMap;

/**
 * putとか楽にやる
 * @author yuki
 *
 */
public class MyHashMap {

	/**
	 * mapにいろいろ をputsして返す。代入の必要はない
	 * @param map
	 * @param os
	 * @return
	 */
	public static HashMap<Object,Object> puts(HashMap<Object,Object> map,Object... os) {
		for(int i =0,l=os.length;i<l;){map.put(os[i++],os[i++]);}
		return map;
	}

	/**
	 * HashMap作って返す
	 * @param os
	 * @return
	 */
	public static HashMap<Object,Object> create(Object... os) {
		return puts(new HashMap<Object,Object>(), os);
	}
}
