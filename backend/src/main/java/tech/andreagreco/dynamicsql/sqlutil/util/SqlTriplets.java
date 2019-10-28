package tech.andreagreco.dynamicsql.sqlutil.util;

/**
 * @author LikeGrees
 */
public class SqlTriplets<X, Y, Z> {
    private X column;
    private Y table;
    private Z where;

    public X getColumn() {
        return column;
    }

    public void setColumn(X column) {
        this.column = column;
    }

    public Y getTable() {
        return table;
    }

    public void setTable(Y table) {
        this.table = table;
    }

    public Z getWhere() {
        return where;
    }

    public void setWhere(Z where) {
        this.where = where;
    }
}