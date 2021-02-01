package ru.js.chat.store;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BlockedUsersStoreImpl implements BlockedUsersStore{
    private final List<String> blockedUsers = Collections.synchronizedList(new ArrayList<String>());

    public List<String> getAll() {
        return new ArrayList<>(blockedUsers);
    }

    public boolean containUser(String username) {
        return blockedUsers.contains(username);
    }

    public void blockUser(String username) {
        if (!blockedUsers.contains(username)){
            blockedUsers.add(username);
        }
    }

    public void unblockUser(String username) {
        if (blockedUsers.contains(username)){
            blockedUsers.remove(username);
        }
    }
}
