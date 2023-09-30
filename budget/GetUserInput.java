package budget;

import java.util.*;

public class GetUserInput {

    static final String[] PRINT_MENU_OPTIONS = {"0", "1", "2", "3", "4", "5", "6", "7"};
    static final String[] PRINT_PURCHASE_OPTIONS = {"1", "2", "3", "4", "5"};
    static final String[] PRINT_PURCHASE_MENU_OPTIONS = {"1", "2", "3", "4", "5", "6"};
    static final String[] PRINT_ANALYZE_OPTIONS = {"1", "2", "3", "4"};
    static final String[] PRINT_CERTAIN_TYPES_OPTIONS = {"1", "2", "3", "4"};

    static int getMenuInput() {
        while (true) {
            Main.printMenu();
            String userInput = Main.scanner.nextLine();

            if (!Arrays.asList(PRINT_MENU_OPTIONS).contains(userInput)) {
                System.out.println("Wrong input! Please input one of the available number options.\n");
            } else {
                return Integer.parseInt(userInput);
            }
        }
    }

    static int getPurchaseOptionsInput() {
        while (true) {
            Main.printPurchaseOptions();
            String userInput = Main.scanner.nextLine();

            if (!Arrays.asList(PRINT_PURCHASE_OPTIONS).contains(userInput)) {
                System.out.println("Wrong input!\n");
            } else {
                return Integer.parseInt(userInput);
            }
        }
    }

    static int getPurchaseMenuOptionsInput() {
        while (true) {
            Menu.showPurchaseMenu();
            String userInput = Main.scanner.nextLine();

            if (!Arrays.asList(PRINT_PURCHASE_MENU_OPTIONS).contains(userInput)) {
                System.out.println("Wrong integer input!\n");
            } else {
                return Integer.parseInt(userInput);
            }
        }
    }

    static float getPurchasePrice() {
        while (true) {
            System.out.println("Enter its price:");
            String price = Main.scanner.nextLine();
            if (!isPositiveNumeric(price)) {
                System.out.println("Please input a valid non-negative number!\n");
            } else {
                return Float.parseFloat(price);
            }
        }
    }

    private static boolean isPositiveNumeric(String input) {
        try {
            float number = Float.parseFloat(input);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int getAnalyzeOptionsInput() {
        while (true) {
            Main.printAnalyzeMenu();
            String userInput = Main.scanner.nextLine();

            if (!Arrays.asList(PRINT_ANALYZE_OPTIONS).contains(userInput)) {
                System.out.println("Wrong integer input!\n");
            } else {
                return Integer.parseInt(userInput);
            }
        }
    }

    static int getCertainTypeInput() {
        while (true) {
            Main.printCertainTypeMenu();
            String userInput = Main.scanner.nextLine();

            if (!Arrays.asList(PRINT_CERTAIN_TYPES_OPTIONS).contains(userInput)) {
                System.out.println("Wrong integer input!\n");
            } else {
                return Integer.parseInt(userInput);
            }
        }
    }
}
