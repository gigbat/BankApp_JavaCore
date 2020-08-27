package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Пополнить баланс", используется в StartUI.
 */
public class TopUpBalanceAction implements UserAction {

    @Override
    public String getTitle() {
        return "Пополнить баланс";
    }

    /**
     * В этом методе обращайтесь к банковскому сервису, уточняйте у пользователя на сколько он хочет
     * пополнить баланс, каким способом... печатайте результат, может быть баланс после пополнения
     * (на ваше усмотрение).
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
