package ru.sbt.orlov.homework.presenter;

import ru.sbt.orlov.homework.model.*;
import ru.sbt.orlov.homework.view.MainView;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MainPresenterImpl implements MainPresenter {

    private final Terminal terminal = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();
    private final MainView mainView;
    private boolean isLocked;
    private LocalTime lockedTo = LocalTime.now();
    private int attempts;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void handleAcceptPinButtonClick(String pin) {
        if (!isLocked||LocalTime.now().isAfter(lockedTo)) {
            try {
                isLocked = false;
                pinValidator.validate(pin);
                mainView.goToOperations();
            } catch (PinValidationException ex) {
                mainView.showMessage("Опаньки...", "Неправильный пинкод", 0);
                attempts++;
                if (attempts == 3) {
                    isLocked = true;
                    attempts = 0;
                    lockedTo = LocalTime.now().plus(12, ChronoUnit.SECONDS);
                }
            }
        } else {
            mainView.showMessage("Вы заблокированы до: "+lockedTo.toString(),"Превышено допустимое количество попыток",0);
        }
    }

    @Override
    public void handleGetBalanceButtonClick() {
        try {
            Long longAmount = terminal.balance();
            mainView.showMessage("Баланс","Ваш баланс "+longAmount,1);
        } catch (ConnectionTroubleException ex) {
            mainView.showMessage("Опаньки...","Проблемы с соединением!\nСпасибо что пользуетесь услугами интердегрессбанка!",0);
        }
    }

    @Override
    public void handleWithDrawButtonClick(String amount) {
        try {
            Long longAmount = Long.parseLong(amount);
            if (longAmount%100!=0||longAmount<0) {
                mainView.showMessage("Неверный ввод суммы","Сумма должна быть кратна 100",0);
            }
            terminal.withdraw(longAmount);
        } catch (NumberFormatException ex) {
            mainView.showMessage("Введите число","Неверный формат ввода суммы",2);
        } catch (ConnectionTroubleException ex) {
            mainView.showMessage("Опаньки...","Проблемы с соединением!\nСпасибо что пользуетесь услугами интердегрессбанка!",0);
        } catch (NotEnoughMoneyException ex) {
            mainView.showMessage("Недостаточно средств","У вас денег нет. А у нас есть.",2);
        }
    }

    @Override
    public void handlePutButtonClick(String amount) {
        try {
            Long longAmount = Long.parseLong(amount);
            if (longAmount%100!=0||longAmount<0) {
                mainView.showMessage("Неверный ввод суммы","Сумма должна быть кратна 100",0);
            }
            terminal.put(longAmount);
        } catch (NumberFormatException ex) {
            mainView.showMessage("Введите число","Неверный формат ввода суммы",2);
        }
    }

    @Override
    public void handleExitButtonClick() {
        mainView.returnToPinInput();
    }
}
