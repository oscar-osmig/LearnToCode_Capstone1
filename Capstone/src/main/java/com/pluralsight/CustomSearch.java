package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomSearch {
    static Scanner scanner = new Scanner(System.in);

    public static void customSearch(Scanner scanner, ArrayList<Transaction> transactions){

        System.out.println("* Press <enter> to live category blank *");
        // start date
        System.out.println("Start date: (yyyy-mm-dd)");
        String startDate = scanner.nextLine();
        // end date
        System.out.println("End date: (yyyy-mm-dd)");
        String endDate = scanner.nextLine();
        // Description
        System.out.println("Description:");
        String description = scanner.nextLine();
        // vendor
        System.out.println("Vendor:");
        String vendor = scanner.nextLine();
        // amount
        System.out.println("Amount: ( enter 0 to leave blank)");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        // if not enter value variable not needed in the search

        ArrayList<Transaction> transaction = transactions;
        //LocalDate theEndDate =  LocalDate.parse(endDate, formatter);
        // check is input date is after start day
        if (startDate.isEmpty() && endDate.isEmpty() && description.isEmpty() && vendor.isEmpty()) // filter by amount
        {
            System.out.println("* filtered by amount *");
            filter(amount, transaction);
        }else if (startDate.isEmpty() && description.isEmpty() && vendor.isEmpty()){ // filter by end-date and amount
            System.out.println("* filtered by end-date and amount ");
            filter(endDate, amount,transaction);
        } else if ( endDate.isEmpty() && startDate.isEmpty() && vendor.isEmpty() ) { // filter by description only
            System.out.println("* filtered by start-date and amount ");
            filter(description, transaction);
        } else if (startDate.isEmpty() && endDate.isEmpty() && amount == 0) { // filter by description and vendor
            System.out.println("* filtered by description and vendor *");
            filter(description, vendor, amount, transaction);
        } else if (description.isEmpty() && vendor.isEmpty()) { // display between start-date and end-date
            System.out.println("* filtered from start-date to end-date *");
            filter(startDate, endDate,  transaction);
        } else if (startDate.isEmpty()) { // filter by end-date, vendor, amount
            System.out.println("* filter by date, vendor, and amount *");
            filter(endDate, amount,description ,vendor, transaction);
        }else if (endDate.isEmpty()) { // filter by start-date, vendor, amount
            System.out.println("* filter by date, vendor, and amount *");
            filter(startDate, amount, description,vendor, transaction);
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
                System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                        "\n time: " + item.getTime() +
                        "\n description: " + item.getDescription() +
                        "\n vendor: " + item.getVendor() +
                        "\n amount: " + item.getAmount());
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
                System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                        "\n time: " + item.getTime() +
                        "\n description: " + item.getDescription() +
                        "\n vendor: " + item.getVendor() +
                        "\n amount: " + item.getAmount());
                counter++;
            }
        }
    }

    public static void filter(String description, String vendor, float amount, ArrayList<Transaction> transaction )
    {
        description.trim().toLowerCase();
        vendor.trim().toLowerCase();
        System.out.println(description);
        System.out.println(vendor);
        ArrayList<Transaction> transactions1 = transaction;
        int counter = 1;
        for (Transaction item : transactions1)
        {

            if (description.equals(item.getDescription().trim()) && vendor.equals(item.getVendor().trim()) && amount == 0)
            {
                System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                        "\n time: " + item.getTime() +
                        "\n description: " + item.getDescription() +
                        "\n vendor: " + item.getVendor() +
                        "\n amount: " + item.getAmount());
                counter++;
            }
        }
    }

    // filtered by  date and amount
    public static void filter(String Date, float amount, ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theEndDate = LocalDate.parse(Date, formatter);
        int counter = 1;
        for (Transaction item : transactions1)
        {
            if ( theEndDate.equals(item.getDate()) && item.getAmount() == amount )
            {
                System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                        "\n time: " + item.getTime() +
                        "\n description: " + item.getDescription() +
                        "\n vendor: " + item.getVendor() +
                        "\n amount: " + item.getAmount());
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
        int counter = 1;
        for (Transaction item : transactions1)
        {
            if ( theEndDate.equals(item.getDate()) && vendor.equals(item.getVendor()) && description.equals(item.getDescription()) && item.getAmount() == amount )
            {
                System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                        "\n time: " + item.getTime() +
                        "\n description: " + item.getDescription() +
                        "\n vendor: " + item.getVendor() +
                        "\n amount: " + item.getAmount());
                counter++;
            }
        }
    }
    // filter between start and end date
    public static void filter(String startDate, String endDate, ArrayList<Transaction> transaction )
    {
        ArrayList<Transaction> transactions1 = transaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate theStartDate = LocalDate.parse(startDate, formatter);
        LocalDate theEndDate = LocalDate.parse(endDate, formatter);
        int counter = 1;

        for (Transaction item : transactions1)
        {   //
                //  2024-10-13             2023-04-15
                if ( (theStartDate.equals(item.getDate())) )
                {
                    System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                            "\n time: " + item.getTime() +
                            "\n description: " + item.getDescription() +
                            "\n vendor: " + item.getVendor() +
                            "\n amount: " + item.getAmount());
                    counter++;
                } else if (theStartDate.isBefore(item.getDate()) && theEndDate.isAfter(item.getDate())) {
                    System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                            "\n time: " + item.getTime() +
                            "\n description: " + item.getDescription() +
                            "\n vendor: " + item.getVendor() +
                            "\n amount: " + item.getAmount());
                    counter++;
                } else if (theEndDate.equals(item.getDate())) {
                    System.out.println("\n----- TRANSACTION " + counter + " -----\n" + " date: " + item.getDate() +
                            "\n time: " + item.getTime() +
                            "\n description: " + item.getDescription() +
                            "\n vendor: " + item.getVendor() +
                            "\n amount: " + item.getAmount());
                    counter++;
                }

        }
    }

        public static void main (String[]args) throws IOException
        {
            customSearch(scanner, Ledger.getTransaction());

        }
    }
