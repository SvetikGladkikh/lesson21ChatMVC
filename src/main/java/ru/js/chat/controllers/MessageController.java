package ru.js.chat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.js.chat.User;
import ru.js.chat.store.Message;
import ru.js.chat.store.MessageStore;

import java.util.Date;
import java.util.List;

import static ru.js.chat.utils.Constants.ADMIN_USERNAME;

@Controller
@SessionAttributes(types = User.class)
public class MessageController {
    private MessageStore messageStore;
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @RequestMapping(path = "/messages", method = RequestMethod.GET)
    public String getMessages(@SessionAttribute User user, ModelMap model) {
        String username = getUsername(user);
        boolean isAdmin = isAdmin(user);
        List<Message> messages = messageStore.getAll();
        model.addAttribute("user", username);
        model.addAttribute("messages", messages);
        model.addAttribute("isAdmin", isAdmin);
        logger.info("Show message list.");
        return "messageList";
    }

    @RequestMapping(path = "/newMessage", method = RequestMethod.GET)
    public ModelAndView getMessages(@SessionAttribute User user) {
        String username = getUsername(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", username);
        modelAndView.setViewName("newMessage");
        logger.info("Show form for write message.");
        return modelAndView;
    }

    @RequestMapping(path = "/newMessage", method = RequestMethod.POST)
    public RedirectView addMessage(@SessionAttribute User user, String messageText) {
        String username = getUsername(user);
        Message message = new Message(new Date(), username, messageText);
        messageStore.addMessage(message);
        logger.info("New message was added from " + username + ". Text: " + messageText );
        return new RedirectView("/messages");
    }

    protected String getUsername(User user){
        String username = user.getUsername();
        if(username == null || "".equals(username)){
            throw new RuntimeException("Имя пользователя не может быть пустым!");
        }

        return username;
    }

    protected boolean isAdmin(User user){
        return ADMIN_USERNAME.equals(getUsername(user));
    }
}
