package tech.andreagreco.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.andreagreco.app.dynamicbatis.QueryBuilder;
import tech.andreagreco.app.mapper.UserMapper;
import tech.andreagreco.app.model.User;

import java.util.List;

/**
 * @author LikeGrees
 */
@Component
public class UserService {

    private UserMapper userMapper;

    private QueryBuilder queryBuilder;

    @Autowired
    UserService(UserMapper userMapper, QueryBuilder queryBuilder) {
        this.userMapper = userMapper;
        this.queryBuilder = queryBuilder;
    }

    public User getUser(long userId) {
        return userMapper.findById(queryBuilder.select(User.class).where(new String[]{"id = " + userId}).build());
    }

    public List<User> getAllUser() {
        return userMapper.findAll(queryBuilder.select(User.class).build());
    }
}
