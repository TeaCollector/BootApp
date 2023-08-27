package com.rain.springbootdemo.controller;

import com.rain.springbootdemo.model.User;
import com.rain.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("users", listUsers);
        return "user-list";                                          // здесь возвращаем строку, где будет находится логика
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {           // этот метод отдельно создает форму, куда мы будем вбивать данные
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {               // а вот здесь уже будем сохранять данные в базу
        userService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
}
