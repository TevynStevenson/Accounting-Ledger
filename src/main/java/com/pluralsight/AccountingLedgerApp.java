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
            //Case includes (D)dd , (P) , (L) , (E)
            //The outputs of each statement will be changed later
            switch (input) {
                case "D":
                    addDeposit(myScanner);
                    break;
                case "P":
                    makePayment(myScanner);
                    break;
                case "L":
                    System.out.println("Display Ledger Selected");
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
                Which option would you like to choose? :
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


}
