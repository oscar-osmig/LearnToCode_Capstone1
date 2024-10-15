package com.pluralsight;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomSearch {
    static Scanner scanner = new Scanner(System.in);
    private static boolean run = true;

    public static boolean checkIfDateFormat(String input1, String input2){
        String format = "yyyy-MM-dd";
        //LocalDate theStartDate = LocalDate.parse(input, formatter);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try{
            Date parseDate = sdf.parse(input1);
            Date parseDate2 = sdf.parse(input2);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }


    public static void customSearch(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException {

        Ascii_visuals.display("customSearch.txt");

        System.out.println("\n* Press <enter> to live category blank *");
        // start date
        System.out.println("\nStart date: (yyyy-mm-dd)");
        String startDate = scanner.nextLine();
        // end date
        System.out.println("End date: (yyyy-mm-dd)");
        String endDate = scanner.nextLine();

        // make sure the date is able to parse to LocalDate
        if(!checkIfDateFormat(startDate, endDate)){
            System.out.println("* Both dates must be the right format! *");
            customSearch(scanner, transactions);
        }
        // Description
        System.out.println("Description:");
        String description = scanner.nextLine().toLowerCase();
        // vendor
        System.out.println("Vendor:");
        String vendor = scanner.nextLine().toLowerCase();
        // amount
        System.out.println("Amount: ( enter 0 to leave blank)");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        // if not enter value variable not needed in the search



        ArrayList<Transaction> transaction = transactions;

        if (startDate.isEmpty() && endDate.isEmpty() && description.isEmpty() && vendor.isEmpty()) // filter by amount
        {
            System.out.println("\n* filtered by amount *");
            filter(amount, transaction);
        }

        else if (endDate.isEmpty() && description.isEmpty() && vendor.isEmpty() && amount == 0) { // filter by start date
            System.out.println("\n* filtered by start-date *");
            filterStartDate(startDate, transaction);
        }

        else if (startDate.isEmpty() && description.isEmpty() && vendor.isEmpty() && amount == 0) { // filter by end date
            System.out.println("\n* filtered by end-date *");
            filterStartDate(endDate, transaction);
        }

        else if ( startDate.isEmpty() && endDate.isEmpty() && vendor.isEmpty() && amount == 0) { // filter by description only
            System.out.println("\n* filtered by vendor ");
            filter(description, transaction);
        }

        else if ( endDate.isEmpty() && startDate.isEmpty() && description.isEmpty() && amount == 0) { // filter by vendor only
            System.out.println("\n* filtered by start-date and amount ");
            filter(vendor, transaction);
        }

        else if (startDate.isEmpty() && endDate.isEmpty()) { // filter by description, vendor, amount
            System.out.println("\n* filtered by description, vendor and amount ");
            filter(description, vendor, amount, transaction);
        }

        else if (startDate.isEmpty() && endDate.isEmpty() && description.isEmpty()) { // filter by vendor, amount
            System.out.println("\n* filtered by vendor and amount ");
            filter(description, vendor, amount, transaction);
        }

        else if (endDate.isEmpty() && description.isEmpty() && !vendor.isEmpty()) { // filtering by start date and vendor
            System.out.println("\n* filtered from start date and vendor *");
            filter(startDate, vendor, transaction);
        }

        else if (!startDate.isEmpty() && endDate.isEmpty() && vendor.isEmpty() && amount == 0) { // filtering by start date and description
            System.out.println("\n* filtered from start date and description *");
            filter(startDate, description, transaction);
        }


        else if (endDate.isEmpty() && description.isEmpty()) { // filtering by start date and vendor
            System.out.println("\n* filtered from start date and vendor *");
            filter(startDate, vendor, transaction);
        }

        else if (endDate.isEmpty() && vendor.isEmpty() && amount == 0) { // filtering by start date and description
            System.out.println("\n* filtered from start date and description *");
            filter(startDate, description, transaction);
        }

        else if (!endDate.isEmpty() && startDate.isEmpty() && vendor.isEmpty() && amount == 0) { // filtering by end date and description
            System.out.println("\n* filtered from end date and description *");
            filter(endDate, description, transaction);
        }

        else if (description.isEmpty() && startDate.isEmpty() && amount == 0) { // filtering by end date and vendor
            System.out.println("\n* filtered from end date and vendor *");
            filter(endDate, vendor, transaction);
        }

        else if (!description.isEmpty() && vendor.isEmpty() && amount == 0) { // filtering by start date, end date, and description
            System.out.println("\n* filtered start-date, end-date, and description ");
            filterRange(startDate, endDate,  description, transaction);
        }

        else if (description.isEmpty() && vendor.isEmpty() && amount == 0) { // filtering by start date and end date
            System.out.println("\n* filtered from range start-date and end-date *");
            filterRange(startDate, endDate, description, transaction);
        }

        else if (startDate.isEmpty() && endDate.isEmpty() && amount == 0) { // filter by description and vendor
            System.out.println("\n* filtered by description and vendor *");
            filter(description, vendor, amount, transaction);
        }

        else if (startDate.isEmpty()) { // filter by end-date, vendor, amount
            System.out.println("\n* filter by date, vendor, and amount *");
            filter(endDate,amount,description ,vendor, transaction);
        }

        else if (endDate.isEmpty()) { // filter by start-date, vendor, amount
            System.out.println("* filter by date, vendor, and amount *");
            filter(startDate,amount, description,vendor, transaction);
        }

        if (!startDate.isEmpty() && !endDate.isEmpty() && !description.isEmpty() && !vendor.isEmpty() && amount != 0) // filter by amount
        {
            System.out.println("\n* Each category *");
            filter(startDate, endDate, description, vendor, amount, transaction);
        }
        else {System.out.println("* Transaction not found *");}
        Thread.sleep(200);

        System.out.println("\n* Press <enter> to reset or enter 0 to go back to reports *");
        String choice = scanner.nextLine();
        if(choice.equals("")){
            customSearch(scanner, transactions);
        } else if (choice.equals("0")) {
            Reports.channel();
        }else {customSearch(scanner, transactions); }

    }

    private static void filter(String startDate, String endDate, String description, String vendor, float amount, ArrayList<Transaction> transaction) {
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theStartDate = LocalDate.parse(startDate, formatter);
        LocalDate theEndtDate = LocalDate.parse(endDate, formatter);
        int counter = 1;

        for (Transaction item : transactions1){
            if (item.getDate().equals(theStartDate) && item.getDescription().toLowerCase().equals(description) && item.getVendor().toLowerCase().equals(vendor) && item.getAmount() == amount){
                printTransaction(counter, item);
                counter++;
            } else if (item.getDate().isAfter(theStartDate) && item.getDate().isBefore(theEndtDate) && item.getDescription().toLowerCase().equals(description) && item.getVendor().toLowerCase().equals(vendor) && item.getAmount() == amount) {
                printTransaction(counter, item);
                counter++;
            }else if (item.getDate().equals(endDate) && item.getDescription().toLowerCase().equals(description) && item.getVendor().toLowerCase().equals(vendor) && item.getAmount() == amount){
                printTransaction(counter, item);
                counter++;
            }
        }

    }

    private static void filter(String startDate, float amount, ArrayList<Transaction> transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theStartDate = LocalDate.parse(startDate, formatter);
        int counter = 1;
        ArrayList<Transaction> transactions1 = transaction;
        for (Transaction item: transactions1){
            if (item.getDate().equals(theStartDate) && item.getAmount() == amount){
                printTransaction(counter, item);
            }
            counter++;
        }
    }

    private static void filter(String startDate, String description, ArrayList<Transaction> transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theStartDate = LocalDate.parse(startDate, formatter);
        int counter = 1;
        ArrayList<Transaction> transactions1 = transaction;
        for (Transaction item: transactions1){
            if (item.getDate().equals(theStartDate) && item.getDescription().toLowerCase().equals(description)){
                printTransaction(counter, item);
                counter++;
            }else if (item.getDate().equals(theStartDate) && item.getVendor().toLowerCase().equals(description)) {
                printTransaction(counter, item);
                counter++;
            }

        }

    }

    public static void printTransaction(int counter, Transaction item){

        System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                "\n time: " + item.getTime() +
                "\n description: " + item.getDescription() +
                "\n vendor: " + item.getVendor() +
                "\n amount: " + item.getAmount());

    }


    // filter all start date only
    public static void filterStartDate(String date, ArrayList<Transaction> transaction ){
        int counter = 1;
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theStartDate = LocalDate.parse(date, formatter);
        for (Transaction item : transactions1)
        {
            if (item.getDate().equals(theStartDate))
            {
                printTransaction(counter, item);
                counter++;
            }
        }
    }

    // filter by description only
    public static void filter(String description, ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        int counter = 1;
        for (Transaction item : transactions1)
        {
            if (item.getDescription().equals(description))
            {
                printTransaction(counter, item);
                counter++;
            } else if (item.getVendor().equals(description)) {

                printTransaction(counter, item);
                counter++;
            }
        }
    }

    // filtered amount
    public static void filter(float amount, ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        int counter = 1;
        for (Transaction item : transactions1)
        {
            if (item.getAmount() == amount)
            {
                printTransaction(counter, item);
                counter++;
            }
        }
    }

    public static void filter(String description, String vendor, float amount, ArrayList<Transaction> transaction )
    {
        description.trim().toLowerCase();
        vendor.trim().toLowerCase();

        ArrayList<Transaction> transactions1 = transaction;
        int counter = 1;
        for (Transaction item : transactions1)
        {

            if (description.equals(item.getDescription().toLowerCase()) && vendor.equals(item.getVendor().toLowerCase()) && amount == item.getAmount())
            {
                printTransaction(counter, item);
                counter++;
            }else if (description.equals(item.getDescription().toLowerCase()) && vendor.equals(item.getVendor().toLowerCase()) && amount == 0) {
                printTransaction(counter, item);
                counter++;
            } else if (item.getVendor().equals(vendor) && item.getAmount() == amount) {
                printTransaction(counter, item);
                counter++;
            }
        }
    }

    // filtered by  date and amount
    public static void filterRange(String theDate, float amount, ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theEndDate = LocalDate.parse(theDate, formatter);
        int counter = 1;
        for (Transaction item : transactions1)
        {
            if ( theEndDate.equals(item.getDate()) && item.getAmount() == amount )
            {
                printTransaction(counter, item);
                counter++;
            }
        }
    }
    // filter by date (either star/end date), amount, and vendor, description
    public static void filter(String Date, float amount, String vendor, String description ,ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theEndDate = LocalDate.parse(Date, formatter);
        //LocalDate theStartDate = LocalDate.parse(vendor, formatter);
        int counter = 1;
        for (Transaction item : transactions1)
        {
            if ( theEndDate.equals(item.getDate()) && vendor.equals(item.getVendor().toLowerCase()) && description.equals(item.getDescription().toLowerCase()) && item.getAmount() == amount )
            {
                printTransaction(counter, item);
                counter++;
            }
        }
    }
    // filter between start and end date
    public static void filterRange(String startDate, String endDate, String description, ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theStartDate = LocalDate.parse(startDate, formatter);
        LocalDate theEndDate = LocalDate.parse(endDate, formatter);
        int counter = 1;
        for (Transaction item : transactions1)
        {   //
            //  2024-10-13             2023-04-15
            if ( item.getDate().equals(theStartDate) )
            {
                printTransaction(counter, item);
                counter++;
            } else if (!item.getDate().isEqual(theStartDate) && item.getDate().isBefore(theEndDate)) {
                printTransaction(counter, item);
                counter++;
            } else if (item.getDate().equals(theEndDate))
            {
                printTransaction(counter, item);
                counter++;
            } else if (item.getDate().equals(theStartDate) && item.getDescription().toLowerCase().equals(description) || item.getDate().equals(endDate) && item.getDescription().toLowerCase().equals(description)) {
                printTransaction(counter, item);
                counter++;
            }

        }
    }

    public static void main (String[]args) throws IOException, InterruptedException {
        customSearch(scanner, Ledger.getTransaction());

    }
}