package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Показать баланс".
 */
public class ShowBalanceAction implements UserAction {

    @Override
    public String getTitle() {
        return "Показать баланс";
    }

    /**
     * Метод, который относится к показу баланса. При вызове отправляет его на выполнение.
     *
     * @param bankService BankService объект.
     * @param input Input объект.
     * @param requisite Строка в произвольной форме, используется для поиска пользователя.
     * @return возвращает всегда true, приложение продолжает работать.
     */
    @Override
    public boolean execute(BankService bankService, Input input, String requisite) {
        return true;
    }
}
