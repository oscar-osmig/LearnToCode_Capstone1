package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ascii_visuals {

    public static void display(String File) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
        String line;
        while ( (line = bufferedReader.readLine() ) != null){
            System.out.println(line);
            Thread.sleep(250);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        display("home.txt");
    }
}
