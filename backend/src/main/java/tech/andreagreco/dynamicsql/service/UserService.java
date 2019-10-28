package tech.andreagreco.dynamicsql.service;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.andreagreco.dynamicsql.mapper.UserMapper;
import tech.andreagreco.dynamicsql.model.User;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(long userId) {

        SQL query = new SQL();
        query.SELECT("*").FROM("users").WHERE("id = " + userId);

        return userMapper.findById(query);
    }

    public List<User> getAllUser() {

        SQL query = new SQL();
        query.SELECT("*").FROM("users");

        return userMapper.findAll(query);
    }
}
