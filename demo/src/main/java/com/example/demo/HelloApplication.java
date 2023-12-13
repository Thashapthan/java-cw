package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Foodies Fave Food Center");
        stage.setScene(scene);
        stage.show();
    }


    static FoodQueue[] foodQueueOne = new FoodQueue[2]; //create a new food queue one
    static FoodQueue[] foodQueueTwo = new FoodQueue[3]; //create a new food queue two
    static FoodQueue[] foodQueueThree = new FoodQueue[5]; //create a new food queue three
    static FoodQueue[] waitingFoodQueue = new FoodQueue[10]; //create new waiting food queue
    static int burgerStocks = 50; //initial burger stock is 50
    static int cashierOneIncome = 0; //initial total income is 0
    static int cashierTwoIncome = 0; //initial total income is 0
    static int cashierThreeIncome = 0; //initial total income is 0

    public static void main(String[] args) {

        String[] menuOptions = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "112", "999", "VFQ", "VEQ", "ACQ", "RCQ", "PCQ", "VCS", "SPD", "LPD", "STK", "AFS", "EXT", "IFQ", "GUI"};
        List<String> menuSelection = Arrays.asList(menuOptions);

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
                System.out.println("110 or IFQ: Print the income from each queue");
                System.out.println("112 or GUI: Open GUI with customer details");
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

                case "110":
                case "IFQ":
                    printIncome(); //print income from each queue
                    break;

                case "112":
                case "GUI":
                    launch(); //launch the GUI
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
        for (int i = 0; i < foodQueueThree.length; i++) {
            if (i < foodQueueOne.length) {
                if (foodQueueOne[i] != null) {
                    System.out.print("  " + "O");
                } else {
                    System.out.print("  " + "X");
                }
            } else {
                System.out.print("   ");
            }

            if (i < foodQueueTwo.length) {
                if (foodQueueTwo[i] != null) {
                    System.out.print("     " + "O");
                } else {
                    System.out.print("     " + "X");
                }
            } else {
                System.out.print("      ");
            }

            if (foodQueueThree[i] != null) {
                System.out.print("     " + "O");
            } else {
                System.out.print("     " + "X");
            }
            System.out.println();
        }

        //displaying the message
        System.out.println("*****************");
        System.out.println("X - not occupied");
        System.out.println("O - occupied");

        if (foodQueueOne[0] != null) {
            System.out.print("\nCashier 1: ");

            for (FoodQueue i: foodQueueOne) {
                if (i != null) {
                    System.out.print(i.getCustomer().getFirstName() + " ");
                }
            }
        } else {
            System.out.print("\nCashier 1: Empty");
        }

        if (foodQueueTwo[0] != null) {
            System.out.print("\nCashier 2: ");
            for (FoodQueue i: foodQueueTwo) {
                if (i != null) {
                    System.out.print(i.getCustomer().getFirstName() + " ");
                }
            }
        } else {
            System.out.print("\nCashier 2: Empty");
        }

        if (foodQueueThree[0] != null) {
            System.out.print("\nCashier 3: ");
            for (FoodQueue i: foodQueueThree) {
                if (i != null) {
                    System.out.print(i.getCustomer().getFirstName() + " ");
                }
            }
        } else {
            System.out.print("\nCashier 3: Empty");
        }

    }

    //method to view all empty queues
    public static void view_empty_queues() {
        boolean cashierOneEmpty = true;
        boolean cashierTwoEmpty = true;
        boolean cashierThreeEmpty = true;

        for (int i = 0; i < foodQueueOne.length; i++) {
            if (foodQueueOne[i] != null) {
                cashierOneEmpty = false;
                break;
            }
        } //checking whether the cashier one is empty

        for (int i = 0; i < foodQueueTwo.length; i++) {
            if (foodQueueTwo[i] != null) {
                cashierTwoEmpty = false;
                break;
            }
        } //checking whether the cashier two is empty

        for (int i = 0; i < foodQueueThree.length; i++) {
            if (foodQueueThree[i] != null) {
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

        if (burgerStocks == 0) {
            System.out.println("No burgers available");
            return;
        } //checking whether the burger stock is empty

        //getting the customer first name
        System.out.print("\nEnter customer first name: ");
        String fistName = input.next();

        //getting the customer second name
        System.out.print("\nEnter customer second name: ");
        String secondName = input.next();

        int noOfBurgers;
        // validate required burger quantity input
        try {
            System.out.print("\nEnter required burger quantity: ");
            noOfBurgers = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
            return;
        }

        input.nextLine();

        if (noOfBurgers > burgerStocks) {
            System.out.println("Not enough burgers available");
            return;
        }

        boolean queueFull = true;

        //checking the queues to add customer
        for (int i = 0; i < foodQueueThree.length; i++) {
            if (i < foodQueueOne.length && foodQueueOne[i] == null) {
                foodQueueOne[i] = new FoodQueue(new Customer(fistName, secondName, noOfBurgers), i+1);
                queueFull = false;
                cashierOneIncome += noOfBurgers * 650;
                burgerStocks -= noOfBurgers;
                System.out.println("Customer added to cashier 1");
                break;
            } else if (i < foodQueueTwo.length && foodQueueTwo[i] == null) {
                foodQueueTwo[i] = new FoodQueue(new Customer(fistName, secondName, noOfBurgers), i+1);
                queueFull = false;
                cashierTwoIncome += noOfBurgers * 650;
                burgerStocks -= noOfBurgers;
                System.out.println("Customer added to cashier 2");
                break;
            } else if (foodQueueThree[i] == null) {
                foodQueueThree[i] = new FoodQueue(new Customer(fistName, secondName, noOfBurgers), i+1);
                queueFull = false;
                cashierThreeIncome += noOfBurgers * 650;
                burgerStocks -= noOfBurgers;
                System.out.println("Customer added to cashier 3");
                XZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ2DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD1X
            }
        }

        if (queueFull) {
            System.out.println("All queues are full so that customer added to the waiting queue"); //error message
            for (int i = 0; i < waitingFoodQueue.length; i++) {
                if (waitingFoodQueue[i] == null) {
                    waitingFoodQueue[i] = new FoodQueue(new Customer(fistName, secondName, noOfBurgers), i+1);
                    return;
                }
            }
        }
    }

    //method to remove a customer from a queue
    public static void remove_customer(Scanner input) {

        //getting the cashier number
        System.out.print("Enter cashier number: ");
        int cashierNumber;
        try {
            cashierNumber = input.nextInt();
        } catch (Exception e) {
            cashierNumber = 99;
        }

        //checking whether the cashier number is valid
        if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {
            //checking whether the cashier is empty
            if (cashierNumber == 1 && foodQueueOne[0] == null) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 2 && foodQueueTwo[0] == null) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 3 && foodQueueThree[0] == null) {
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
                    input.nextLine();
                }

                //checking whether the specific place is valid
                if (cashierNumber == 1) {
                    if (specific_place < foodQueueOne.length) {
                        if (foodQueueOne[specific_place] != null) {
                            for(int i = specific_place; i < foodQueueOne.length - 1; i++) {
                                foodQueueOne[i] = foodQueueOne[i + 1];
                            }
                            foodQueueOne[foodQueueOne.length-1] = waitingFoodQueue[0];
                            for (int i = 0; i < waitingFoodQueue.length-1;  i++) {
                                waitingFoodQueue[i] = waitingFoodQueue[i + 1];
                            }
                            System.out.println("Customer removed from the queue successfully"); //success message
                        } else {
                            System.out.println("No customer in the specific place. Please try again."); //error message
                        }
                    } else {
                        System.out.println("Invalid specific place. Please try again."); //error message
                    }
                } else if (cashierNumber == 2) {
                    if (specific_place < foodQueueTwo.length) {
                        if (foodQueueTwo[specific_place] != null) {
                            foodQueueTwo[specific_place] = null;
                            for (int i = specific_place; i < foodQueueTwo.length - 1; i++) {
                                foodQueueTwo[i] = foodQueueTwo[i + 1];
                            }
                            foodQueueTwo[foodQueueTwo.length - 1] = waitingFoodQueue[0];
                            for (int i = 0; i < waitingFoodQueue.length-1;  i++) {
                                waitingFoodQueue[i] = waitingFoodQueue[i + 1];
                            }
                            System.out.println("Customer removed from the queue successfully"); //success message
                        } else {
                            System.out.println("No customer in the specific place. Please try again."); //error message
                        }
                    } else {
                        System.out.println("Invalid specific place. Please try again."); //error message
                    }
                } else {
                    if (specific_place < foodQueueThree.length) {
                        if (foodQueueThree[specific_place] != null) {
                            foodQueueThree[specific_place] = null;
                            for (int i = specific_place; i < foodQueueThree.length - 1; i++) {
                                foodQueueThree[i] = foodQueueThree[i + 1];
                            }
                            foodQueueThree[foodQueueThree.length - 1] = waitingFoodQueue[0];
                            for (int i = 0; i < waitingFoodQueue.length-1;  i++) {
                                waitingFoodQueue[i] = waitingFoodQueue[i + 1];
                            }
                            System.out.println("Customer removed from the queue successfully"); //success message
                        } else {
                            System.out.println("No customer in the specific place. Please try again."); //error message
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
        } catch (Exception e) {
            cashierNumber = 99;
        }

        //checking whether the cashier number is valid
        if (cashierNumber == 1 || cashierNumber == 2 || cashierNumber == 3) {
            //checking whether the cashier is empty
            if (cashierNumber == 1 && foodQueueOne[0] == null) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 2 && foodQueueTwo[0] == null) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else if (cashierNumber == 3 && foodQueueThree[0] == null) {
                System.out.println("Cashier is empty. Please try again."); //error message
            } else {
                //remove a served customer in the queue
                if (cashierNumber == 1) {
                    foodQueueOne[0] = null;
                    for (int i = 0; i < foodQueueOne.length - 1; i++) {
                        foodQueueOne[i] = foodQueueOne[i + 1];
                    }
                    if (waitingFoodQueue[0] != null) {
                        foodQueueOne[foodQueueOne.length - 1] = waitingFoodQueue[0];
                    }
                } else if (cashierNumber == 2) {
                    foodQueueTwo[0] = null;
                    for (int i = 0; i < foodQueueTwo.length - 1; i++) {
                        foodQueueTwo[i] = foodQueueTwo[i + 1];
                    }
                    if (waitingFoodQueue[0] != null) {
                        foodQueueOne[foodQueueOne.length - 1] = waitingFoodQueue[0];
                    }
                } else {
                    foodQueueThree[0] = null;
                    for (int i = 0; i < foodQueueThree.length - 1; i++) {
                        foodQueueThree[i] = foodQueueThree[i + 1];
                    }
                    if (waitingFoodQueue[0] != null) {
                        foodQueueOne[foodQueueOne.length - 1] = waitingFoodQueue[0];
                    }
                }
                foodQueueOne[foodQueueOne.length - 1] = waitingFoodQueue[0];
                for (int i = 0; i < waitingFoodQueue.length-1;  i++) {
                    waitingFoodQueue[i] = waitingFoodQueue[i+1];
                }
                System.out.println("Served customer removed from the queue successfully"); //success message
            }
        } else {
            System.out.println("Invalid cashier number. Please try again."); //error message
        }
    }

    //method to sort the customers in alphabetical order
    public static void sorted_customers() {
        System.out.println("\nSort the customers in alphabetical order"); //displaying the message

        String[] sortCashierOneNames = new String[foodQueueOne.length];
        String[] sortCashierTwoNames = new String[foodQueueTwo.length];
        String[] sortCashierThreeNames = new String[foodQueueThree.length];

        //copying the cashier names to the new array lists
        if (foodQueueOne[0] != null ) {
            for (int i = 0; i < foodQueueOne.length; i++) {
                if (foodQueueOne[i] != null) {
                    sortCashierOneNames[i] = foodQueueOne[i].getCustomer().getFirstName();
                }
            }
            bubbleSort(sortCashierOneNames);
            System.out.println("Cashier 1: " + Arrays.toString(sortCashierOneNames));
        } else {
            System.out.println("Cashier 1 is empty");
        }

        if (foodQueueTwo[0] != null ) {
            for (int i = 0; i < foodQueueTwo.length; i++) {
                if (foodQueueTwo[i] != null) {
                    sortCashierTwoNames[i] = foodQueueTwo[i].getCustomer().getFirstName();
                }
            }
            bubbleSort(sortCashierTwoNames);
            System.out.println("Cashier 2: " + Arrays.toString(sortCashierTwoNames));
        } else {
            System.out.println("Cashier 2 is empty");
        }

        if (foodQueueThree[0] != null ) {
            for (int i = 0; i < foodQueueThree.length; i++) {
                if (foodQueueThree[i] != null) {
                    sortCashierThreeNames[i] = foodQueueThree[i].getCustomer().getFirstName();
                }
            }
            bubbleSort(sortCashierThreeNames);
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

            //printing the queues
            for (int i = 0; i < foodQueueThree.length; i++) {
                if (i < foodQueueOne.length) {
                    if (foodQueueOne[i] != null) {
                        fileWriter.write("  " + "O");
                    } else {
                        fileWriter.write("  " + "X");
                    }
                } else {
                    fileWriter.write("   ");
                }

                if (i < foodQueueTwo.length) {
                    if (foodQueueTwo[i] != null) {
                        fileWriter.write("     " + "O");
                    } else {
                        fileWriter.write("     " + "X");
                    }
                } else {
                    fileWriter.write("      ");
                }

                if (foodQueueThree[i] != null) {
                    fileWriter.write("     " + "O");
                } else {
                    fileWriter.write("     " + "X");
                }
                fileWriter.write("\n");
            }


            fileWriter.write("\n");

            fileWriter.write("*****************" + "\n");
            fileWriter.write("X - not occupied" + "\n");
            fileWriter.write("O - occupied" + "\n");

            if (foodQueueOne[0] != null) {
                fileWriter.write("\nCashier 1: \n");
                for (FoodQueue i: foodQueueOne){
                    if (i != null) {
                        fileWriter.write(i.printFile());
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("\n");
            } else {
                fileWriter.write("\nCashier 1: Empty"  + "\n");
            }

            if (foodQueueTwo[0] != null) {
                fileWriter.write("\nCashier 2: \n");
                for (FoodQueue i: foodQueueTwo){
                    if (i != null) {
                        fileWriter.write(i.printFile());
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("\n");
            } else {
                fileWriter.write("\nCashier 2: Empty"  + "\n");
            }

            if (foodQueueThree[0] != null) {
                fileWriter.write("\nCashier 3: \n");
                for (FoodQueue i: foodQueueThree){
                    if (i != null) {
                        fileWriter.write(i.printFile());
                        fileWriter.write("\n");
                    }
                }
                fileWriter.write("\n");
            } else {
                fileWriter.write("\nCashier 3: Empty"  + "\n");
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
        int stocks;
        try {
            stocks = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input"); //error message
            return;
        }

        //adding the burger stocks
        burgerStocks += stocks;

        System.out.println("Burger stocks added successfully"); //success message
    }

    //method to print income of each queues
    public static void printIncome() {
        System.out.println("\nIncome of each queues"); //displaying the message

        //printing the income of each queues
        System.out.println("Cashier 1: " + cashierOneIncome);
        System.out.println("Cashier 2: " + cashierTwoIncome);
        System.out.println("Cashier 3: " + cashierThreeIncome);
    }



}