package ru.js.chat.store;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessagesStoreImpl implements MessageStore {
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

    public List<Message> getAll() {
        return new ArrayList<Message>(messages);
    }

    public void addMessage(Message newMessage) {
        messages.add(newMessage);
    }
}
