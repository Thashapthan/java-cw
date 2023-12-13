import java.io.*;
import java.util.*;

public class FoodCenter {

    static String[] cashierOneNames = new String[2]; //array to store customer names
    static String[] cashierTwoNames = new String[3]; //array to store customer names
    static String[] cashierThreeNames = new String[5]; //array to store customer names
    static String[] cashierOne = new String[2]; //first cashier who can hold maximum of 2 customers
    static String[] cashierTwo = new String[3]; //second cashier who can hold maximum of 3 customers
    static String[] cashierThree = new String[5]; //third cashier who can hold maximum of 5 customers

    static int burgerStocks = 50; //initial burger stock is 50

    public static void main(String[] args) {

        String[] menuOptions = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "999", "VFQ", "VEQ", "ACQ", "RCQ", "PCQ", "VCS", "SPD", "LPD", "STK", "AFS", "EXT"};
        List<String> menuSelection = Arrays.asList(menuOptions);

        // X in the cashier arrays represents the not occupied queues
        // O in the cashier arrays represents the occupied queues
        // in the beginning no queues are occupied. so all arrays are filled with X
        Arrays.fill(cashierOne, "X"); //adding all elements as X
        Arrays.fill(cashierTwo, "X"); //adding all elements as X
        Arrays.fill(cashierThree, "X"); //adding all elements as X
        Arrays.fill(cashierOneNames, null); //adding all elements as X
        Arrays.fill(cashierTwoNames, null); //adding all elements as X
        Arrays.fill(cashierThreeNames, null); //adding all elements as X

        Scanner input = new Scanner(System.in); //creates input to get user input

        label:
        while (true) {
            System.out.println("\nWelcome to the Foodies Fave Food center"); //displays welcome message


            String menuOption; //creating an integer variable to store and use the user's menu selection

            while (true) {

                //display warning message when the burger stock is less than 10
                if (burgerStocks <= 10) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Warning: Low stock! Only " + burgerStocks + " burgers left.");
                    System.out.println("-------------------------------------------------");
                }

                //printing the menu screen
                System.out.println("-------------------------------------------------");
                System.out.println("Please select an option from a console menu: ");
                System.out.println("100 or VFQ: View all Queues");
                System.out.println("101 or VEQ: View all Empty Queues");
                System.out.println("102 or ACQ: Add customer to a Queue");
                System.out.println("103 or RCQ: Remove a customer from a Queue");
                System.out.println("104 or PCQ: Remove a served customer");
                System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
                System.out.println("106 or SPD: Store Program Data into file");
                System.out.println("107 or LPD: Load Program Data from file");
                System.out.println("108 or STK: View Remaining burgers Stock");
                System.out.println("109 or AFS: Add burgers to Stock");
                System.out.println("999 or EXT: Exit the Program");
                System.out.println("-------------------------------------------------");
                System.out.print("Select an Option from console menu: ");


                //to validate the user input.
                //the user is only required to give specific inputs. which are predefined in menuOptions array
                //otherwise it'll show an error message

                String menuOptionRaw = input.next(); //getting user input for menu selection
                menuOption = menuOptionRaw.toUpperCase(); //converting the input to uppercase

                // checks whether the input is predefined
                if (menuSelection.contains(menuOption)) {
                    break; //if the input is predefined. the loop for user input will break
                } else {
                    System.out.println("Invalid option. Please try again."); //error message for invalid input
                }
            }



