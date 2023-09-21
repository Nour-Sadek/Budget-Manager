package budget;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Menu menu = new Menu();

    public static void main(String[] args) {
        // write your code here
        boolean status = true;

        while (status) {
            printMenu();
            int userInput = scanner.nextInt();
            System.out.println();

            switch (userInput) {
                case 0 -> {
                    System.out.println("Bye!");
                    status = false;
                }
                case 1 -> addIncome();
                case 2 -> addPurchase();
                case 3 -> showPurchases();
                case 4 -> showBalance();
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            0) Exit""");
    }

    private static void addIncome() {
        System.out.println("Add income:");
        float income = scanner.nextFloat();
        menu.setIncome(income);
        System.out.println("Income was added!\n");
    }

    private static void showBalance() {
        System.out.println("Balance: $" + menu.getBalance() + "\n");
    }

    private static void addPurchase() {

        while (true) {
            printPurchaseOptions();
            int userInput = scanner.nextInt();
            System.out.println();

            if (userInput == 5) {
                System.out.println();
                break;
            }

            System.out.println("Enter purchase name:");
            scanner.nextLine();
            String item = scanner.nextLine();
            System.out.println("Enter its price:");
            float price = scanner.nextFloat();

            PurchaseType purchase;

            switch (userInput) {
                case 1:
                    purchase = menu.getPurchases().get("Food");
                    purchase.addPurchase(item, price);
                    break;
                case 2:
                    purchase = menu.getPurchases().get("Clothes");
                    purchase.addPurchase(item, price);
                    break;
                case 3:
                    purchase = menu.getPurchases().get("Entertainment");
                    purchase.addPurchase(item, price);
                    break;
                case 4:
                    purchase = menu.getPurchases().get("Other");
                    purchase.addPurchase(item, price);
                    break;
            }

            System.out.println("Purchase was added!\n");
        }

    }

    private static void printPurchaseOptions() {
        System.out.println("""
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) Back""");
    }

    private static void showPurchases() {
        menu.showPurchases();
    }
}
