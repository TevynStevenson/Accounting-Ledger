package com.pluralsight;

//Import necessary tools
//Scanner first
// .* imports entire package

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;
import java.io.*;


public class AccountingLedgerApp {

    //Main Method
    public static void main(String[] args) {
        //Keep the main method clean
        // Add multiple comments for step-by-step
        // Make multiple commits

        //Call displayHomeScreen to the main method
        //Introduce scanner to this method
        Scanner myScanner = new Scanner(System.in);

        //Add a loop around the home screen menu
        //The app should stop when the user enter (X)Exit
        //Add boolean to keep app running until false

        boolean menuRunning = true;
        while (menuRunning) {

            //Variable name input
            //Make it a String
            String input = displayHomeScreen(myScanner);

            //Add case switch statements
            //Case includes (D) , (P) , (L) , (E)
            //The outputs of each statement will be changed later
            switch (input) {
                case "D":
                    addDeposit(myScanner);
                    break;
                case "P":
                    makePayment(myScanner);
                    break;
                case "L":
                    displayLedgerMenu(myScanner);
                    break;
                case "X":
                    System.out.println("Exiting Application");
                    menuRunning = false;
                    break;
                default:
                    System.out.println("Invalid Selection. Please try again");
                    break;
            }


        }


    }


    // Other methods created
    //Call these methods to main method when completed
    //Add scanner
    // Welcome screen, prompt user to input selection
    //Selections include (A)dd Deposit | (M)ake Payment | (L)edger | (E)xit
    public static String displayHomeScreen(Scanner myScanner) {
        //Introduce new Scanner named myScanner
        myScanner = new Scanner(System.in);

        // Welcome user to the app
        //Adding a little flair to the project
        //Title Banking Supreme

        //Add try/catch to be able to delay messages
        try {
            System.out.println("==================================================");
            Thread.sleep(500);
            System.out.println("                                                    ");

            System.out.println("           BANKING SUPREME  |  EST. 2026           ");
            Thread.sleep(500);
            System.out.println("                                                    ");

            System.out.println("             YOUR FINANCES. YOUR CONTROL.          ");
            Thread.sleep(500);
            System.out.println("                                                    ");
            System.out.println("==================================================");
            Thread.sleep(700);
            System.out.println("             SECURE BANKING PORTAL                 ");
            System.out.println();

            //Add real banking initialization
            //Initializing, Loading, Secure session
            System.out.println("[SYSTEM] Initializing Banking Supreme....");
            Thread.sleep(1000);
            System.out.println("[SYSTEM] Financial Ledger Loading........");
            Thread.sleep(1000);
            System.out.println("[SYSTEM] Secure session ready!\n");
            Thread.sleep(1500);

        } catch (InterruptedException e) {
            System.out.println("[SYSTEM] System initialization interrupted");
        }

        //Prompt user to input selection
        System.out.println("""
                Which option would you like to choose?
                (D)Add Deposit
                (P)Make Payment
                (L)Ledger
                (X)Exit""");
        //Save users input

        //return users input
        return myScanner.nextLine().toUpperCase();

    }

    //Method for switch statement D
    //Allow user to add a deposit to the .csv file
    public static void addDeposit(Scanner depositScanner) {
        //Initialize scanner
        //Prompt user to enter desire deposit amount
        //Store input
        //Prompt user for the amount they want to deposit

        System.out.println("How much would you like to deposit?");

        //Store users input
        //Variable name = depositAmount
        //Make it .nextDouble because the user will input a $ amount
        String depositAmount = depositScanner.nextLine();

        //Prompt user for a short description
        System.out.println("Short description of your deposit");
        //Store the answer
        String depositDescription = depositScanner.nextLine().toUpperCase();


        //Prompt the user for the Vendor
        System.out.println("Vendor");
        //Store the answer
        String vendor = depositScanner.nextLine().toUpperCase();

        //Add try/catch statement to transfer user input into Transactions.csv file
        //Will need to implement FileWriter and BufferedWriter
        //Catch exception e, runtimeException, ioException
        //When constructing the filewriter dont forget file name, and true to make sure it appends

        try {
            //Import localDate
            //Import LocalTime
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            //Format the time
            //Add date time formatter ("HH:mm:ss:")
            //formattedTime will be variable name
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = time.format(formatter);

            //Add bufferedWriter (Can do it all in one line to save time)
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));

