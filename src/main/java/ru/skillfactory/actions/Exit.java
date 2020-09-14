package ru.skillfactory.actions;

import ru.skillfactory.*;

/**
 * Класс для реализации действия "Выйти из программы".
 */
public class Exit implements UserAction {

    @Override
    public String getTitle() {
        return "Выйти из программы";
    }

    /**
     * Метод, который относится к завершению программы. При вызове отправляет его на выполнение и завершает
     * работу программы.
     *
     * @param bankService BankService объект.
     * @param input Input объект.
     * @param requisite Строка в произвольной форме, используется для поиска пользователя.
     * @return возвращает false провоцируя выход из приложения.
     */
    @Override
    public boolean execute(BankService bankService, Input input, String requisite) {
        System.out.println("Приложение завершило работы.");
        return false;
    }
}
