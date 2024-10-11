package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ascii_visuals {

    public static void displayAppTitle() throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("title.txt"));
        String line;
        while ( (line = bufferedReader.readLine() ) != null){
            System.out.println(line);
            Thread.sleep(250);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        displayAppTitle();
    }
}
