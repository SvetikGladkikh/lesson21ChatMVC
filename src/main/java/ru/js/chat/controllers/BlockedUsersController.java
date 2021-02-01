package ru.js.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.js.chat.User;
import ru.js.chat.store.BlockedUsersStore;
import ru.js.chat.store.Message;
import ru.js.chat.store.MessageStore;

import java.util.List;

import static ru.js.chat.utils.Constants.ADMIN_USERNAME;

@Controller
@SessionAttributes(types = User.class)
public class BlockedUsersController {
    private BlockedUsersStore blockedUsersStore;
    private Logger logger = LoggerFactory.getLogger(BlockedUsersController.class);

    @Autowired
    public BlockedUsersController(BlockedUsersStore blockedUsersStore) {
        this.blockedUsersStore = blockedUsersStore;
    }

    @RequestMapping(path = "/blockedUsers", method = RequestMethod.GET)
    public ModelAndView getBlockedUsers(@SessionAttribute User user) {
        String username = getUsername(user);
        ModelAndView modelAndView = new ModelAndView("blockedUsersList");
        modelAndView.addObject("user", username);
        modelAndView.addObject("blockedUsers", blockedUsersStore.getAll());
        logger.info("Show blocked users.");
        return modelAndView;
    }

    @RequestMapping(path = "/blockUser", method = RequestMethod.GET)
    public String blockUserGet() {
        logger.info("Show form for block users.");
        return "blockUser";
    }

    @RequestMapping(path = "/blockUser", method = RequestMethod.POST)
    public RedirectView blockUserPost(String username){
        blockedUsersStore.blockUser(username);
        logger.info(username + "was blocked.");
        return new RedirectView("/blockedUsers");
    }

    @RequestMapping(path = "/unblockUser", method = RequestMethod.GET)
    public String unblockUserGet(){
        logger.info("Show form for unblock users.");
        return "unblockUser";
    }

    @RequestMapping(path = "/unblockUser", method = RequestMethod.POST)
    public RedirectView unblockUserPost(String username){
        blockedUsersStore.unblockUser(username);
        logger.info(username + "was unblocked.");
        return new RedirectView("/blockedUsers");
    }

    @RequestMapping(path = "/blockedUserMessage", method = RequestMethod.GET)
    public ModelAndView blockedUserPage(@SessionAttribute User user){
        logger.info(user.getUsername() + "was open blocked page.");
        ModelAndView modelAndView = new ModelAndView("blockedUserMessage");
        modelAndView.addObject("user", user.getUsername());
        return modelAndView;
    }

    private String getUsername(User user){
        String username = user.getUsername();
        if(username == null || "".equals(username)){
            throw new RuntimeException("Имя пользователя не может быть пустым!");
        }

        return username;
    }
}
