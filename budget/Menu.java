package budget;

import java.util.*;

class Menu {
    private float income;
    private Map<String, Float> purchases;

    Menu() {
        this.purchases = new LinkedHashMap<>();
        this.income = 0;
    }

    public float getIncome() {
        return this.income;
    }

    public float getBalance() {
        float totalSum = this.calculateTotalSum();

        if (totalSum <= this.income) return this.income - totalSum;
        else return 0;
    }

    public Map<String, Float> getPurchases() {
        return this.purchases;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    protected void addPurchase(String item, float price) {
        this.purchases.put(item, price);
    }

    protected void showPurchases() {
        if (this.purchases.isEmpty()) {
            System.out.println("The purchase list is empty\n");
        } else {
            for (var entry: this.purchases.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }

            System.out.println("Total sum: $" + this.calculateTotalSum() + "\n");
        }
    }

    private float calculateTotalSum() {
        float sum = 0;

        for (float price: this.purchases.values()) {
            sum += price;
        }

        return sum;
    }
}
