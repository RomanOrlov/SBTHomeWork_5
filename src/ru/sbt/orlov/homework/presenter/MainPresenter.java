package ru.sbt.orlov.homework.presenter;

public interface MainPresenter {

    void handleAcceptPinButtonClick(String pin);

    void handleGetBalanceButtonClick();

    void handleWithDrawButtonClick(String amount);

    void handlePutButtonClick(String amount);

    void handleExitButtonClick();
}
