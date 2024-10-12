package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Ledger {
    private static Scanner scanner = new Scanner(System.in);

    public static void board() throws IOException, InterruptedException
    {
        Ascii_visuals.display("ledger.txt");
        System.out.println("\n A) All transactions" +
                           "\n D) Deposits" +
                           "\n P) Payments" +
                           "\n R) Reports" +
                           "\n H) Home");

    }

    public static void channel() throws IOException, InterruptedException {
        boolean running = true;
        do {
            board();
            System.out.println("Your choice:");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "A": case "a":
                // add method for displaying
                    displayAllTransaction();
                    running = false;
                    break;
                case "D": case "d":
                    display_deposits(scanner, getTransaction());
                    running = false;
                    break;
                case "P": case "p":
                    display_payments(scanner, getTransaction());
                    running = false;
                    break;
                case "R": case "r":
                    running = false;
                    break;
                case "H": case "h":
                    Main.channel();
                    break;
                default:
                    System.out.println("That's not a valid option, try again ...");
            }
        }while(running) ;
    }

    public static ArrayList<Transaction> getTransaction() throws IOException
    { // returns a list type Product
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv")); // reads lines in provided file
        bufferedReader.readLine();
        String input;

        while((input = bufferedReader.readLine()) != null){ // while the line is not the last in file it continues
            String[] category = input.split(Pattern.quote("|")); // splits the lines by | and store each element in a string array
            Transaction transaction1 = new Transaction(LocalDate.parse(category[0]), LocalTime.parse(category[1]), category[2], category[3], Float.parseFloat(category[4])); // make a new product with the parts of the array we made
            transactions.add(transaction1);
        }
        Collections.sort(transactions, Comparator.comparing(Transaction::getDate));
        return transactions;
    }

    public static void displayAllTransaction() throws IOException, InterruptedException {
        ArrayList<Transaction> data_in_file = getTransaction();
        int counter = 1;
        for (int i = 0; i < data_in_file.size(); i++)
        {
            Transaction p = data_in_file.get(i);
            System.out.println("------ transaction " + counter + " ------ \n" + p.getDate() + "\nTime: " + p.getTime() + "\nDescription: " +
                    p.getDescription() + "\nVendor: " + p.getVendor() + "\nAmount: " + p.getAmount() + "\n");
            counter++;
        }
        System.out.println("\n* Press < enter > when ready to get back to menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}
    }

    public static void display_deposits(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException {
        int counter = 1;
        for(Transaction deposits : transactions)
        {
            if(deposits.getAmount() > 0) {
                //found it!
                System.out.println("------ deposit " + counter + " ------ \n" + "\nDate: " + deposits.getDate() + "\nTime: " + deposits.getTime() + "\nDescription: " +
                        deposits.getDescription() + "\nVendor: " + deposits.getVendor() + "\nAmount: " + deposits.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}
    }

    public static void display_payments(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException {
        int counter = 1;
        for(Transaction deposits : transactions)
        {
            if(deposits.getAmount() < 0) {
                //found it!
                System.out.println("------ payment " + counter + " ------ \n" + "\nDate: " + deposits.getDate() + "\nTime: " + deposits.getTime() + "\nDescription: " +
                        deposits.getDescription() + "\nVendor: " + deposits.getVendor() + "\nAmount: " + deposits.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to menu *");
        String input = scanner.nextLine();
        if (input.equals("")){Main.channel();}
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        channel();
    }
}
