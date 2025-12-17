package task1;

public class Bank {
    public void sendMoney(Account from, Account to, int amount) {
        Account lockFirst = from.hashCode() < to.hashCode() ? from : to;
        Account lockSecond = from.hashCode() < to.hashCode() ? to : from;
        synchronized (lockFirst) {
            synchronized (lockSecond) {
                if (from.takeMoney(amount)) {
                    to.addMoney(amount);
                } else {
                    System.out.println("Не вистачає грошей на рахунку!");
                }
            }
        }
    }
}