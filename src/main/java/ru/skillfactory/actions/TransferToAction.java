package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Перевести средства".
 */
public class TransferToAction implements UserAction {

    @Override
    public String getTitle() {
        return "Перевести средства";
    }

    /**
     * Метод, который относится к переводу баланса на счёт получателя. При вызове отправляет его на выполнение.
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
