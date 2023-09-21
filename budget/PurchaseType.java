package budget;

import java.util.*;

abstract class PurchaseType {
    protected Map<String, Float> purchases;

    protected PurchaseType() {
        this.purchases = new LinkedHashMap<>();
    }

    protected void addPurchase(String item, float price) {
        this.purchases.put(item, price);
    }

    protected void showPurchases() {
        printType();
        if (this.isEmpty()) {
            System.out.println("The purchase list is empty!\n");
        } else {
            for (var entry: this.purchases.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }

            System.out.println("Total sum: $" + this.calculateTotalSum() + "\n");
        }
    }

    abstract void printType();

    protected float calculateTotalSum() {
        float sum = 0;

        for (float price: this.purchases.values()) {
            sum += price;
        }

        return sum;
    }

    protected boolean isEmpty() {
        return this.purchases.isEmpty();
    }
}

class Food extends PurchaseType {

    protected Food() {
        super();
    }

    void printType() {
        System.out.println("Food:");
    }

}

class Clothes extends PurchaseType {

    protected Clothes() {
        super();
    }

    void printType() {
        System.out.println("Clothes:");
    }

}

class Entertainment extends PurchaseType {

    protected Entertainment() {
        super();
    }

    void printType() {
        System.out.println("Entertainment:");
    }

}

class Other extends PurchaseType {

    protected Other() {
        super();
    }

    void printType() {
        System.out.println("Other:");
    }

}

