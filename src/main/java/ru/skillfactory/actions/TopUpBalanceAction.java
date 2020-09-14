package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Пополнить баланс".
 */
public class TopUpBalanceAction implements UserAction {

    @Override
    public String getTitle() {
        return "Пополнить баланс";
    }

    /**
     * Метод, который относится к пополнению баланса. При вызове отправляет его на выполнение.
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
