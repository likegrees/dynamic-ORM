package tech.andreagreco.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.andreagreco.app.dynamicbatis.QueryBuilder;
import tech.andreagreco.app.entity.User;
import tech.andreagreco.app.mapper.UserMapper;
import tech.andreagreco.app.model.UserModel;
import tech.andreagreco.app.model.UserModelTwo;

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
        return userMapper.findById(queryBuilder.select(UserModel.class).where(new String[]{"id = " + userId}).build());
    }

    public List<User> getAllUser() {
        return userMapper.findAll(queryBuilder.select(UserModel.class).build());
    }

    public User getUserTwo(long userId) {
        return userMapper.findById(queryBuilder.select(UserModelTwo.class).where(new String[]{"id = " + userId}).build());
    }

    public List<User> getAllUserTwo() {
        return userMapper.findAll(queryBuilder.select(UserModelTwo.class).build());
    }
}
