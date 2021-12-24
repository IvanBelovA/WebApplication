package ru.belov.webapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.belov.webapplication.model.User;
import ru.belov.webapplication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/not-confirm-mail")
    public List<User> findNotConfirm() {
        return userService.findNotConfirmMail();
    }

    @GetMapping("/not-login")
    public List<User> findNotLogin() {
        return userService.findNotLogin();
    }

    @GetMapping("/active-token")
    public List<User> findActiveToken() {
        return userService.findUsersActiveToken();
    }

    @GetMapping("/top-fail-login")
    public List<User> findTopFailLogin() {
        return userService.findTopFailLogin();
    }

}
