package ru.js.chat.store;

import java.util.List;

public interface BlockedUsersStore {

    /**
     * Получить всех заблокированных пользователей
     * @return логины всех заблокированных пользователей
     */
    List<String> getAll();

    /**
     * Проверка наличия пользователя в списке
     * @param username имя проверяемого пользователя
     * @return true при наличии пользователя
     */
    boolean containUser(String username);

    /**
     * Добавить заблокированного пользователя в хранилище
     * @param username логин пользователя
     */
    void blockUser(String username);

    /**
     * Удалить пользователя из заблокированных в хранилище
     * @param username логин пользователя
     */
    void unblockUser(String username);
}
