package task1;

public class Account {
    private int money;

    public Account(int startMoney) {
        this.money = startMoney;
    }

    public Account() {
        this.money = 0;
    }

    public boolean takeMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int getMoney() {
        return money;
    }
}