            //Initialize variable for "line"
            String line = String.format("%s | %s | Deposit | %s | %s | $%s\n", date, formattedTime, depositDescription, vendor, depositAmount);

            //Allow user to write to the .csv file
            bufferedWriter.write(line);

            //Users transaction information will go here
            //Close the buffered writer
            bufferedWriter.close();

            //Add catch statement
        } catch (IOException e) {
            System.out.println("Error writing to file");
            throw new RuntimeException(e);
        }

        //Print out message for successful deposit
        System.out.println("Your deposit has been added to the transaction list");
    }


    //Create a make payment method
    //Payments must be stored as negative
    //Either require a negative amount or convert it to negative
    public static void makePayment(Scanner paymentScanner) {
        //Initialize scanner
        //Prompt user to enter desire deposit amount
        //Store input
        //Prompt user for the description
        System.out.println("How much for the payment?");

        //Store users input
        //Variable name = paymentAmount
        //Make it .nextDouble because the user will input a $ amount

        String paymentAmount = paymentScanner.nextLine();

        //Prompt user for a short description
        System.out.println("Short description of your payment");
        //Store the answer
        String paymentDescription = paymentScanner.nextLine().toUpperCase();

        //Prompt the user for the Vendor
        System.out.println("Vendor");
        //Store the answer
        String vendor = paymentScanner.nextLine().toUpperCase();

        //Add try/catch statement to transfer user input into Transactions.csv file
        //Will need to implement FileWriter and BufferedWriter
        //Catch exception e, runtimeException, ioException
        //When constructing the filewriter dont forget file name, and true to make sure it appends

        try {
            //Import localDate
            //Import LocalTime
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            //Format the time
            //Add date time formatter ("HH:mm:ss:")
            //formattedTime will be variable name
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = time.format(formatter);

            //Add bufferedWriter (Can do it all in one line to save time
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));

            //Initialize variable for "line"
            String line = String.format("%s | %s | Payment | %s | %s | $-%s\n", date, formattedTime, paymentDescription, vendor, paymentAmount);

            //Allow user to write to the .csv file
            bufferedWriter.write(line);

            //Users transaction information will go here
            //Close the buffered writer
            bufferedWriter.close();

            //Add catch statement
        } catch (IOException e) {
            System.out.println("Error writing to file");
            throw new RuntimeException(e);
        }

        System.out.println("Your payment has been added to the transaction list");
    }

    //Create a method to read transactions from transactions.csv
    //FileReader/BufferedReader
    //Call FileReader first, surround with try catch statement
    //Use ("\\|") to split
    public static ArrayList<Transaction> readTransactions() {

        //Initialize new transactions
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {

            //Initialize the FileReader before the BufferedReader
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Don't declare a variable or while loop won't run
            String line;


            //Add while loop to run until there are no more transactions
            while ((line = bufferedReader.readLine()) != null) {


                //Split the inventory by the (|) pipe
                String[] transactionData = line.split("\\|");

                //if (transactionData.length != 6) {
                // continue;
                //  }

                // Remove unnecessary spaces
                String date = transactionData[0];
                String time = transactionData[1].trim();

                // Extract transaction information
                String type = transactionData[2].trim();
                String description = transactionData[3].trim();
                String vendor = transactionData[4].trim();

                // Parse transaction amount because Doubles aren't Strings
                double amount = Double.parseDouble(
                        transactionData[5].trim().replace("$", "")
                );

                // Create new Transaction object
                Transaction addedTransaction = new Transaction(
                        date, time, type + " | " + description, vendor, amount
                );

                // Add transaction to ArrayList
                transactions.add(addedTransaction);
            }

            //Must close bufferedReader
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Error reading file");
            throw new RuntimeException(e);
        }

        //Return the output
        return transactions;
    }

    //Create a displayTransactions() menu
    //Use a loop for the ArrayList
    public static void displayTransactions(ArrayList<Transaction> transactions) {
        //If statement for if the list is empty
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
            return;
        }

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );


        //For loop to run until there are no more transactions
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }

    }

    //Make a displayLedgerMenu method
    //Reference displayHomeScreen
    //Connect the All option to display every transaction
    public static String displayLedgerScreen(Scanner myScanner) {

        //Add scanner
        myScanner = new Scanner(System.in);

        //Add flair
        //Add try/catch statement to be able to run Thread.sleep()

        try {

            //Add loading prompt;
            System.out.println("[SYSTEM] Ledger menu loading... please wait \n");
            Thread.sleep(2000);


            //Welcome user
            System.out.println("======================================= ");
            Thread.sleep(500);
            System.out.println("                                        ");
            System.out.println("                                        ");
            System.out.println("       Welcome To The Ledger Menu       ");
            Thread.sleep(500);
            System.out.println("                                        ");
            System.out.println("                                        ");
            Thread.sleep(500);
            System.out.println("======================================= \n");
            Thread.sleep(1500);

            //Add catch statement to allow Thread.sleep() to run
        } catch (InterruptedException e) {
            System.out.println("Error loading menu");
        }

        //Prompt user for input selection
        //Selections : A,D,P,R, and H
        System.out.println("""
                Which option would you like to choose?
                (A) All Transactions
                (D) Deposits
                (P) Payments
                (R) Reports
                (L) Exit""");
        //Save users input
        //toUpperCase so capitalization doesn't matter

        return myScanner.nextLine().toUpperCase();
    }

    //method for displayLedgerMenu options menu
    //Name displayLedgerMenu
    //Case switches A,D,P,R,L, and Default
    //boolean statement to run while loop
    //while loop to keep app running until exited
    public static void displayLedgerMenu(Scanner myScanner) {

        //Initialize boolean statement to keep running until exited
        boolean appRunning = true;
        while (appRunning) {

            //Display the Ledger menu
            //Capture user input
            String input = displayLedgerScreen(myScanner);

            //Add switch statements
            switch (input) {
                case "A":
                    displayTransactions(readTransactions());
                    break;
                case "D":
                    displayTransactions(readDeposits());
                    break;
                case "P":
                    displayTransactions(readPayments());
                    break;
                case "R":
                    if (displayReportMenu(myScanner)) {
                        //Add this return to make sure when H is hit in reportMenu, it will shutdown
                        //This method too.
                        return;
                    }
                    break;
                case "L":
                    System.out.println("Thank you for using the Ledger Menu");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Invalid input");

            }
        }

    }

    //Create a method to filter and display deposits only
    //Use an if statement to check whether the amount is >0
    //Return a new ArrayList
    public static ArrayList<Transaction> readDeposits() {

        //Initialize new array list
        ArrayList<Transaction> deposits = new ArrayList<>();

        //Get all the transactions from the csv file
        ArrayList<Transaction> transactions = readTransactions();

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Loop through all of the transactions
        for (Transaction transaction : transactions) {

            //Check if the amount is positive
            if (transaction.getAmount() > 0) {

                //If positive, it will be added to the deposits arraylist
                deposits.add(transaction);
            }

        }
        //Return the deposits arrayList
        return deposits;


    }

    //Create a method to filter and display deposits only
    //Use an if statement to check whether the amount is >0
    //Return a new ArrayList
    public static ArrayList<Transaction> readPayments() {
        //Initialize new array list
        ArrayList<Transaction> payments = new ArrayList<>();

        //Get all the transactions from the csv file
        ArrayList<Transaction> transactions = readTransactions();

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Loop through all of the transactions
        for (Transaction transaction : transactions) {

            //Check if the amount is positive
            if (transaction.getAmount() < 0) {

                //If positive, it will be added to the deposits arraylist
                payments.add(transaction);
            }

        }
        //Return the deposits arrayList
        return payments;


    }

    //Create a seperate reports menu that displays all available reporting options
    //Allow users to select date based reports, vendor searches, etc
    public static String displayReportScreen(Scanner myScanner) {
        //Add scanner
        myScanner = new Scanner(System.in);


        // Add try/catch statement
        //Run thread.sleep() to delay messages
        try {
            //Welcome user
            System.out.println("|$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$|");
            Thread.sleep(500);
            System.out.println("|                                         |");
            Thread.sleep(500);
            System.out.println("|                                         |");
            System.out.println("|====== Welcome To The Reports Menu ======|");
            Thread.sleep(500);
            System.out.println("|                                         |");
            System.out.println("|                                         |");
            Thread.sleep(500);
            System.out.println("|$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$|");
            Thread.sleep(500);

            System.out.println("[SYSTEM] Access Granted.....\n");
            Thread.sleep(500);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        //Prompt user for input selection
        //Selections : 1,2,3,4,5 and 0 to exit
        System.out.println("""
                Which option would you like to choose?
                (1) Month To Date
                (2) Previous Month To Date
                (3) Year To Date
                (4) Previous Year To Date
                (5) Search By Vendor
                (6) Custom Search
                (0) Exit to Ledger Menu
                (H) Exit to Home Menu""");
        //Save users input
        //toUpperCase so capitalization doesn't matter

        return myScanner.nextLine().toUpperCase();

    }

    //Method for displaying reportsMenu
    //Case switches ---> 1,2,3,4,5,0, and H(Home)
    //Add switch statement
    //boolean statement run the while loop
    //while loop to keep app running until exited
    //Make method Boolean so it can have a stopping return
    public static Boolean displayReportMenu(Scanner myScanner) {
        //Add arrayList


        boolean appRunning = true;
        while (appRunning) {
            String input = displayReportScreen(myScanner);
            switch (input) {
                case "1":
                    displayMonthToDate(readTransactions());
                    break;
                case "2":
                    displayPreviousMonth(readTransactions());
                    break;
                case "3":
                    displayYearToDate(readTransactions());
                    break;
                case "4":
                    displayPreviousYear(readTransactions());
                    break;
                case "5":
                    displaySearchByVendor(readTransactions());
                    break;
                case "6":
                    if (CustomSearchMenu(myScanner)) {
                        return true;
                    }
                    break;
                case "9":
                    System.out.println("================================");
                    System.out.println("      BANKING SUPREME           ");
                    System.out.println("   Created by: Tevyn Stevenson  ");
                    System.out.println("   Java Developer in Training   ");
                    System.out.println("     Built with Java!           ");
                    System.out.println("================================");
                    break;
                case "0":
                    System.out.println("Exiting the Report Menu");
                    return false;
                case "H":
                    System.out.println("Going to Home Menu");
                    return true;

                default:
                    System.out.println("Invalid input");

            }
        }
        //Add return
        return false;
    }

    //Create a report that display transactions
    //First day of the current month through today's date
    //LocalDate.Now() to get todays date
    //.withDayOfMonth(1) to get first day of current month
    public static void displayMonthToDate(ArrayList<Transaction> transactions) {

        //Get the first day of the month
        LocalDate today = LocalDate.now();

        //Get first day of month
        LocalDate startOfMonth = today.withDayOfMonth(1);

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Loops though all of the transactions
        for (Transaction transaction : transactions) {

            //Get the date of the current transaction
            LocalDate transactionDate = transaction.getDate();

            //Make an if statement to get the month to date
            if (!transactionDate.isBefore(startOfMonth) && !transactionDate.isAfter(today)) {

                //Display Transaction
                System.out.println(transaction);


            }
        }

    }

    //Create a report that display transactions
    //First day of the previous month through today's date
    //LocalDate.Now() to get todays date
    //.withDayOfMonth(1) to get first day of previous month
    public static void displayPreviousMonth(ArrayList<Transaction> transactions) {

        //Get todays date
        LocalDate today = LocalDate.now();

        //Get start of previous month
        LocalDate startOfCurrentMonth = today.withDayOfMonth(1);

        //Find last day of the month
        LocalDate startOfPreviousMonth = startOfCurrentMonth.minusMonths(1);

        //Get last day of previous month
        LocalDate endOfPreviousMonth = startOfCurrentMonth.minusDays(1);

        //Print message
        System.out.println("Previous Month To Date Report");

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop
        for (Transaction transaction : transactions) {

            //Get previous month
            LocalDate recordDate = transaction.getDate();

            //Add if statement
            if (!recordDate.isBefore(startOfPreviousMonth) && !recordDate.isAfter(endOfPreviousMonth)) {

                //Print out transactions
                System.out.println(transaction);
            }

        }
    }


    //Create a report that displays transactions
    //Year To Date report
    //LocalDate.now().withDayOfYear(1) to get January 1.
    //Use today's date as the ending date.
    public static void displayYearToDate(ArrayList<Transaction> transactions) {

        //Get today's date
        LocalDate today = LocalDate.now();

        //Get current year date
        LocalDate startOfCurrentYear = today.withDayOfYear(1);

        //Report message
        System.out.println("Year To Date Report");

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop to run until there are no more transactions
        for (Transaction transaction : transactions) {

            //Make the target date
            LocalDate targetDate = transaction.getDate();

            //Make if statement
            if (!targetDate.isBefore(startOfCurrentYear) && !targetDate.isAfter(today)) {

                //Print out Year To Date Report
                System.out.println(transaction);
            }
        }


    }


    //Create a report that displays transactions
    //Previous Year To Date report
    //LocalDate.now().minusYears(1) to determine the previous year
    //.withDayOfYear(1) to find January 1.
    public static void displayPreviousYear(ArrayList<Transaction> transactions) {

        //Get today's date
        LocalDate today = LocalDate.now();

        //Get current year date
        LocalDate startOfCurrentYear = today.withDayOfYear(1);

        //Get the start of the previous year
        LocalDate startOfPreviousYear = startOfCurrentYear.minusYears(1);

        //Get the end of the previous year
        LocalDate endOfPreviousYear = startOfCurrentYear.minusDays(1);

        //Report message
        System.out.println("Previous Year To Date Report");

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop to run until there are no more transactions
        for (Transaction transaction : transactions) {

            //Make the target date
            LocalDate targetDate = transaction.getDate();

            //Make if statement
            if (!targetDate.isBefore(startOfPreviousYear) && !targetDate.isAfter(endOfPreviousYear)) {

                //Print out Year To Date Report
                System.out.println(transaction);
            }
        }


    }

    //Create a search by vendor method
    //Prompt user to enter the vendor name
    //Use . equalsIgnoreCase so capitalization doesn't matter
    //Use .contains() for partial inputs Nike ---> Nike Inc.
    public static void displaySearchByVendor(ArrayList<Transaction> transactions) {

        //Prompt user to input vendor
        Scanner myScanner = new Scanner(System.in);

        //Welcome message
        System.out.println("====== Welcome To The Search By Vendor Menu ======");
        System.out.println("Enter the name of your vendor");

        //Save users input
        String vendor = myScanner.nextLine();

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Loop through thre transactions
        for (Transaction transaction : transactions) {

            //Add if statement for if the users vendor matches the vendors on file
            //Gonna ask about it
            //if (transaction.getVendor().contains(vendor.toLowerCase())) {

            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                //Print transactions
                System.out.println(transaction);

            }


        }
    }

    //Create a custom search method
    //Can have Start Date,End Date,Description,Vendor,Amount
    public static String displayCustomSearch(Scanner myScanner) {
        //Add scanner
        myScanner = new Scanner(System.in);

        //Welcome user
        System.out.println("====== Welcome To The Custom Search Menu ======");

        //Prompt user for input selection
        //Selections : 1,2,3,4,5 and 0 to exit
        System.out.println("""
                Which option would you like to choose?
                (S) Start Date
                (E) End Date
                (D) Description
                (V) Vendor
                (A) Amount
                (L) Exit Custom Search
                (H) Exit to Home Menu""");
        //Save users input
        //toUpperCase so capitalization doesn't matter

        return myScanner.nextLine().toUpperCase();

    }

    //Method for displaying Custom Search menu
    //Case switches ---> 1,2,3,4,5,0, and H(Home)
    //Add switch statement
    //boolean statement run the while loop
    //while loop to keep app running until exited
    //Make method Boolean so it can have a stopping return
    public static Boolean CustomSearchMenu(Scanner myScanner) {

        //boolean to keep the app running
        boolean appRunning = true;

        while (appRunning) {

            //User input
            String input = displayCustomSearch(myScanner);

            switch (input) {
                case "S":
                    searchByStartDate(readTransactions());
                    break;
                case "E":
                    searchByEndDate(readTransactions());
                    break;
                case "D":
                    searchByDescription(readTransactions());
                    break;
                case "V":
                    displaySearchByVendor(readTransactions());
                    break;
                case "A":
                    searchByAmount(readTransactions());
                    break;
                case "L":
                    System.out.println("Leaving Custom Search");
                    return false;
                case "H":
                    System.out.println("Going to Home Menu");
                    return true;

                default:
                    System.out.println("Invalid input");

            }
        }
        //Add return
        return false;
    }

    //Add a method to search by description
    //Void doesn't need return
    public static void searchByDescription(ArrayList<Transaction> transactions) {

        //Prompt user to input vendor
        Scanner myScanner = new Scanner(System.in);

        //Welcome message
        System.out.println("====== Welcome To The Search By Description ======");
        System.out.println("Enter description of the transaction");

        //Store the users input
        String description = myScanner.nextLine();

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop
        for (Transaction transaction : transactions) {

            //Use .contains to see if description matches users input
            //To lowercase to make capitalization not matter
            if (transaction.getDescription().toLowerCase().contains(description.toLowerCase())) {


                System.out.println(transaction);
            }
        }

    }


    //Add a search by amount method
    public static void searchByAmount(ArrayList<Transaction> transactions) {

        //Introduce scanner
        Scanner myScanner = new Scanner(System.in);

        //Prompt user to enter amount
        //Welcome message
        System.out.println("====== Welcome To The Search By Amount=====");
        System.out.println("Enter amount of the transaction");

        //Save users input
        double amount = myScanner.nextDouble();

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop
        for (Transaction transaction : transactions) {

            //Add if statement
            if (transaction.getAmount() == amount) {

                System.out.println(transaction);
            }
        }
    }

    //Add a method to search by start date
    //Will have to use .parse to get the correct input
    public static void searchByStartDate(ArrayList<Transaction> transactions) {

        //Initialize scanner
        Scanner myScanner = new Scanner(System.in);

        //Prompt user to input the start date
        //Welcome message
        System.out.println("====== Welcome To The Search By Start Date=====");
        System.out.println("Enter start date of the transaction (YYYY-MM-DD)");

        //Save users input
        String userInput = myScanner.nextLine();

        //Add localDate for the startDate
        LocalDate startDate = LocalDate.parse(userInput);


        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop
        for (Transaction transaction : transactions) {


            //Add if statement
            if (!transaction.getDate().isBefore(startDate)) {

                System.out.println(transaction);
            }

        }
    }

    //Make a method to search by end date
    //Will have to parse to get the correct output
    public static void searchByEndDate(ArrayList<Transaction> transactions) {

        //Initialize scanner
        Scanner myScanner = new Scanner(System.in);

        //Prompt user to enter the end date
        //Welcome message
        System.out.println("====== Welcome To The Search By End Date=====");
        System.out.println("Enter end date of the transaction (YYYY-MM-DD)");

        //Store users input
        String userInput = myScanner.nextLine();

        //Initialize dates
        LocalDate endDate = LocalDate.parse(userInput);

        //Sort the transactions from bottom to top instead of top to bottom
        //Use Collections.Sort()
        //Must import
        //Put before the for loop
        transactions.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        //Add for loop
        for (Transaction transaction : transactions) {
            if (!transaction.getDate().isAfter(endDate)) {

                System.out.println(transaction);
            }
        }


    }


    //End of methods
}









