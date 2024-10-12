package com.pluralsight;

import java.io.IOException;
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
        System.out.println("Amount:");
        int amount = scanner.nextInt();
        // if not enter value variable not needed in the search


    }

    public static void main(String[] args) throws IOException {
        customSearch(scanner, Ledger.getTransaction());
    }
}
