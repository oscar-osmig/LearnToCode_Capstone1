package com.pluralsight;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    public static void display_home() throws IOException, InterruptedException
    {
        String spacing1 = "                                                                   ";
        String spacing2 = "                                                                  \n";
        Ascii_visuals.display("home.txt");
        System.out.println("\n" + spacing1 +
                "D) Add deposit           " + spacing2 +
                spacing1 + "P) Make a payment (debit)" + spacing2 + spacing1 +
                "L) Ledger                " + spacing2 + spacing1 +
                "X) Quit                  " + spacing2);

    }

    public static void channel() throws IOException, InterruptedException {
        do {
            display_home();
            System.out.println("Your choice:");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "D": case "d":
                // add method for depositing
                Deposit.add_deposit(scanner);
                    running = false;
                    break;
                case "P": case "p":
                    Payment.make_payment(scanner);
                    running = false;
                    break;
                case "L": case "l":
                    Ledger.channel();
                    running = false;
                    break;
                case "x": case "X":
                    Ascii_visuals.display("bye.txt");
                    Ascii_visuals.display("meme.txt");
                    running = false;
                    break;
                default:
                    System.out.println("That's not a valid option, try again ...");
            }
        }while(running) ;
    }


    public static void main(String[] args) throws IOException, InterruptedException
    {
        channel();
    }

}
