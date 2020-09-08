package ru.skillfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

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

    /**
     * Метод добавляете аккаунт в Map-у если у аккаунта уникальные реквизиты (логин тоже нужно проверить,
     * чтобы корректно работал метод contains).
     *
     * @param account Аккаунт с заполненными полями.
     */
    public void addAccount(BankAccount account) {
        if (accounts.size() == 0) {
            accounts.put(account.getRequisite(), account);
        } else {
            for (BankAccount entry : accounts.values()) {
                if (entry.getRequisite() == account.getRequisite()) {
                    System.out.println("Пользователь с такими реквизитами уже существует.");
                } else {
                    accounts.put(account.getRequisite(), account);
                }
            }
        }
    }

    /**
     * Метод проверяет что в Map-е есть аккаунт, если есть вернёт реквезиты. В моей реализации
     * метод просто вернёт реквезиты без генерации исключений.
     *
     * @param bankAccount валидный аккаунт.
     * @return возвращает объект Optional, который будет содержат строку - requisite,
     *         если передоваемого пользователя нету или пароль не совпадает вы сможете
     *         передать пустой объект Optional и проверить что он не пуст.
     */
    public Optional<String> getRequisiteIfPresent(BankAccount bankAccount) {
        Optional<BankAccount> opt = Optional.empty();
        for (BankAccount entry:
             accounts.values()) {
            if (entry.equals(bankAccount)) {
                opt = Optional.ofNullable(bankAccount);
            }
        }

        return Optional.ofNullable(opt.get().getRequisite());
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
     * @param amount сумма для пополнения.
     * @return возвращает true если баланс был увеличен.
     */
    public boolean topUpBalance(String requisite, long amount) {
        boolean topUp = false;

        for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
            if (entry.getKey().equals(requisite)) {
                entry.getValue().setBalance(amount);
                topUp = true;
                break;
            }
        }
        return topUp;
    }

    /**
     * Метод, если все условия соблюдены, переводит средства с одного счёта на другой.
     *
     * @param srcRequisite реквизиты, строка в произвольном формате.
     * @return true если выполнены все условия, средства фактически переведены.
     */
    public boolean transferMoney(String srcRequisite) {
        boolean rsl = false;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите реквизиты получателя: ");
        long destRequisite = Long.parseLong(scanner.nextLine());

        System.out.print("Введите сумму пополнения: ");
        long amount = Long.parseLong(scanner.nextLine());

        for (Map.Entry<String, BankAccount> srcEntry : accounts.entrySet()) {
            if (srcEntry.getKey().equals(srcRequisite)) {
                for (Map.Entry<String, BankAccount> dstEntry : accounts.entrySet()) {
                    if (dstEntry.getKey().equals(balance(destRequisite))) {
                        dstEntry.getValue().setBalance(amount);
                        rsl = true;
                        break;
                    }
                }
            }
        }
        return rsl;
    }

    public Map<String, BankAccount> getAccounts() {
        return accounts;
    }
}
