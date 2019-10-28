package tech.andreagreco.dynamicsql.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tech.andreagreco.dynamicsql.model.User;
import tech.andreagreco.dynamicsql.sqlutil.QueryProvider;

import java.util.List;

/**
 * @author LikeGrees
 */
@Mapper
public interface UserMapper {

    @SelectProvider(type= QueryProvider.class, method="getSelect")
    @Results(id="userMap" , value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname"),
            @Result(property = "username", column = "username")
    })
    List<User> findAll(SQL query);

    @SelectProvider(type= QueryProvider.class, method="getSelect")
    @ResultMap("userMap")
    User findById (SQL query);
}
