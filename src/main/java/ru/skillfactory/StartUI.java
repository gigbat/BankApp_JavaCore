package ru.skillfactory;

import ru.skillfactory.actions.*;

/**
 * Класс, который запускает общение с пользователем.
 */
public class StartUI {

    /**
     * Здесь будет происходить инициализация меню, вы
     *  1. Авторизовываете пользователя.
     *  2. Печатаете меню.
     *  3. В зависимости от введённого числа запускаете нужную функцию.
     *
     * @param bankService BankService объект.
     * @param actions массив с действиями.
     * @param input Input объект.
     */
    public void init(BankService bankService, UserAction[] actions, Input input) {
        String requisite = authorization(bankService, input);
        showMenu(actions);
        boolean run = true;

        while (run) {
            int select = input.askInt("Выберите пункт меню: ");
            if (select >= 0 && select <= actions.length - 1) {
                // Мы обращаемся к листу и запускаем один из статических методов, которые мы хранили в листе.
                run = actions[select].execute(bankService, input, requisite);
                // Ниже нужно реализовать метод authorization(BankService bankService, Input input)????
            } else {
                System.out.println("Такого пункта нету...");
            }
        }
    }


    /**
     * Метод должен работать пока пользователь не авторизуется (пока отключил цикл!).
     *
     * @param bankService BankService объект.
     * @param input Input объект.
     * @return возвращает реквизиты аккаунта, под которым авторизовался пользователь.
     *         Получайте их вызывом метода getRequisiteIfPresent, класса BankService.
     */
    private String authorization(BankService bankService, Input input) {
        String rsl = null;
        boolean authComplete = false;
        while (!authComplete) { // цикл отключён!!!
            /*
             * Запрашиваете у пользователя логин, пароль пока он не пройдёт авторизацию.
             * Авторизация пройдена при условие что в BankService есть пользователь с
             * данным логином и паролем (работайте только с теми пользователями что есть).
             */
            String login = input.askStr("Ваш логин: ");
            String password = input.askStr("Ваш password: ");

            BankAccount bankAccount = new BankAccount("",password,login);
            rsl = String.valueOf(bankService.getRequisiteIfPresent(bankAccount));

            if (rsl.length() >= 12) {
                authComplete = true;
            } else {
                authComplete = false;
            }
        }
        return rsl;
    }

    /**
     * Печатается меню пользователя (только печатается, общения с пользователем нету).
     *
     * @param actions массив с действиями.
     */
    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].getTitle());
        }
    }

    public static void main(String[] args) {
        BankService bankService = new BankService();
        // здесь создадите несколько аккаунтов на проверку
        // данные осмысленно заполните, не просто пустые строки
        bankService.addAccount(new BankAccount("Богдан", "1234", "5153655321456782"));
        bankService.addAccount(new BankAccount("Иван", "4321", "9876216526741253"));
        bankService.addAccount(new BankAccount("Сергей", "1010", "8563874390124545"));
        bankService.addAccount(new BankAccount("Сергей", "2525", "4149876523341237"));
        bankService.addAccount(new BankAccount("Богдан", "2121", "4149646523321237"));
        // Ещё аккаунты

        // Наши действия мы будет хранить в специальном листе, вызывать из этого же списка
        UserAction[] actions = {
                new ShowBalanceAction(),
                new TopUpBalanceAction(),
                new TransferToAction(),
                new Exit()
        };

        // Наш Input можно менять на нужную реализацию (ValidateInput доделайте)
        Input input = new ValidateInput();
        // Запускаем наш UI передавая аргументами банковский сервис, экшены и Input.
        new StartUI().init(bankService, actions, input);
    }
}
