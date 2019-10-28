package tech.andreagreco.dynamicsql.sqlutil;

import org.apache.ibatis.jdbc.SQL;

public class SelectQueryProvider {
    public String getSelect(SQL query){
        return query.toString();
    }
}
