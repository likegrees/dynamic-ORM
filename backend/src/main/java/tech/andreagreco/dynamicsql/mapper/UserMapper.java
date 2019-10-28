package tech.andreagreco.dynamicsql.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import tech.andreagreco.dynamicsql.model.User;
import tech.andreagreco.dynamicsql.sqlutil.SelectQueryProvider;

import java.util.List;

@Mapper
public interface UserMapper {

    @SelectProvider(type= SelectQueryProvider.class, method="getSelect")
    @Results(id="userMap" , value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname"),
            @Result(property = "username", column = "username")
    })
    List<User> findAll(SQL query);

    @SelectProvider(type= SelectQueryProvider.class, method="getSelect")
    @ResultMap("userMap")
    User findById (SQL query);
}
