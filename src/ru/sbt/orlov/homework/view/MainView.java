package ru.sbt.orlov.homework.view;

public interface MainView {

    void showMessage(String title, String message, int messageType);

    void returnToPinInput();

    void goToOperations();

}
