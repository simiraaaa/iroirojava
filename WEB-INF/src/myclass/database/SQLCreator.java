package myclass.database;

import myclass.util.Compare;

/**
 * setしたデータをもとに簡単なSQL文を作る
 */
public class SQLCreator {
    private String table, whr;

    private String[] columns;

    public SQLCreator() {
    }


    public SQLCreator(String tableName) {
        setTable(tableName);
    }

    public SQLCreator(String tableName, String... cols) {
        setTable(tableName).setColumns(cols);
    }

    /**
     * 使用するテーブルをセット<br>
     * select文の場合は複数のテーブルをスペース区切りでセットできます。
     *
     * @param tableName
     * @return
     */
    public final SQLCreator setTable(String tableName) {
        table = tableName;
        return this;
    }

    public final String getTable() {
        return table;
    }

    /**
     * UPDATE SELECT INSERTで使用するカラムをセット SELECT の場合はtableが複数ある場合 table.col [as
     * name]という感じで
     *
     * @param cols
     * @return
     */
    public final SQLCreator setColumns(String... cols) {
        columns = cols;
        return this;
    }

    public final String[] getColumns() {
        return columns;
    }

    /**
     * where文をセットします。<br>
     * prepareStatementで?を使用する場合、最後に追加されるものと考えてください。 <br>
     * where文を使用しない場合nullか""をセットしてください。
     *
     * @param where
     * @return
     */
    public final SQLCreator setWhere(String where) {
        whr = where;
        return this;
    }

    public final String getWhere() {
        return whr;
    }

    /**
     * insert文発行<br>
     * valuesを指定しなかった場合すべて?になります
     *
     * @param values
     * @return
     */
    public final String insert(String... values) {
        return SQLforMySQL.insert(table, columns, values);
    }

    /**
     * insert
     *
     * @return
     */
    public final String insert() {
        return SQLforMySQL.insert(table, columns);
    }

    /**
     * delete文を発行<br>
     * whereは必ずセットしてください
     *
     * @return
     */
    public final String delete() {
        return SQLforMySQL.delete(table, whr);
    }

    /**
     * update
     *
     * @param values
     * @return
     */
    public final String update(String... values) {
        return Compare.isEmpty(whr) ? SQLforMySQL.update(table, columns, values) : SQLforMySQL
                .update(table, columns, values, whr);
    }

    /**
     * update
     *
     * @return
     */
    public final String update() {
        return Compare.isEmpty(whr) ? SQLforMySQL.update(table, columns)
                : SQLforMySQL.update(table, columns, whr);
    }

    /**
     * select
     *
     * @return
     */
    public final String select() {
        return Compare.isEmpty(whr) ? SQLforMySQL.select(table, columns)
                : SQLforMySQL.select(table, columns, whr);
    }

    private void checkWhere() {
        if (whr == null) {
            whr = "";
        }
    }

    /**
     * where文の後ろにorder byを追加
     * 
     * @param cols
     * @return
     */
    public SQLCreator addOrderBy(String cols) {
        checkWhere();
        whr += SQLforMySQL.orderBy(cols);
        return this;
    }

    /**
     * where文の後ろにgroup by 追加
     */
    public SQLCreator addGroupBy(String cols) {
        checkWhere();
        whr += SQLforMySQL.groupBy(cols);
        return this;
    }

    /**
     * limit追加
     * 
     * @param start
     * @param size
     * @return
     */
    public SQLCreator addLimit(int start, int size) {
        checkWhere();
        whr += SQLforMySQL.limit(start, size);
        return this;
    }

    /**
     * limit追加
     * 
     * @param size
     * @return
     */
    public SQLCreator addLimit(int size) {
        checkWhere();
        whr += SQLforMySQL.limit(size);
        return this;
    }

}