package tech.andreagreco.dynamicsql.sqlutil;

import org.apache.ibatis.jdbc.SQL;

public class SelectBuilder {
    public String getSelect(long id){
        SQL query = new SQL();
        query.SELECT("*")
                .FROM("users")
                .WHERE("id = " + id);
        return query.toString();
    }
}
