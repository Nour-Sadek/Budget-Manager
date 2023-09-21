package budget;

import java.util.*;

class Menu {
    private float income;
    private Map<String, PurchaseType> purchases;
    static Scanner scanner = new Scanner(System.in);

    Menu() {
        this.purchases = new HashMap<>();
        this.purchases.put("Food", new Food());
        this.purchases.put("Clothes", new Clothes());
        this.purchases.put("Entertainment", new Entertainment());
        this.purchases.put("Other", new Other());
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

    public Map<String, PurchaseType> getPurchases() {
        return this.purchases;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    private float calculateTotalSum() {
        float sum = 0;

        for (PurchaseType purchase: this.purchases.values()) {
            sum += purchase.calculateTotalSum();
        }

        return sum;
    }

    public void showPurchases() {
        if (this.isEmpty()) {
            System.out.println("The purchase list is empty!");
        } else {
            boolean status = true;
            while (status) {
                showPurchaseMenu();
                int userInput = scanner.nextInt();
                System.out.println();

                PurchaseType purchase;

                switch (userInput) {
                    case 1 -> {
                        purchase = this.purchases.get("Food");
                        purchase.showPurchases();
                    }
                    case 2 -> {
                        purchase = this.purchases.get("Clothes");
                        purchase.showPurchases();
                    }
                    case 3 -> {
                        purchase = this.purchases.get("Entertainment");
                        purchase.showPurchases();
                    }
                    case 4 -> {
                        purchase = this.purchases.get("Other");
                        purchase.showPurchases();
                    }
                    case 5 -> this.printAllPurchases();
                    case 6 -> {
                        status = false;
                        System.out.println();
                    }
                }
            }
        }
    }

    private void printAllPurchases() {
        System.out.println("All:");

        for (PurchaseType purchase: this.purchases.values()) {
            for (var entry: purchase.purchases.entrySet()) {
                System.out.println(entry.getKey() + " $" + entry.getValue());
            }
        }

        System.out.println("Total sum: $" + this.calculateTotalSum() + "\n");
    }

    private static void showPurchaseMenu() {
        System.out.println("""
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) All
            6) Back""");
    }

    protected boolean isEmpty() {
        boolean verdict = true;

        for (PurchaseType purchase: this.purchases.values()) if (!purchase.isEmpty()) verdict = false;

        return verdict;
    }

}
