package tech.andreagreco.dynamicsql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.andreagreco.dynamicsql.model.User;
import tech.andreagreco.dynamicsql.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public User getUserById (@PathVariable("userId") long userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/all")
    public List<User> getUserById () {
        return userService.getAllUser();
    }
}
