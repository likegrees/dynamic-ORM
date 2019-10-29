package tech.andreagreco.app.dynamicbatis;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author LikeGrees
 */
public class QueryProvider {
    public String getSelect(SQL query){
        return query.toString();
    }
}
