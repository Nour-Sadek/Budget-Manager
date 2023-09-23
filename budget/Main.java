package budget;

import java.io.*;
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
                case 5 -> savePurchases();
                case 6 -> loadPurchases();
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
                5) Save
                6) Load
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
                case 1 -> {
                    purchase = menu.getPurchases().get("Food");
                    purchase.addPurchase(item, price);
                }
                case 2 -> {
                    purchase = menu.getPurchases().get("Clothes");
                    purchase.addPurchase(item, price);
                }
                case 3 -> {
                    purchase = menu.getPurchases().get("Entertainment");
                    purchase.addPurchase(item, price);
                }
                case 4 -> {
                    purchase = menu.getPurchases().get("Other");
                    purchase.addPurchase(item, price);
                }
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

    private static void savePurchases() {
        File file = new File("purchases.txt");
        // overwrite the file if already exists or create it
        try (FileWriter writer = new FileWriter(file, false)) {
            // Write in the income
            writer.write("income: " + menu.getIncome() + "\n\n");
            // Write the value for each item in the menu
            Map<String, PurchaseType> purchases = menu.getPurchases();
            for (var purchaseType: purchases.entrySet()) {
                String type = purchaseType.getKey();
                PurchaseType purchase = purchaseType.getValue();
                // Check if this purchase type has items
                if (!purchase.isEmpty()) {
                    Map<String, Float> items = purchase.getPurchases();
                    for (var entry: items.entrySet()) {
                        String name = entry.getKey();
                        Float price = entry.getValue();
                        // Write to the file
                        writer.write("item:\n");
                        writer.write("type: " + type + "\n");
                        writer.write("name: " + name + "\n");
                        writer.write("price: " + price + "\n\n");
                    }
                }
            }
            System.out.println("Purchases were saved!\n");
        } catch (IOException e) {
            System.out.printf("An exception occured %s", e.getMessage());
        }
    }

    private static void loadPurchases() {
        File file = new File("purchases.txt");
        try (Scanner reader = new Scanner(file)) {
            // Read the income
            String incomeLine = reader.nextLine();
            float income = Float.parseFloat(incomeLine.substring(8));
            menu.setIncome(income);
            // Reading the items
            while (reader.hasNext()) {
                String item = reader.nextLine();
                if (item.isEmpty()) continue;
                // read the type
                String typeLine = reader.nextLine();
                String type = typeLine.substring(6);
                // read the name
                String nameLine = reader.nextLine();
                String name = nameLine.substring(6);
                // read the price
                String priceLine = reader.nextLine();
                float price = Float.parseFloat(priceLine.substring(7));
                // Save the item in <menu>
                menu.addPurchase(type, name, price);
            }
            System.out.println("Purchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("No file found: purchases.txt");
        }
    }
}
