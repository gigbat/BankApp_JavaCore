package ru.skillfactory;

import ru.skillfactory.actions.*;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, который запускает общение с пользователем.
 */
public class StartUI {

    /**
     * Метод инициализирующее меню.
     *
     * @param bankService BankService объект.
     * @param actions     массив с действиями.
     * @param input       Input объект.
     */
    public void init(BankService bankService, UserAction[] actions, Input input) {
        String requisite = authorization(bankService, input);
        showMenu(actions);
        boolean run = true;

        while (run) {
            int select = input.askInt("Выберите пункт меню: ");
            if (select >= 0 && select <= actions.length - 1) {
                run = actions[select].execute(bankService, input, requisite);
                getAccount(requisite, bankService, select);
            } else {
                System.out.println("Такого пункта нету...");
            }
        }
    }

    public void getAccount(String requisite, BankService bankService, int select) {
        Scanner scanner = new Scanner(System.in);

        if (select == 0) {
            System.out.println("Баланс: " + bankService.getBalanceForAccount(requisite));
        } else if (select == 1) {
            System.out.print("Введите сумму для пополнения: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (bankService.topUpBalance(requisite, amount)) {
                System.out.println("Баланс успешно пополнен!");
            }
        } else if (select == 2) {
            try {
                System.out.print("Введите имя получателя: ");
                String username = scanner.nextLine();

                System.out.print("Подтвердите ваш пароль: ");
                String password = scanner.nextLine();

                System.out.print("Введите реквизиты получателя: ");
                String destRequisite = scanner.nextLine();

                System.out.print("Введите сумму пополнения: ");
                long amount = Long.parseLong(scanner.nextLine());

                if (bankService.transferMoney(username, password, requisite, destRequisite, amount)) {
                    System.out.println("Средства успешно переведены!");
                }
            } catch (NullPointerException e) {
                System.err.println("Такого пользователя не существует.");
            }
        } else if (select == 3) {
            System.out.println("Выход с программы.");
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Метод для авторизации пользователя.
     *
     * @param bankService BankService объект.
     * @param input       Input объект.
     * @return возвращает реквизиты аккаунта, под которым авторизовался пользователь.
     */
    private String authorization(BankService bankService, Input input) {
        String rsl = null;
        boolean authComplete = false;
        while (!authComplete) {
            String login = input.askStr("Ваш логин: ");
            String password = input.askStr("Ваш password: ");

            BankAccount bankAccount = new BankAccount("", password, login);
            rsl = regex(String.valueOf(bankService.getRequisiteIfPresent(bankAccount)));

            System.out.println("Вы зашли под реквизитами: " + rsl);

            if (rsl.length() >= 12) {
                authComplete = true;
            } else {
                authComplete = false;
            }
        }
        return rsl;
    }

    public static String regex(String optionalString) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(optionalString);

        String result = "";

        while (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    /**
     * Печатается меню пользователя.
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
        bankService.addAccount(new BankAccount("Богдан", "1234", "5153655321456782"));
        bankService.addAccount(new BankAccount("Иван", "4321", "9876216526741253"));
        bankService.addAccount(new BankAccount("Сергей", "1010", "8563874390124545"));
        bankService.addAccount(new BankAccount("Сергей", "2525", "4149876523341237"));
        bankService.addAccount(new BankAccount("Богдан", "2121", "4149646523321237"));

        UserAction[] actions = {
                new ShowBalanceAction(),
                new TopUpBalanceAction(),
                new TransferToAction(),
                new Exit()
        };

        Input input = new ValidateInput();
        new StartUI().init(bankService, actions, input);
    }
}
