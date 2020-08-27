package ru.skillfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        accounts.put(account.getRequisite(), account);
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
    public boolean getRequisiteIfPresent(BankAccount bankAccount) {
        Optional<BankAccount> opt = Optional.ofNullable(null);
        for (Map.Entry<String, BankAccount> entry:
             accounts.entrySet()) {
            if (entry.getValue().equals(bankAccount)) {
                opt = Optional.ofNullable(bankAccount);
            }
        }

        if (opt.isPresent()) {
            opt.get().setAccept(true);
            return opt.get().getAccept();
        } else {
            return opt.get().getAccept();
        }
    }

    /**
     * Метод кол-во средств на передаваемых реквизитах. На этом методе вам нужно выкидывать исключение,
     * если передаваемые реквизиты не валидны, это единственный способ сообщить о проблеме.
     *
     * @param requisite реквизиты, строка в произвольном формате.
     * @return кол-во средств в копейках (для других валют аналогично было бы).
     */
    public long balance(String requisite) {
        return 0;
    }

    /**
     * Метод должен пополнять баланс.
     *
     * @param requisite реквизиты, строка в произвольном формате.
     * @param amount сумма для пополнения.
     * @return возвращает true если баланс был увеличен.
     */
    public boolean topUpBalance(String requisite, long amount) {
        return false;
    }

    /**
     * Метод, если все условия соблюдены, переводит средства с одного счёта на другой.
     *
     * @param username строка в произвольном формате.
     * @param password строка в произвольном формате.
     * @param srcRequisite реквизиты, строка в произвольном формате.
     * @param destRequisite реквизиты, строка в произвольном формате.
     * @param amount кол-во средств в копейках (для других валют аналогично было бы).
     * @return true если выполнены все условия, средства фактически переведены.
     */
    public boolean transferMoney(String username, String password, String srcRequisite,
                                 String destRequisite, long amount) {
        boolean rsl = false;
        return rsl;
    }
}
