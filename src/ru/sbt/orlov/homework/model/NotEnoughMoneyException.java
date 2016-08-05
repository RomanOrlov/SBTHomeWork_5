package ru.sbt.orlov.homework.model;

public class NotEnoughMoneyException extends IllegalArgumentException{
    public NotEnoughMoneyException(String s) {
        super(s);
    }
}
