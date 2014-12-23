package myclass.database;

import myclass.wrap.MyArray;

/**
 * MySQL用のSQL文が簡単に作れるやつ<br>
 * use文で使うデータベースを指定している前提
 * @author yuki
 *
 */
public abstract class SQLforMySQL {
	private static final String
	CREATE = "CREATE",
	TABLE = "TABLE",
	VALUES ="VALUES",
	START_KAKKO = "(",
	END_KAKKO = ")",
	SPACE = " ",
	DROP = "DROP",
	UNIQUE = "UNIQUE",
	INSERT = "INSERT",
	INTO = "INTO",
	UPDATE = "UPDATE",
	SET = "SET",
	WHERE = "WHERE",
	EQUAL = "=",
	HATENA = "?",
	COMMA= ",",
	SELECT = "SELECT",
	FROM = "FROM",
	DELETE ="DELETE";



	/**
	 * CREATE文
	 * @param tableName
	 * @param colsAndDataType カラム名 データ型 [オプション],,,,,
	 * @return
	 */
	public static String createTable(String tableName,String colsAndDataType) {
		return CREATE + SPACE + TABLE + SPACE + START_KAKKO + colsAndDataType + END_KAKKO;
	}

	public static String dropTable(String... tableName) {
		return DROP + SPACE + TABLE + SPACE + MyArray.join(tableName, COMMA);
	}

	/**
	 * インサート文を作って返す
	 * @param tableName テーブル名
	 * @param cols (c1,c2,c3)な文字列
	 * @param values (?,?,?)な文字列<br>prepareを使わない場合は文字列は''で囲む
	 * @return
	 */
	public static String insert(String tableName, String cols, String values) {
		return INSERT + SPACE + INTO + SPACE + tableName + cols + SPACE +VALUES + values;
	}

	/**
	 * インサート文を作って返す
	 * @param tableName テーブル名
	 * @param cols ["c1","c2","c3"]な文字列配列
	 * @param values ["?","?","?"]な文字列配列<br>prepareを使わない場合は文字列は''で囲む
	 * @return
	 */
	public static String insert(String tableName, String[] cols, String[] values) {
		return insert(tableName,createCols(cols),createCols(values));
	}

	/**
	 * インサート文を作って返す
	 * @param tableName テーブル名
	 * @param cols ["c1","c2","c3"]な文字列配列
	 * @return
	 */
	public static String insert(String tableName, String[] cols) {
		return insert(tableName,createCols(cols),createHatenaValues(cols.length));
	}

	/**
	 * update文作る
	 * @param tableName
	 * @param set SET句
	 * @param where WHERE句
	 * @return
	 */
	public static String update(String tableName,String set,String where) {
		return UPDATE + SPACE + tableName + SPACE + SET + SPACE + set + SPACE + WHERE +SPACE +where;
	}

	/**
	 * update文作る
	 * @param tableName
	 * @param set SET句
	 * @return
	 */
	public static String update(String tableName,String set) {
		return UPDATE + SPACE + tableName + SPACE + SET + SPACE + set;
	}

	/**
	 * update文作る
	 * @param tableName
	 * @param where WHERE句
	 * @return
	 */
	public static String update(String tableName,String col,String val,String where) {
		return UPDATE + SPACE + SET + SPACE + createSet(col,val) + SPACE + WHERE +SPACE +where;
	}

	/**
	 * update文作る
	 * @param tableName
	 * @param where WHERE句
	 * @return
	 */
	public static String update(String tableName,String[] cols,String[] vals,String where) {
		return UPDATE + SPACE + tableName  + SPACE + SET + SPACE + createSet(cols,vals) + SPACE + WHERE +SPACE +where;
	}
	/**
	 * update文作る
	 * @param tableName
	 * @param where WHERE句
	 * @return
	 */
	public static String update(String tableName,String[] cols,String where) {
		return UPDATE + SPACE + tableName  + SPACE + SET + SPACE + createSet(cols) + SPACE + WHERE +SPACE +where;
	}

