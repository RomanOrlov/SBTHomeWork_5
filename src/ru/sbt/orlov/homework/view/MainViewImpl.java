package ru.sbt.orlov.homework.view;

import ru.sbt.orlov.homework.presenter.MainPresenter;
import ru.sbt.orlov.homework.presenter.MainPresenterImpl;

import javax.swing.*;

public class MainViewImpl extends JFrame implements MainView {

    private final MainPresenter presenter;
    private JPanel pinInsert = new JPanel();
    private JPanel operations = new JPanel();

    public MainViewImpl() {
        super("Банкомат на swing, - лишись своего обеда!");
        this.presenter = new MainPresenterImpl(this);
        setVisible(true);
        setSize(450,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initPinInsertPane();
        initOperationsPane();
        setContentPane(pinInsert);
    }

    private void initOperationsPane() {
        operations.setSize(450,200);
        Box content = Box.createVerticalBox();
        JLabel info = new JLabel("Ты деньга вводить, я выдавать");
        info.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField input = new JTextField();
        input.setHorizontalAlignment(SwingConstants.CENTER);

        JButton withdraw = new JButton("Снять");
        withdraw.setHorizontalAlignment(SwingConstants.CENTER);
        withdraw.addActionListener(action -> {
            presenter.handleWithDrawButtonClick(input.getText());
            input.setText("");
        });
        JButton put = new JButton("Положить");
        put.setHorizontalAlignment(SwingConstants.CENTER);
        put.addActionListener(action -> {
            presenter.handlePutButtonClick(input.getText());
            input.setText("");
        });
        JButton getBalance = new JButton("Баланс");
        getBalance.setHorizontalAlignment(SwingConstants.CENTER);
        getBalance.addActionListener(action -> presenter.handleGetBalanceButtonClick());

        JButton exit = new JButton("Выйти из системы");
        exit.setHorizontalAlignment(SwingConstants.LEFT);
        exit.addActionListener(action -> {
            input.setText("");
            presenter.handleExitButtonClick();
            setContentPane(pinInsert);
        });
        Box buttons = Box.createHorizontalBox();
        buttons.add(withdraw);
        buttons.add(put);
        buttons.add(getBalance);
        content.add(info);
        content.add(input);
        content.add(buttons);
        content.add(exit);
        operations.add(content);
    }

    private void initPinInsertPane() {
        pinInsert.setSize(450,200);
        Box vertBox = Box.createVerticalBox();
        JLabel info = new JLabel("Введите пинкод (Чтобы что то работало введите 42)");
        info.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField pinInput = new JTextField();
        pinInput.setHorizontalAlignment(SwingConstants.CENTER);
        JButton acceptPin = new JButton("Ввести пинкод");
        acceptPin.setHorizontalAlignment(SwingConstants.CENTER);
        vertBox.add(info);
        vertBox.add(pinInput);
        vertBox.add(acceptPin);
        pinInsert.add(vertBox);
        acceptPin.addActionListener(action -> {
            presenter.handleAcceptPinButtonClick(pinInput.getText());
            pinInput.setText("");
        });
    }

    @Override
    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(this,message,title, messageType);
    }

    @Override
    public void goToOperations() {
        setContentPane(operations);
    }

    @Override
    public void returnToPinInput() {
        setContentPane(pinInsert);
    }
}