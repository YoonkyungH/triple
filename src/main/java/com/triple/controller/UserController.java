package com.triple.controller;

import com.triple.domain.User;
import com.triple.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/new")
    public void create(@RequestBody UserForm form) {
        User user = new User();

        user.setUserUUID(UUID.randomUUID().toString());
        user.setName(form.getName());

        userService.create(user);
    }


    @ResponseBody
    @GetMapping("/user/mileage")
    public int getMileage(@RequestBody UserForm form) {
        return userService.getMileage(form.getUserUUID());
    }
}
