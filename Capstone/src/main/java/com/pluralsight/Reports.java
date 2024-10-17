package com.pluralsight;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    private static Scanner scanner = new Scanner(System.in);

    public static void board() throws IOException, InterruptedException {
        String spacing1 = "                                                                          ";
        String spacing2 = "                                                                 \n";
        Ascii_visuals.display("reports.txt");
        System.out.println("\n" + spacing1 + "1) Month To Date   " + spacing2 +
                           spacing1 + "2) Previous Month  " + spacing2 +
                           spacing1 + "3) Year To Date    " + spacing2 +
                           spacing1 + "4) Previous Year   " + spacing2 +
                           spacing1 + "5) Search by Vendor" + spacing2 +
                           spacing1 + "6) Custom search   " + spacing2 +
                           spacing1 + "0) Back            " + spacing2);
    }

    public static void channel() throws IOException, InterruptedException
    {
        boolean running = true;
        int choice;
        do {
            board();
            System.out.println("Your choice:");
            try {
                choice = scanner.nextInt();

            }catch (Exception e){
                continue;
            }finally {
                scanner.nextLine();
            }

            //scanner.nextLine();
            switch (choice)
            {
                case 1:
                // add method for displaying
                    get_month_to_date(scanner, Ledger.getTransaction());
                    running = false;
                    break;
                case 2:
                    get_previous_month(scanner, Ledger.getTransaction());
                    running = false;
                    break;
                case 3:
                    get_year_to_date(scanner, Ledger.getTransaction());
                    running = false;
                    break;
                case 4:
                    get_previous_year(scanner, Ledger.getTransaction());
                    running = false;
                    break;
                case 5:
                    //System.out.println("searching");
                    search_by_vendor(scanner, Ledger.getTransaction());
                    running = false;
                    break;
                case 6:
                    CustomSearch2.search(scanner);
                    running = false;
                    break;
                case 0:
                    Ledger.channel();
                    break;
                default:
                    System.out.println("That's not a valid option, try again ...");
            }
        }while(running) ;
    }

    public static void search_by_vendor(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException {
        int counter = 1;
        System.out.println("\nVendor's name:");
        String vendor = scanner.nextLine().toLowerCase();
        for(Transaction transac : transactions) {
            //System.out.println(transac.getVendor());
            if (vendor.equals(transac.getVendor().toLowerCase())){
                System.out.println("\n------ transaction " + counter + " ------ \n" + "\nDate: " +
                        transac.getDate() + "\nTime: " + transac.getTime() + "\nDescription: " +
                        transac.getDescription() + "\nVendor: " + transac.getVendor() + "\nAmount: " +
                        transac.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to reports menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}

    }

    public static void get_previous_month(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException
    {
        int counter = 1;
        ArrayList<Transaction> month = transactions;
        int monthnow = LocalDate.now().getMonthValue();
        for(Transaction t_month : month)
        {
            int currentmonth = t_month.getDate().getMonthValue();
            if( currentmonth == (monthnow-1) ) {
                //found it!
                System.out.println("------ transaction " + counter + " on " + (t_month.getDate().getMonth()) + " ------ \n" + "\nDate: " +
                        t_month.getDate() + "\nTime: " + t_month.getTime() + "\nDescription: " +
                        t_month.getDescription() + "\nVendor: " + t_month.getVendor() + "\nAmount: " +
                        t_month.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to reports menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}
    }

    public static void get_month_to_date(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException
    {
        int counter = 1;
        ArrayList<Transaction> month = transactions;
        int monthnow = LocalDate.now().getMonthValue();
        for(Transaction t_month : month)
        {
            int currentmonth = t_month.getDate().getMonthValue();
            if( currentmonth == monthnow ) {
                //found it!
                System.out.println("------ transaction " + counter + " on " + t_month.getDate().getMonth() + " ------ \n" + "\nDate: " +
                        t_month.getDate() + "\nTime: " + t_month.getTime() + "\nDescription: " +
                        t_month.getDescription() + "\nVendor: " + t_month.getVendor() + "\nAmount: " +
                        t_month.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to reports menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}
    }

    public static void get_previous_year(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException
    {
        int counter = 1;
        ArrayList<Transaction> year = transactions;
        int yearnow = LocalDate.now().getYear();
        for(Transaction this_year : year)
        {
            int currentyear = this_year.getDate().getYear();
            if( currentyear == (yearnow-1) ) {
                //found it!
                System.out.println("------ transaction " + counter + " on " + (yearnow-1) + " ------ \n" + "\nDate: " +
                        this_year.getDate() + "\nTime: " + this_year.getTime() + "\nDescription: " +
                        this_year.getDescription() + "\nVendor: " + this_year.getVendor() + "\nAmount: " +
                        this_year.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to reports menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}
    }

    public static void get_year_to_date(Scanner scanner, ArrayList<Transaction> transactions) throws IOException, InterruptedException
    {
        int counter = 1;
        ArrayList<Transaction> year = transactions;
        int yearnow = LocalDate.now().getYear();
        for(Transaction this_year : year)
        {
            int currentyear = this_year.getDate().getYear();
            if( currentyear == yearnow ) {
                //found it!
                System.out.println("------ transaction " + counter + " on " + yearnow + " ------ \n" + "\nDate: " +
                        this_year.getDate() + "\nTime: " + this_year.getTime() + "\nDescription: " +
                        this_year.getDescription() + "\nVendor: " + this_year.getVendor() + "\nAmount: " +
                        this_year.getAmount() + "\n");
                counter++;
            }
        }
        System.out.println("\n* Press < enter > when ready to get back to reports menu *");
        String input = scanner.nextLine();
        if (input.equals("")){channel();}
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        channel();
    }
}
