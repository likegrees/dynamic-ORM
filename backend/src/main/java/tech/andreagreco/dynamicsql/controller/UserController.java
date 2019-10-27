package tech.andreagreco.dynamicsql.controller;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.andreagreco.dynamicsql.mapper.UserMapper;
import tech.andreagreco.dynamicsql.model.User;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{userId}")
    public User getUserById (@PathVariable("userId") long userId) {
        return userMapper.findById(userId);
    }

    @GetMapping("/all")
    public List<User> getUserById () {
        return userMapper.findAll();
    }
}
