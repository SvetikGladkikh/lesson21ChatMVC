package ru.js.chat.store;

import java.util.List;

public interface MessageStore {

    /**
     * Получить все сообщения
     * @return все сообщения из хранилища
     */
    List<Message> getAll();

    /**
     * Добавить сообщение в хранилище
     * @param newMessage новое сообщение
     */
    void addMessage(Message newMessage);
}
