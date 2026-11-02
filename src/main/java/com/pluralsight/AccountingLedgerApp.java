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
        //The app should stop when the user enter (E)xit
        //Add boolean to keep app running until false

        boolean menuRunning = true;
        while(menuRunning){
        displayHomeScreen(myScanner);

        //Add scanner for users input
        //Variable name input
        //Make it a char


        //Add case switch statements
        //Case includes (A)dd , (M) , (L) , (E)





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
            (E)xit""");
    //Save users input

    //return users input
    return myScanner.nextLine();

}






}
