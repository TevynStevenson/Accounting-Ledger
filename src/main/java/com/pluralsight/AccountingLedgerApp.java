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
                    menuRunning = false;
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
    public static String displayHomeScreen(Scanner myScanner) {
        //Add scanner
        // Welcome screen, prompt user to input selection
        //Selections include (A)dd Deposit | (M)ake Payment | (L)edger | (E)xit

        //Introduce new Scanner named myScanner
        myScanner = new Scanner(System.in);

        // Welcome user to the app
        System.out.println("====== Welcome To The Banking Supreme App ====== \n");

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
    public static void displayLedgerMenu(Scanner myScanner) {

        //Add scanner
        myScanner = new Scanner(System.in);

        //Welcome user
        System.out.println("====== Welcome To The Ledger Menu ======");

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
        myScanner.nextLine();

    }







}