	/**
	 * update文作る
	 * @param tableName
	 * @param where WHERE句
	 * @return
	 */
	public static String update(String tableName,String[] cols,String[] vals) {
		return UPDATE + SPACE + tableName + SPACE + SET + SPACE + createSet(cols,vals) ;
	}
	/**
	 * update文作る
	 * @param tableName
	 * @param where WHERE句
	 * @return
	 */
	public static String update(String tableName,String[] cols) {
		return UPDATE  + SPACE + tableName + SPACE + SET + SPACE + createSet(cols);
	}


	/**
	 * col=valな文字列
	 * @param col
	 * @param val 省略した場合col=?
	 * @return
	 */
	public static String createSet(String col,String val) {
		return col + EQUAL + val;
	}

	/**
	 * col=?
	 * @param col
	 * @return
	 */
	public static String createSet(String col) {
		return createSet(col,HATENA);
	}

	/**
	 * cols=values,,,
	 * @param cols
	 * @param values
	 * @return
	 */
	public static String createSet(String[] cols,String[] values) {
		return MyArray.join(MyArray.join(cols, values,EQUAL),COMMA);
	}

	/**
	 * cols=?,,,
	 * @param cols
	 * @param values
	 * @return
	 */
	public static String createSet(String[] cols) {
		return MyArray.join(cols, EQUAL + HATENA + COMMA) + EQUAL + HATENA;
	}

	/**
	 * SELECT文作る
	 * @param tableName
	 * @param cols
	 * @param where
	 * @return
	 */
	public static String select(String tableName,String cols,String where){
		return SELECT + SPACE + cols + SPACE + FROM + SPACE + tableName + SPACE + WHERE + SPACE + where;
	}

	/**
	 * select
	 * @param tableName
	 * @param cols 配列
	 * @param where
	 * @return
	 */
	public static String select(String tableName,String[] cols,String where){
		return SELECT + SPACE + createCols(false, cols) + SPACE + FROM + SPACE + tableName + SPACE + WHERE + SPACE + where;
	}

	/**
	 * SELECT文作る
	 * @param tableName
	 * @param cols
	 * @return
	 */
	public static String select(String tableName,String cols){
		return SELECT + SPACE + cols + SPACE + FROM + SPACE + tableName ;
	}

	/**
	 * select
	 * @param tableName
	 * @param cols 配列
	 * @return
	 */
	public static String select(String tableName,String[] cols){
		return SELECT + SPACE + createCols(false, cols) + SPACE + FROM + SPACE + tableName ;
	}
	/**
	 * DELETE文作る
	 * @param tableName
	 * @param where
	 * @return
	 */
	public static String delete(String tableName,String where){
		return DELETE + SPACE + FROM + SPACE + tableName + SPACE + WHERE + SPACE + where;
	}

	/**
	 * UNIQUE()
	 * @param cols
	 * @return
	 */
	public static String unique(String... cols) {
		return UNIQUE + createCols(cols);
	}
	/**
	 * (a,b,c)な感じにする
	 * @param cols "a","b","c"もしくは配列["a","b","c"]
	 * @return
	 */
	public static String createCols(String... cols){
		return START_KAKKO + MyArray.join(cols, COMMA) + END_KAKKO;
	}
	/**
	 * カッコなしパターン
	 * (a,b,c)な感じにする
	 * @param cols "a","b","c"もしくは配列["a","b","c"]
	 * @return
	 */
	public static String createCols(boolean dummy,String... cols){
		return MyArray.join(cols, COMMA);
	}

	/**
	 * i個の(?,?)つくる
	 * @param i
	 * @return
	 */
	public static String createHatenaValues(int i) {
		StringBuffer sb = new StringBuffer();
		sb.append(START_KAKKO);
		while(--i >= 0){
			sb.append(HATENA);
			if(i > 0)sb.append(COMMA);
		}
		return sb.append(END_KAKKO).toString();
	}

	/**
	 * i個の?,?つくる
	 * @param i
	 * @return
	 */
	public static String createHatenaValues(boolean dummy,int i) {
		StringBuffer sb = new StringBuffer();
		while(--i >= 0){
			sb.append(HATENA);
			if(i > 0)sb.append(COMMA);
		}
		return sb.toString();
	}

}
