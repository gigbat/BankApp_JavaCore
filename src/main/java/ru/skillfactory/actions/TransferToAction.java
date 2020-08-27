package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Перевести средства", используется в StartUI.
 */
public class TransferToAction implements UserAction {

    @Override
    public String getTitle() {
        return "Перевести средства";
    }

    /**
     * Перевести средства - также общаетесь в этом методе с пользователем и передаёте информацию,
     * так как операция важная желательно ещё раз заставлять вводить пароль/логин и передавать информацию
     * в BankService. Exceptions пользователю печатать не надо (как и в других методах этого класса),
     * вводите подсказки или написанные вами сообщения об ошибках.
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
