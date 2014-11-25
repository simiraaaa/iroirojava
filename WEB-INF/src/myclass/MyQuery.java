package myclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public abstract class MyQuery{
	public MyQuery() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	private HashMap<Object, HashMap<String, Object>> map=null;

	/**
	 * データベースにアクセスしSQL文を実行する（con.close()しない）
	 * @param con Connection
	 * @param sql SQL文
	 * @return
	 */
	public boolean exe(Connection con, String sql) {
		return exe(con, sql, "");
	}

	/**
	 * データベースにアクセスしSQL文を実行する（con.close()しない）
	 * @param con Connection
	 * @param sql SQL文
	 * @param primary mapの主キーにしたいフィールド名
	 * @param fields mapにセットしたいフィールド
	 * @return
	 */
	public boolean exe(Connection con, String sql, String primary,String... fields) {
		boolean isTrue =false;
		boolean isSelect=!"".equals(primary);

		try {
			if(isSelect){
				isTrue=exe(con.prepareStatement(sql), primary, fields);
			}else {
				isTrue=exe(con.prepareStatement(sql));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("エラーが出たSQL文\n"+sql);
		}

		return isTrue;
	}


	/**
	 *
	 * @param ps 実行するPreparedStatement
	 * @param primary マップに保存するデータの主キーにしたい列名
	 * @param fields マップに保存するデータの列名
	 * @return 成功ならtrue
	 */
	public boolean exe(PreparedStatement ps, String primary,String... fields ) {
		boolean isTrue=false;
		boolean isSelect = !"".equals(primary);
		if(isSelect){
			ResultSet rs=null;
			try {
				setPreparedSql(ps);
				rs=ps.executeQuery();
				this.map=getMap(rs, primary, fields);
				isTrue=true;
			} catch (SQLException e) {
				System.out.println("ps.executeQueryのエラー");
				e.printStackTrace();
			}finally{
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						System.out.println("rsクローズ時のエラー");
						e.printStackTrace();
					}
				}
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println("ps.close();時のエラー");
						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				ps.executeUpdate();
				isTrue=true;
			} catch (SQLException e) {
				System.out.println("ps.executeUpdate();時のエラー");
				e.printStackTrace();
			}finally{
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						System.out.println("ps.close();時のエラー");
						e.printStackTrace();
					}
				}
			}

		}

		return isTrue;
	}
	/**
	 * Select以外のSQL文を実行する
	 * @param ps実行するPreparedStatement
	 * @return 成功ならtrue
	 */
	public boolean exe(PreparedStatement ps) {
		return exe(ps, "");
	}

	/**
	 * ResultSetをmapに格納する(closeはしない)
	 * @param primary mapの主キーにしたいフィールド名
	 * @param fields mapにセットしたいフィールド
	 * @return mp セットしたマップ
	 * @throws SQLException
	 */
	public HashMap<Object,  HashMap<String, Object>> getMap(ResultSet rs,String primary,String... fields) throws SQLException {
		HashMap<Object,  HashMap<String, Object>> mp =new HashMap<Object, HashMap<String, Object>>();
		int len=fields.length;
		while( rs.next() ){
			HashMap<String, Object> map2=new HashMap<String, Object>();
			for(int j=0;j<len;)map2.put(fields[j],rs.getObject(fields[j++]));
			mp.put(rs.getObject(primary), map2);
		}
		return mp;
	}
	public HashMap<Object,  HashMap<String, Object>> getMap() {
		return this.map;
	}
	/**
	 * exeメソッドの中で使う
	 * 何も処理しなければ setPreparedSql(PreparedStatement ps){}と書く
	 * @param ps
	 */
	abstract protected void setPreparedSql(PreparedStatement ps);
}