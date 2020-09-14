package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Интрефейс для реализации различных действий.
 */
public interface UserAction {

    /**
     * Метод получает подзаголовок Action-а.
     *
     * @return Строку текста, которая содержит заголовок для действия.
     */
    String getTitle();

    /**
     * Выполнить Action.
     *
     * @param bankService BankService объект.
     * @param input Input объект.
     * @param requisite Строка в произвольной форме, используется для поиска пользователя.
     * @return true если после этого действие приложение продолжает работу.
     */
    boolean execute(BankService bankService, Input input, String requisite);
}
