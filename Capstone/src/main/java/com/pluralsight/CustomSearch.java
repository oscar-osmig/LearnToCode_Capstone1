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
        ArrayList<Transaction> transactionDate = transactions;

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
        System.out.println("Amount:");
        int amount = scanner.nextInt();
        // if not enter value variable not needed in the search
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //formatting the input date
        LocalDate theStartDate = LocalDate.parse(startDate, formatter);
        //LocalDate theEndDate =  LocalDate.parse(endDate, formatter);
        // check is input date is after start day

        for(Transaction dateToCompare : transactionDate){
            boolean isBeforeStartDate = theStartDate.isBefore(dateToCompare.getDate());
            System.out.println(isBeforeStartDate);
        }


    }

    public static void main(String[] args) throws IOException {
        customSearch(scanner, Ledger.getTransaction());
    }
}
