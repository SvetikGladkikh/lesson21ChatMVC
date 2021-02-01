package ru.js.chat.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.js.chat.User;
import ru.js.chat.store.BlockedUsersStore;
import ru.js.chat.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.js.chat.utils.Constants.USERNAME_SESSION_ATTRIBUTE;

public class BlockedUserInterceptor implements HandlerInterceptor {

    private final BlockedUsersStore blockedUsersStore;

    @Autowired
    public BlockedUserInterceptor(BlockedUsersStore blockedUsersStore) {
        this.blockedUsersStore = blockedUsersStore;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute(USERNAME_SESSION_ATTRIBUTE);
        if(user != null
                && blockedUsersStore.containUser(user.getUsername())
                && !Constants.BLOCKED_USER_MESSAGE_URL.equals(request.getRequestURI())
                && !Constants.LOGIN_URL.equals(request.getRequestURI())){
            response.sendRedirect(request.getContextPath() + Constants.BLOCKED_USER_MESSAGE_URL);
            return false;
        }

        return true;
    }
}
