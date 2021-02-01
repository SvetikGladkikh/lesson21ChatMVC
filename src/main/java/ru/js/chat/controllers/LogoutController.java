package ru.js.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;
import ru.js.chat.User;

@Controller
@RequestMapping("/logout")
@SessionAttributes(types = User.class)
public class LogoutController {
    private Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @RequestMapping(method = RequestMethod.GET)
    public RedirectView logout(SessionStatus sessionStatus, @SessionAttribute User user) {
        logger.info(user.getUsername() + " was logout.");
        sessionStatus.setComplete();
        user.setUsername(null);
        return new RedirectView("/login");
    }
}
