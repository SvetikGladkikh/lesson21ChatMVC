package ru.js.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.js.chat.User;

import java.util.Map;

@Controller
@RequestMapping("/login")
@SessionAttributes(types = User.class)
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String loginGet() {
        logger.info("Show login form.");
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public RedirectView loginPost(User user) {
        logger.info(user.getUsername() + " was login.");
        return new RedirectView("/messages");
    }
}
