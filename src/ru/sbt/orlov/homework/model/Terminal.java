package ru.sbt.orlov.homework.model;

public interface Terminal {

    long balance() throws ConnectionTroubleException;

    void withdraw(long sum) throws NotEnoughMoneyException,ConnectionTroubleException;

    void put(long sum) throws ConnectionTroubleException;
}
