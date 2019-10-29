package tech.andreagreco.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tech.andreagreco.app.dynamicbatis.QueryProvider;
import tech.andreagreco.app.model.User;

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
