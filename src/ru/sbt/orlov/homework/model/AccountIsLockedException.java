package ru.sbt.orlov.homework.model;

public class AccountIsLockedException extends RuntimeException {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