            // selection menu.
            switch (menuOption.toUpperCase()) {
                case "100":
                case "VFQ":
                    view_all_queues(); //view all queues
                    break;

                case "101":
                case "VEQ":
                    view_empty_queues(); //view all empty queues
                    break;

                case "102":
                case "ACQ":
                    add_customer(input); //add a customer to a queue
                    break;

                case "103":
                case "RCQ":
                    remove_customer(input); //remove a customer from a queue
                    break;

                case "104":
                case "PCQ":
                    remove_served_customer(input); //remove a served customer
                    break;

                case "105":
                case "VCS":
                    sorted_customers(); //sort the customers in alphabetical order
                    break;

                case "106":
                case "SPD":
                    store_customer_data(); //store to file
                    break;

                case "107":
                case "LPD":
                    load_customer_data(); //load from file
                    break;

                case "108":
                case "STK":
                    view_remaining_burger_stocks(); //view remaining burger stock
                    break;

                case "109":
                case "AFS":
                    add_burger_stocks(input); //add burger to stock
                    break;

                default:  //if the user input is 999 or EXT
                    System.out.println("Program is exiting..."); //exit message
                    break label; //it'll exit the program
            }
        }
    }

    //method to view all queues
    public static void view_all_queues() {

        //displaying the message
        System.out.println("\n*****************");
        System.out.println("*    Cashiers   *");
        System.out.println("*****************");

        //printing the queues
        for (int i = 0; i < cashierThree.length; i++) {
            if (i < cashierOne.length) {
                System.out.print("  " + cashierOne[i]);
            } else {
                System.out.print("   ");
            }

            if (i < cashierTwo.length) {
                System.out.print("     " + cashierTwo[i]);
            } else {
                System.out.print("      ");
            }

            System.out.print("     " + cashierThree[i]);
            System.out.println();
        }

        //displaying the message
        System.out.println("*****************");
        System.out.println("X - not occupied");
        System.out.println("O - occupied");

        if (cashierOneNames[0] != null) {
            System.out.println("\nCashier 1: " + Arrays.toString(cashierOneNames));
        } else {
            System.out.println("\nCashier 1: Empty");
        }

        if (cashierTwoNames[0] != null) {
            System.out.println("Cashier 2: " + Arrays.toString(cashierTwoNames));
        } else {
            System.out.println("Cashier 2: Empty");
        }

        if (cashierThreeNames[0] != null) {
            System.out.println("Cashier 3: " + Arrays.toString(cashierThreeNames));
        } else {
            System.out.println("Cashier 3: Empty");
        }
    }

    //method to view all empty queues
    public static void view_empty_queues() {
        boolean cashierOneEmpty = true;
        boolean cashierTwoEmpty = true;
        boolean cashierThreeEmpty = true;

        for (int i = 0; i < cashierOne.length; i++) {
            if (cashierOne[i].equals("O")) {
                cashierOneEmpty = false;
                break;
            }
        } //checking whether the cashier one is empty

        for (int i = 0; i < cashierTwo.length; i++) {
            if (cashierTwo[i].equals("O")) {
                cashierTwoEmpty = false;
                break;
            }
        } //checking whether the cashier two is empty

        for (int i = 0; i < cashierThree.length; i++) {
            if (cashierThree[i].equals("O")) {
                cashierThreeEmpty = false;
                break;
            }
        } //checking whether the cashier three is empty

        if (cashierOneEmpty) {
            System.out.println("Cashier 1 is empty");
        }

        if (cashierTwoEmpty) {
            System.out.println("Cashier 2 is empty");
        }

        if (cashierThreeEmpty) {
            System.out.println("Cashier 3 is empty");
        }

        if (!cashierOneEmpty && !cashierTwoEmpty && !cashierThreeEmpty) {
            System.out.println("All cashiers are not empty");
        }

    }

    //method to add a customer to a queue
    public static void add_customer(Scanner input) {

        //getting the customer name
        System.out.print("\nEnter customer name: ");
        String name = input.next();

        //getting the cashier number
        System.out.print("Enter cashier number: ");
        int cashierNumber;
        try {
            cashierNumber = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again."); //error message for invalid input
            return;
        }

        //checking whether the cashier number is valid
        if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {

            if (cashierNumber == 1) {
                boolean cashierOneFilled = true;
                for (int i = 0; i < cashierOne.length; i++) {
                    if (cashierOne[i].equals("X")) {
                        cashierOne[i] = "O";
                        System.out.println("customer added to cashier 1 successfully");
                        burgerStocks -= 5; //deducting the burger stock
                        cashierOneNames[i] = name;
                        cashierOneFilled = false; //if the queue is not filled
                        break;
                    }
                }
                if (cashierOneFilled) {
                    System.out.println("Sorry the required queue is filled, please find another "); //error message for filled queue
                }
            } else if (cashierNumber ==2) {
                boolean cashierTwoFilled = true;
                for (int i = 0; i < cashierTwo.length; i++) {
                    if (cashierTwo[i].equals("X")) {
                        cashierTwo[i] = "O";
                        System.out.println("customer added to cashier 2 successfully");
                        burgerStocks -= 5; //deducting the burger stock
                        cashierTwoNames[i] = name;
                        cashierTwoFilled = false; //if the queue is not filled
                        break;
                    }
                }
                if (cashierTwoFilled) {
                    System.out.println("Sorry the required queue is filled, please find another "); //error message for filled queue
                }
            } else {
                boolean cashierThreeFilled = true;
                for (int i = 0; i < cashierThree.length; i++) {
                    if (cashierThree[i].equals("X")) {
                        cashierThree[i] = "O";
                        System.out.println("customer added to cashier 3 successfully");
                        burgerStocks -= 5; //deducting the burger stock
                        cashierThreeNames[i] = name;
                        cashierThreeFilled = false; //if the queue is not filled
                        break;
                    }
                }
                if (cashierThreeFilled) {
                    System.out.println("Sorry the required queue is filled, please find another "); //error message for filled queue
                }
            }
        } else {
            System.out.println("Invalid cashier number. Please try again."); //error message
        }
    }

    //method to remove a customer from a queue
    public static void remove_customer(Scanner input) {

        //getting the cashier number
        System.out.print("Enter cashier number: ");
        int cashierNumber;
        try {
            cashierNumber = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again."); //error message for invalid input
            return;
        }

        //checking whether the cashier number is valid
        if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {
            //checking whether the cashier is empty
            if (cashierNumber == 1 && cashierOne[0].equals("X")) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 2 && cashierTwo[0].equals("X")) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 3 && cashierThree[0].equals("X")) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else {
                int specific_place = 99;
                //getting the customer specific place
                System.out.print("Enter customer specific place: ");
                try {
                    specific_place = input.nextInt();
                    specific_place -= 1;
                } catch (InputMismatchException e) {
                    //error message
                }

                input.nextLine();


                //checking whether the specific place is valid
                if (cashierNumber == 1) {
                    if (specific_place <= cashierOne.length) {
                        if (cashierOne[specific_place].equals("O")) {
                            cashierOne[specific_place] = "X";
                            for(int i = specific_place; i < cashierOne.length - 1; i++) {
                                cashierOne[i] = cashierOne[i + 1];
                            }
                            cashierOne[cashierOne.length-1] = "X";
                            cashierOneNames[specific_place] = null;
                            for (int i = specific_place; i < cashierOneNames.length - 1; i++) {
                                cashierOneNames[i] = cashierOneNames[i + 1];
                            }
                            cashierOneNames[cashierOneNames.length-1] = null;
                            System.out.println("Customer removed from the queue successfully"); //success message
                        } else {
                            System.out.println("No customer in the specific place. Please try again."); //error message
                        }
                    } else {
                        System.out.println("Invalid specific place. Please try again."); //error message
                    }
                } else if (cashierNumber == 2) {
                    if (specific_place <= cashierTwo.length) {
                        if (cashierTwo[specific_place].equals("O")) {
                            cashierTwo[specific_place] = "X";
                            for (int i = specific_place; i < cashierTwo.length - 1; i++) {
                                cashierTwo[i] = cashierTwo[i + 1];
                            }
                            cashierTwo[cashierTwo.length-1] = "X";
                            cashierTwoNames[specific_place] = null;
                            for (int i = specific_place; i < cashierTwoNames.length - 1; i++) {
                                cashierTwoNames[i] = cashierTwoNames[i + 1];
                            }
                            cashierTwoNames[cashierTwoNames.length-1] = null;
                            System.out.println("Customer removed from the queue successfully"); //success message
                        } else {
                            System.out.println("Invalid specific place. Please try again."); //error message
                        }
                    } else {
                        System.out.println("Invalid specific place. Please try again."); //error message
                    }
                } else {
                    if (specific_place <= cashierThree.length) {
                        if (cashierThree[specific_place].equals("O")) {
                            cashierThree[specific_place] = "X";
                            for (int i = specific_place; i < cashierThree.length - 1; i++) {
                                cashierThree[i] = cashierThree[i + 1];
                            }
                            cashierThree[cashierThree.length-1] = "X";
                            cashierThreeNames[specific_place] = null;
                            for (int i = specific_place; i < cashierThreeNames.length - 1; i++) {
                                cashierThreeNames[i] = cashierThreeNames[i + 1];
                            }
                            cashierThreeNames[cashierThreeNames.length-1] = null;
                            System.out.println("Customer removed from the queue successfully"); //success message
                        } else {
                            System.out.println("Invalid specific place. Please try again."); //error message
                        }
                    } else {
                        System.out.println("Invalid specific place. Please try again."); //error message
                    }
                }
            }
        } else {
            System.out.println("Invalid cashier number. Please try again."); //error message
        }
    }

    //method to remove a served customer
    public static void remove_served_customer(Scanner input) {

        //getting the cashier number
        System.out.print("Enter cashier number: ");
        int cashierNumber;
        try {
            cashierNumber = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again."); //error message for invalid input
            return;
        }

        //checking whether the cashier number is valid
        if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {
            //checking whether the cashier is empty
            if (cashierNumber == 1 && cashierOne[0].equals("X")) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 2 && cashierTwo[0].equals("X")) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 3 && cashierThree[0].equals("X")) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else {
                //remove a served customer in the queue
                if (cashierNumber == 1) {
                    cashierOne[0] = "X";
                    for(int i = 0; i < cashierOne.length - 1; i++) {
                        cashierOne[i] = cashierOne[i + 1];
                    }
                    cashierOne[cashierOne.length-1] = "X";
                    cashierOneNames[0] = null;
                    for (int i =0; i < cashierOneNames.length - 1; i++) {
                        cashierOneNames[i] = cashierOneNames[i + 1];
                    }
                    cashierOneNames[cashierOneNames.length-1] = null;
                    System.out.println("Served customer removed from the queue successfully"); //success message
                } else if (cashierNumber == 2) {
                    cashierTwo[0] = "X";
                    for(int i = 0; i < cashierTwo.length - 1; i++) {
                        cashierTwo[i] = cashierTwo[i + 1];
                    }
                    cashierTwo[cashierTwo.length-1] = "X";
                    cashierTwoNames[0] = null;
                    for (int i =0; i < cashierTwoNames.length - 1; i++) {
                        cashierTwoNames[i] = cashierTwoNames[i + 1];
                    }
                    cashierTwoNames[cashierTwoNames.length-1] = null;
                    System.out.println("Served customer removed from the queue successfully"); //success message
                } else {
                    cashierThree[0] = "X";
                    for(int i = 0; i < cashierThree.length - 1; i++) {
                        cashierThree[i] = cashierThree[i + 1];
                    }
                    cashierThree[cashierThree.length-1] = "X";
                    cashierThreeNames[0] = null;
                    for (int i =0; i < cashierThreeNames.length - 1; i++) {
                        cashierThreeNames[i] = cashierThreeNames[i + 1];
                    }
                    cashierThreeNames[cashierThreeNames.length-1] = null;
                    System.out.println("Served customer removed from the queue successfully"); //success message
                }
            }
        } else {
            System.out.println("Invalid cashier number. Please try again."); //error message
        }
    }

    //method to sort the customers in alphabetical order
    public static void sorted_customers() {
        System.out.println("\nSort the customers in alphabetical order"); //displaying the message

        String[] sortCashierOneNames = new String[cashierOneNames.length];
        String[] sortCashierTwoNames = new String[cashierTwoNames.length];
        String[] sortCashierThreeNames = new String[cashierThreeNames.length];

        //copying the cashier names to the new array lists
        if (cashierOneNames[0] != null ) {
            for (int i = 0; i < cashierOneNames.length; i++) {
                if (cashierOneNames[i] != null) {
                    sortCashierOneNames[i] = cashierOneNames[i];
                }
            }
            bubbleSort(sortCashierOneNames);
//            Arrays.fill(sortCashierOneNames, null);
            System.out.println("Cashier 1: " + Arrays.toString(sortCashierOneNames));
        } else {
            System.out.println("Cashier 1 is empty");
        }

        if (cashierTwoNames[0] != null) {
            for (int i = 0; i < cashierTwoNames.length; i++) {
                if (cashierTwoNames[i] != null) {
                    sortCashierTwoNames[i] = cashierTwoNames[i];
                }
            }
            bubbleSort(sortCashierTwoNames);
//            Arrays.fill(sortCashierTwoNames, null);
            System.out.println("Cashier 2: " + Arrays.toString(sortCashierTwoNames));
        } else {
            System.out.println("Cashier 2 is empty");
        }

        if (cashierThreeNames != null) {
            for (int i =0; i < cashierThreeNames.length; i++) {
                if (cashierThreeNames[i] != null) {
                    sortCashierThreeNames[i] = cashierThreeNames[i];
                }
            }
            bubbleSort(sortCashierThreeNames);
//            Arrays.fill(sortCashierThreeNames, null);
            System.out.println("Cashier 3: " + Arrays.toString(sortCashierThreeNames));
        } else {
            System.out.println("Cashier 3 is empty");
        }
    }

    //bubble sort
    private static void bubbleSort(String[] array) {
        int bottom = array.length - 2;
        String tempt;
        boolean exchanged = true;

        while (exchanged) {
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {
                if (array[i +1] == null) {
                    break;
                }else {
                    if (array[i].compareTo(array[i + 1]) > 0) {
                        tempt = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = tempt;
                        exchanged = true;
                    }
                }
            }
            bottom--;
        }
    }

    //method to store to customer data to text file
    public static void store_customer_data() {
        System.out.println("\nStore customer data to text file"); //displaying the message

        try {
            //creating a file writer
            FileWriter fileWriter = new FileWriter("customer_data.txt");

            fileWriter.write("*****************" + "\n");
            fileWriter.write("*    Cashiers   *" + "\n");
            fileWriter.write("*****************" + "\n");

            for (String s : cashierOne) {
                fileWriter.write(s + " ");
            }

            fileWriter.write("\n");

            for (String s : cashierTwo) {
                fileWriter.write(s + " ");
            }

            fileWriter.write("\n");

            for (String s : cashierThree) {
                fileWriter.write(s + " ");
            }

            fileWriter.write("\n");

            fileWriter.write("*****************" + "\n");
            fileWriter.write("X - not occupied" + "\n");
            fileWriter.write("O - occupied" + "\n");

            if (cashierOneNames[0] != null) {
                fileWriter.write("Cashier 1: " + Arrays.toString(cashierOneNames) + "\n");
            } else {
                fileWriter.write("Cashier 1: Empty"  + "\n");
            }

            if (cashierTwoNames[0] != null) {
                fileWriter.write("Cashier 2: " + Arrays.toString(cashierTwoNames) + "\n");
            } else {
                fileWriter.write("Cashier 2: Empty"  + "\n");
            }

            if (cashierThreeNames[0] != null) {
                fileWriter.write("Cashier 3: " + Arrays.toString(cashierThreeNames) + "\n");
            } else {
                fileWriter.write("Cashier 3: Empty"  + "\n");
            }

            fileWriter.close(); //closing the file writer

            System.out.println("Customer data stored successfully"); //success message
        } catch (IOException e) {
            System.out.println("Error occurred"); //error message
        }
    }

    //method to load to customer data from text file
    public static void load_customer_data() {
        System.out.println("\nLoad customer data from text file"); //displaying the message

        try {
            //creating a file
            File file = new File("customer_data.txt");

            //creating a file reader
            Scanner fileReader = new Scanner(file);

            //checks if the file is empty
            while (fileReader.hasNextLine()) {
                String text = fileReader.nextLine();
                System.out.println(text);
            }
            fileReader.close(); //closing the file reader
        } catch (IOException e) {
            System.out.println("Error occurred"); //error message
        }
    }

    //method to view remaining burger stocks
    public static void view_remaining_burger_stocks() {
        //displaying the remaining burger stocks

        System.out.println("Remaining burger stocks: " + burgerStocks);
    }

    //method to add burger stocks
    public static void add_burger_stocks(Scanner input) {
        System.out.println("\nAdd burger stocks"); //displaying the message

        //getting the number of burger stocks to be added
        System.out.print("Enter number of burger stocks to be added: ");
        try {
            int stocks = input.nextInt();
            //adding the burger stocks
            burgerStocks += stocks;
        } catch (Exception e) {
            System.out.println("Invalid input"); //error message
            return;
        }

        System.out.println("Burger stocks added successfully"); //success message
    }


}
