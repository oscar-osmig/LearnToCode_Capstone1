package com.pluralsight;

import javax.swing.tree.TreeNode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.CustomSearch.customSearch;

// Maaike gave me insights and hints to make the custom search app better
public class CustomSearch2 {
    private static Scanner scanner = new Scanner(System.in);

 //   private static ArrayList<Transaction> transactions; = Ledger.getTransaction();


    public static void search(Scanner scanner) throws IOException, InterruptedException {
        Ascii_visuals.display("customSearch.txt");
        //System.out.println("* Press <enter> to live category blank *");
        ArrayList<Transaction> transactions = Ledger.getTransaction();
        ArrayList<Transaction> transac = Ledger.getTransaction();
        // start date
        System.out.println("Start date: (yyyy-mm-dd)");
        String startDate = scanner.nextLine();
        // end date
        System.out.println("End date: (yyyy-mm-dd)");
        String endDate = scanner.nextLine();

        // make sure the date is able to parse to LocalDate



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
        test.loading("Searching");

        if(!startDate.isEmpty()) {
            transac = filterStartDate(LocalDate.parse(startDate), transactions);
        }
        if(!endDate.isEmpty()) {
            transac = filterEndDate(LocalDate.parse(endDate), transactions);
        }
        if (!description.isEmpty()){
            transac = filterDescription(description, transactions);
        }
        if (!vendor.isEmpty()){
            transac = filterVendor(vendor, transactions);
        }
        if (amount != 0){
            transac = filterAmount(amount, transactions);

        }
        int counter = 1;
        for ( Transaction t : transac){
            CustomSearch.printTransaction(counter, t);
            counter++;
        }

        System.out.println("\n* Press <enter> to reset or enter 0 to go back to reports *");
        String choice = scanner.nextLine();
        if(choice.equals("")){
            Thread.sleep(100);
            search(scanner);
        } else if (choice.equals("0")) {
            Reports.channel();
        }else {search(scanner); }


    }

    private static boolean checkIfDateFormat(String date) {
        String format = "yyyy-MM-dd";
        //LocalDate theStartDate = LocalDate.parse(input, formatter);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try{
            Date parseDate = sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

    public static ArrayList<Transaction> filterStartDate(LocalDate startDate, ArrayList<Transaction> transactions){
        ArrayList<Transaction> newList = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getDate().isAfter(startDate) || t.getDate().equals(startDate)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static ArrayList<Transaction> filterEndDate(LocalDate endDate, ArrayList<Transaction> transactions){
        ArrayList<Transaction> newList = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getDate().isBefore(endDate) || t.getDate().equals(endDate)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static ArrayList<Transaction> filterDescription(String description, ArrayList<Transaction> transactions){
        ArrayList<Transaction> newList = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getDescription().toLowerCase().equals(description)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static ArrayList<Transaction> filterVendor(String vendor, ArrayList<Transaction> transactions){
        ArrayList<Transaction> newList = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getDescription().toLowerCase().equals(vendor)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static ArrayList<Transaction> filterAmount(float amount, ArrayList<Transaction> transactions){
        ArrayList<Transaction> newList = new ArrayList<>();
        for(Transaction t : transactions) {
            if(t.getAmount() == amount) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static void main(String[] args)  {
        try {
            search(scanner);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
