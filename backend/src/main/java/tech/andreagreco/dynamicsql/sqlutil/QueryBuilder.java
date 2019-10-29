package tech.andreagreco.dynamicsql.sqlutil;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author LikeGrees
 */
@Component
public class QueryBuilder {

    private Map<String, Map<String, String[]>> tablesMap;

    private SQL query;

    @Autowired
    QueryBuilder(@Qualifier("tablesMap")Map<String, Map<String, String[]>> tablesMap) {
        this.tablesMap = tablesMap;
    }

    public QueryBuilder select(Class<?> table) {
        query = new SQL();
        query.SELECT(tablesMap.get(table.getName()).get("columnsName"))
                .FROM(tablesMap.get(table.getName()).get("tableName"));
        return this;
    }

    public QueryBuilder where(String[] conditions) {
        query.WHERE(conditions);
        return this;
    }

    public SQL build() {
        return query;
    }

}
