package ru.skillfactory;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * BankService - класс, который хранит аккаунты, и реализует логику проверки баланса и переводов.
 */
public class BankService {
    /**
     * В Map-е хранятся аккаунты пользователей банка.
     */
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.putIfAbsent(account.getRequisite(), account);
    }

    /**
     * Метод проверяет что в Map-е есть аккаунт, если есть вернёт реквезиты.
     *
     * @param bankAccount валидный аккаунт.
     * @return возвращает объект Optional, который будет содержат строку - requisite.
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
     * Метод пополнения баланса.
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
     * @param amount        кол-во средств в копейках.
     * @return true если выполнены все условия.
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
