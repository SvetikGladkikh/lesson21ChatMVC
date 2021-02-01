package ru.js.chat.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.js.chat.User;
import ru.js.chat.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.js.chat.utils.Constants.USERNAME_SESSION_ATTRIBUTE;

public class AuthUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute(USERNAME_SESSION_ATTRIBUTE);
        if((user == null || user.getUsername() == null)
                && !Constants.LOGIN_URL.equals(request.getRequestURI())) {
            response.sendRedirect(request.getContextPath() + Constants.LOGIN_URL);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User)request.getSession().getAttribute(USERNAME_SESSION_ATTRIBUTE);
        if(user != null && "".equals(user.getUsername())) {
            throw new RuntimeException("Имя пользователя не может быть пустой строкой!");
        }
    }
}
