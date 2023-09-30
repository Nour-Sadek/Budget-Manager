package budget;

import java.io.*;
import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Menu menu = new Menu();

    public static void main(String[] args) {
        boolean status = true;

        while (status) {
            int userInput = GetUserInput.getMenuInput();
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
                case 7 -> analyze();
            }
        }
    }

    protected static void printMenu() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit""");
    }

    private static void addIncome() {
        System.out.println("Add income:");
        float income = scanner.nextFloat();
        menu.setIncome(income);
        System.out.println("Income was added!\n");
    }

    private static void showBalance() { System.out.println("Balance: $" + menu.getBalance() + "\n"); }

    private static void addPurchase() {

        while (true) {
            int userInput = GetUserInput.getPurchaseOptionsInput();
            System.out.println();

            if (userInput == 5) break;

            System.out.println("Enter purchase name:");
            String item = scanner.nextLine();
            float price = GetUserInput.getPurchasePrice();

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

    protected static void printPurchaseOptions() {
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

    private static void analyze() {
        while (true) {
            int userInput = GetUserInput.getAnalyzeOptionsInput();
            System.out.println();

            if (userInput == 4) break;

            switch (userInput) {
                case 1:
                    sortAllPurchases();
                    break;
                case 2:
                    sortByType();
                    break;
                case 3:
                    sortCertainType();
                    break;
            }
        }
    }

    private static void sortCertainType() {
        int userInput = GetUserInput.getCertainTypeInput();
        System.out.println();

        PurchaseType purchase = switch (userInput) {
            case 1 -> menu.getPurchases().get("Food");
            case 2 -> menu.getPurchases().get("Clothes");
            case 3 -> menu.getPurchases().get("Entertainment");
            case 4 -> menu.getPurchases().get("Other");
            default -> null;
        };

        if (purchase.isEmpty()) {
            System.out.println("The purchase list is empty!\n");
        } else {
            String className = purchase.getClass().getSimpleName();
            Map<String, Float> items = new TreeMap<>();
            Map<String, Float> purchases = purchase.getPurchases();

            for (var entry: purchases.entrySet()) {
                items.put(entry.getKey(), entry.getValue());
            }

            Map<String, Float> sortedAllItems = sortByValues(items);

            System.out.println(className + ':');
            printAllPurchases(sortedAllItems);

        }
    }

    private static void sortByType() {
        System.out.println("Types:");

        Map<String, PurchaseType> purchases = menu.getPurchases();
        Map<String, Float> purchaseTypes = new TreeMap<>();
        float total = 0;

        for (var purchaseType: purchases.entrySet()) {
            PurchaseType purchase = purchaseType.getValue();
            String className = purchase.getClass().getSimpleName();
            float purchaseTotal = purchase.calculateTotalSum();
            total += purchaseTotal;
            purchaseTypes.put(className, purchaseTotal);
        }

        for (var entry: sortByValues(purchaseTypes).entrySet()) {
            System.out.println(entry.getKey() + " - $" + String.format("%.2f", entry.getValue()));
        }

        System.out.println("Total sum: $" + String.format("%.2f", total) + "\n");
    }

    private static void sortAllPurchases() {
        if (menu.isEmpty()) {
            System.out.println("The purchase list is empty!\n");
        } else {
            Map<String, Float> allItems = new TreeMap<>();
            Map<String, PurchaseType> purchases = menu.getPurchases();

            for (var purchaseType: purchases.entrySet()) {
                PurchaseType purchase = purchaseType.getValue();
                // Check if this purchase type has items
                if (!purchase.isEmpty()) {
                    Map<String, Float> items = purchase.getPurchases();
                    for (var entry: items.entrySet()) {
                        allItems.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            Map<String, Float> sortedAllItems = sortByValues(allItems);

            System.out.println("All:");
            printAllPurchases(sortedAllItems);
        }
    }

    private static void printAllPurchases(Map<String, Float> purchases) {

        for (var entry: purchases.entrySet()) {
            System.out.println(entry.getKey() + " $" + String.format("%.2f", entry.getValue()));
        }

        System.out.println("Total sum: $" + String.format("%.2f", calculateTotalSum(purchases)) + "\n");
    }

    private static float calculateTotalSum(Map<String, Float> purchases) {
        float sum = 0;

        for (float purchase: purchases.values()) {
            sum += purchase;
        }

        return sum;
    }

    protected static void printAnalyzeMenu() {
        System.out.println("""
                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back""");
    }

    // Sort TreeMap by values rather than by keys
    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return -compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    protected static void printCertainTypeMenu() {
        System.out.println("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other""");
    }
}
