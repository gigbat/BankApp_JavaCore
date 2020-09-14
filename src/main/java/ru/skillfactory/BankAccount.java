package ru.skillfactory;

import java.util.Objects;

/**
 * Модель данных для работы с пользователем и его счётом.
 */
public class BankAccount {
    private String username;
    private String password;
    private long balance;
    private final String requisite;

    /**
     * Конструктор для создания аккаунта определённого пользователя.
     *
     * @param username Строка в произвольной форме.
     * @param password Строка в произвольной форме.
     * @param requisite Строка в произвольной форме.
     */
    public BankAccount(String username, String password, String requisite) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.requisite = requisite;
    }

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance += balance;
    }

    public String getRequisite() {
        return requisite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankAccount that = (BankAccount) o;
        if (!Objects.equals(password, that.password)) {
            return false;
        }
        return Objects.equals(requisite, that.requisite);
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (requisite != null ? requisite.hashCode() : 0);
        return result;
    }
}
