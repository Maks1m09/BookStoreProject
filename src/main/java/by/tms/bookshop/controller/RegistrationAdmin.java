package by.tms.bookshop.controller;

import by.tms.bookshop.entity.Role;
import by.tms.bookshop.entity.User;
import by.tms.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationAdmin {

    @Autowired
    UserService userService;

    @GetMapping("/registration_admins")
    public String registrationAdmin(Model model) {
        return "registration_admins";
    }

    @PostMapping("/registration_admins")
    public String addAdmin(@RequestParam String userName, @RequestParam String login, @RequestParam String password, @RequestParam String email, Model model, Map<String, Object> mod) {
        User user = new User(userName, login, password, email);
        if (userService.findUserByLogin(user.getLogin()) != null) {
            mod.put("message", "User exists!");
            return "registration_admins";
        }
        user.setRoles(Collections.singleton(Role.ADMIN_ROLE));
        userService.saveUser(user);
        return "redirect:/login";
    }
}
