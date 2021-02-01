package ru.js.chat.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import ru.js.chat.User;
import ru.js.chat.utils.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.js.chat.utils.Constants.ADMIN_USERNAME;
import static ru.js.chat.utils.Constants.USERNAME_SESSION_ATTRIBUTE;

public class AdminUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute(USERNAME_SESSION_ATTRIBUTE);
        if(!ADMIN_USERNAME.equals(user.getUsername())){
            response.sendRedirect(request.getContextPath() + Constants.MESSAGES_URL);
            return false;
        }
        return true;
    }
}
