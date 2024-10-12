package com.pluralsight;

import java.io.IOException;
import java.util.Scanner;

public class Reports {
    private static Scanner scanner = new Scanner(System.in);

    public static void board() throws IOException, InterruptedException {
        Ascii_visuals.display("reports.txt");
        System.out.println("\n 1) Month To Date" +
                           "\n 2) Previous Month" +
                           "\n 3) Year To Date" +
                           "\n 4) Previous Year" +
                           "\n 5) Search by Vendor" +
                           "\n 0) Back");
    }

    public static void channel() throws IOException, InterruptedException
    {
        boolean running = true;
        do {
            board();
            System.out.println("Your choice:");
            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                // add method for displaying

                    running = false;
                    break;
                case 2:

                    running = false;
                    break;
                case 3:

                    running = false;
                    break;
                case 4:
                    running = false;
                    break;
                case 5:

                    break;
                case 0:
                    Ledger.channel();
                    break;
                default:
                    System.out.println("That's not a valid option, try again ...");
            }
        }while(running) ;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        channel();
    }
}
