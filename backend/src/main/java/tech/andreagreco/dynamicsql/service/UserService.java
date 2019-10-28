package tech.andreagreco.dynamicsql.service;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.andreagreco.dynamicsql.mapper.UserMapper;
import tech.andreagreco.dynamicsql.model.User;
import tech.andreagreco.dynamicsql.sqlutil.QueryBuilder;
import tech.andreagreco.dynamicsql.sqlutil.util.SqlTriplets;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QueryBuilder queryBuilder;

    public User getUser(long userId) {

        SqlTriplets<String[], String[], String[]> triplet = new SqlTriplets();

        triplet.setColumn(new String[]{"name", "surname"});
        triplet.setTable(new String[]{"users"});
        triplet.setWhere(new String[]{"id = " + userId});

        return userMapper.findById(queryBuilder.select(triplet));
    }

    public List<User> getAllUser() {

        SqlTriplets<String[], String[], String[]> triplet = new SqlTriplets();
        triplet.setColumn(new String[]{"*"});
        triplet.setTable(new String[]{"users"});

        return userMapper.findAll(queryBuilder.select(triplet));
    }
}
