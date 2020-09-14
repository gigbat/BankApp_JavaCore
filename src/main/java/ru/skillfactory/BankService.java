package ru.skillfactory;

import java.util.*;

/**
 * BankService - класс, который нарушает принцип единственной ответственности. У нас он сразу
 * и хранит аккаунты, и реализует логику проверки баланса и переводов. Можете использовать
 * его в текущем виде, можете решить проблему множественной ответственности и создать интерфейс
 * AccountStore, написать его реализации и в BankService передавать хранилище. В этом случае в
 * этом классе должна быть только логика переводов + баланс и вы просто обращаетесь в store, передовая
 * ответ на уровень выше.
 */
public class BankService {
    /**
     * В Map-е храните аккаунты, ключ это реквизиты
     */
    private Map<String, BankAccount> accounts = new HashMap<>();


    public void addAccount(BankAccount account) {
        accounts.putIfAbsent(account.getRequisite(), account);
    }

    /**
     * Метод проверяет что в Map-е есть аккаунт, если есть вернёт реквезиты. В моей реализации
     * метод просто вернёт реквезиты без генерации исключений.
     *
     * @param bankAccount валидный аккаунт.
     * @return возвращает объект Optional, который будет содержат строку - requisite,
     * если передоваемого пользователя нету или пароль не совпадает вы сможете
     * передать пустой объект Optional и проверить что он не пуст.
     */
    public Optional<String> getRequisiteIfPresent(BankAccount bankAccount) {
        Optional<String> opt;
        BankAccount account = accounts.get(bankAccount.getRequisite());
        if (account != null) {
            opt = Optional.ofNullable(bankAccount.getRequisite());
        } else {
            opt = Optional.empty();
        }
        return opt;
    }

    /**
     * Метод кол-во средств на передаваемых реквизитах. На этом методе вам нужно выкидывать исключение,
     * если передаваемые реквизиты не валидны, это единственный способ сообщить о проблеме.
     *
     * @param amount передаваемый счёт, строка в произвольном формате.
     * @return кол-во средств в копейках (для других валют аналогично было бы).
     */
    public long balance(long amount) {
        return amount;
    }

    /**
     * Метод должен пополнять баланс.
     *
     * @param requisite реквизиты, строка в произвольном формате.
     * @param amount    сумма для пополнения.
     * @return возвращает true если баланс был увеличен.
     */
    public boolean topUpBalance(String requisite, long amount) {
        boolean topUp = false;
        BankAccount bankAccount = accounts.get(requisite);

        if (bankAccount != null) {
            bankAccount.setBalance(amount);
            topUp = true;
        }

        return topUp;
    }

    /**
     * Метод, если все условия соблюдены, переводит средства с одного счёта на другой.
     *
     * @param username      строка в произвольном формате.
     * @param password      строка в произвольном формате.
     * @param srcRequisite  реквизиты, строка в произвольном формате.
     * @param destRequisite реквизиты, строка в произвольном формате.
     * @param amount        кол-во средств в копейках (для других валют аналогично было бы).
     * @return true если выполнены все условия, средства фактически переведены.
     */
    public boolean transferMoney(String username, String password, String srcRequisite,
                                 String destRequisite, long amount) {
        boolean rsl = false;
        BankAccount srcAccount = accounts.get(srcRequisite);
        BankAccount destAccount = accounts.get(destRequisite);

        if (srcAccount != null && srcAccount.getPassword().equals(password)) {
            if (destAccount != null && destAccount.getUsername().equals(username)) {
                destAccount.setBalance(amount);
                rsl = true;
            } else {
                throw new NullPointerException();
            }
        }

        return rsl;
    }

    public long getBalanceForAccount(String requisites) {
        BankAccount bankAccount = accounts.get(requisites);
        return bankAccount.getBalance();
    }

    public void showTest() {
        for(BankAccount bankAccount : accounts.values()) {
            System.out.println(bankAccount.getRequisite() + "\t" + bankAccount.getPassword() + "\t" + bankAccount.getUsername());
        }
    }
}
