package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Payment
{
    private static Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    public static void make_payment(Scanner scanner) throws IOException, InterruptedException {
        LocalTime current_time = local_time();
        LocalDate currenr_date = local_date();
        boolean run = true;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
        String invoice;
        String card;
        float deposit = 0;
        do {    // Maaike help me fix a bug
            System.out.println("\nEnter last 4 digits of your card:");
            card = scanner.nextLine();
            System.out.println("\nEnter Payment description:");
            invoice = scanner.nextLine();
            System.out.println("Payment Amount:");

            try{
                deposit = scanner.nextFloat();
            }catch (InputMismatchException e){
                continue;
            }finally {
                scanner.nextLine();
            }

            if (!invoice.isEmpty() )
            {
                run = false;
                //System.out.println("Both input are correct");
            }

        }while (run);

        System.out.println("Vendor name:");
        String name = scanner.nextLine();
        Transaction transaction = new Transaction(currenr_date, current_time, invoice, name, deposit);

        String todays_transaction = "\n" + transaction.getDate() + "|" + transaction.getTime() + "|" +
                transaction.getDescription() + " " + card + "|" + transaction.getVendor() + "|" +
                (transaction.getAmount() * -1);

        //System.out.println(todays_transaction);
        bufferedWriter.write(todays_transaction);

        bufferedWriter.close();
        System.out.println("* Successful Transaction *");
        System.out.println("\nDo you want to make a new payment? y: yes n: Home Screen");
        String choice = scanner.nextLine();
        switch (choice){case "n": run = false; Main.channel(); break;}

    }

    public static LocalDate local_date(){
        LocalDate day = LocalDate.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//        LocalDate date = LocalDate.parse("2024-10-11", formatter);
        return day;
    }

    public static LocalTime local_time(){
        LocalTime currentime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        return currentime;
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
            make_payment(scanner);
    }
}