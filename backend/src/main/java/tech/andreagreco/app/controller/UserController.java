package tech.andreagreco.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.andreagreco.app.model.User;
import tech.andreagreco.app.service.UserService;

import java.util.List;

/**
 * @author LikeGrees
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUserById (@PathVariable("userId") long userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/all")
    public List<User> getUserById () {
        return userService.getAllUser();
    }
}
