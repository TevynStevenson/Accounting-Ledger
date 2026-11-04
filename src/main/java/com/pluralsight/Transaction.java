package com.pluralsight;

//Import LocalDate
//Import LocalTime
//Import DateTimeFormatter


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    //Class for constructor
    //Create getters/setters
    //Initialize variables
    //Import LocalTime/LocalDate

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    //Initialize constructor


    //For Switch commands D and P
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }


    //Getters/Setters for Date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //Getters/Setters for Time
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    //Getter/setters for Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters/Setters for vendor
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    //Getters/Setters for Amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //To get the output format how I want
    public String toString() {
        return String.format(
                "%s | %s | %s | %s | $%.2f \n",
                getDate(),
                getTime(),
                getDescription(),
                getVendor(),
                getAmount()
        );
    }

    //Added to make the L switch command work
//Class for Ledger must have the Date and Time parsed
//.Trim to cut off unnecessary space
    public Transaction(String date, String time, String description, String vendor, double amount) {

        //Had to this way to get entire csv file to run
        //If deleted would only need HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[H:mm:ss][HH:mm:ss][H:mm][HH:mm]");

        this.date = LocalDate.parse(date.trim());
        this.time = LocalTime.parse(time.trim(), formatter);
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }




}
