package tech.andreagreco.dynamicsql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.andreagreco.dynamicsql.mapper.UserMapper;
import tech.andreagreco.dynamicsql.model.User;
import tech.andreagreco.dynamicsql.sqlutil.QueryBuilder;

import java.util.List;

/**
 * @author LikeGrees
 */
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QueryBuilder queryBuilder;

    public User getUser(long userId) {
        return userMapper.findById(queryBuilder.select(User.class).where(new String[]{"id = " + userId}).build());
    }

    public List<User> getAllUser() {
        return userMapper.findAll(queryBuilder.select(User.class).build());
    }
}
