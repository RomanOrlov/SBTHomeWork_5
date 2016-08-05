package ru.sbt.orlov.homework.model;

public class TerminalServer implements Terminal {
    long balance = 100 * (long)(100 * Math.random());

    @Override
    public long balance() throws ConnectionTroubleException{
        if (Math.random()>0.5d) {
            throw new ConnectionTroubleException("Проблемы с соединением");
        }
        return balance;
    }

    @Override
    public void withdraw(long sum) throws NotEnoughMoneyException,ConnectionTroubleException{
        if (balance<sum) {
            throw new NotEnoughMoneyException("Недостаточно средств!");
        }
        balance-=sum;
    }

    @Override
    public void put(long sum) throws ConnectionTroubleException{
        // Да-да, а тут проблем никаких не будет. Как жрать деньги, - так мы всегда молодцы.
        balance+=sum;
    }
}