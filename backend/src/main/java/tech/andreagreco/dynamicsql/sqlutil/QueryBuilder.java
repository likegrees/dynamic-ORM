package tech.andreagreco.dynamicsql.sqlutil;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import tech.andreagreco.dynamicsql.sqlutil.util.SqlTriplets;

@Component
public class QueryBuilder {

    public SQL select(SqlTriplets<String[], String[], String[]> parameters) {
        SQL query = new SQL();

        query.SELECT(parameters.getColumn())
                .FROM(parameters.getTable())
                .WHERE(parameters.getWhere());
        return query;
    }
}
