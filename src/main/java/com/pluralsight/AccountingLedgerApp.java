package com.pluralsight;

//Import necessary tools
//Scanner first
// .* imports entire package
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
        while(menuRunning){

        //Variable name input
        //Make it a String
            String input = displayHomeScreen(myScanner);

        //Add case switch statements
        //Case includes (A)dd , (M) , (L) , (E)
        //The outputs of each statement will be changed later
            switch (input) {
                case "A":
                    System.out.println("Add Deposit Selected");
                    break;
                case "M":
                    System.out.println("Make Payment Selected");
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
public static String displayHomeScreen (Scanner myScanner){
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
            (A)dd Deposit
            (M)ake Payment
            (L)edger
            (X)Exit""");
    //Save users input

    //return users input
    return myScanner.nextLine().toUpperCase();

}


//public static int addDeposit (Scanner myScanner){








}
