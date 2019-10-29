package tech.andreagreco.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import tech.andreagreco.app.dynamicbatis.QueryProvider;
import tech.andreagreco.app.entity.User;

import java.util.List;

/**
 * @author LikeGrees
 */
@Mapper
public interface UserMapper {

    @SelectProvider(type= QueryProvider.class, method="getSelect")
    List<User> findAll(SQL query);

    @SelectProvider(type= QueryProvider.class, method="getSelect")
    User findById (SQL query);
}
