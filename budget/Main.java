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
                case 0:
                    System.out.println("Bye!");
                    status = false;
                    break;
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    showPurchases();
                    break;
                case 4:
                    showBalance();
                    break;
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
        System.out.println("Enter purchase name:");
        scanner.nextLine();
        String item = scanner.nextLine();
        System.out.println("Enter its price:");
        float price = scanner.nextFloat();
        menu.addPurchase(item, price);
        System.out.println("Purchase was added!\n");
    }

    private static void showPurchases() {
        menu.showPurchases();
    }
}
