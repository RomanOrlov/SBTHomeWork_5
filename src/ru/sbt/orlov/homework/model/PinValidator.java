package ru.sbt.orlov.homework.model;

public class PinValidator {
    public void validate(String pin) {
        if (!pin.equals("42"))
            throw new PinValidationException("Пин код неверен!");
    }
}
