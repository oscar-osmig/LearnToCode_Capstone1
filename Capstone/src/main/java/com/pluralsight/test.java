package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws InterruptedException {
        loading("Loading");
    }
    public static void loading(String s){
        System.out.println("");
        for ( int i = 0; i <= 100; i++){
            System.out.print("\r" + s + " " + i + "%");
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                System.out.println("Error " + e);
            }
        }
        System.out.println("");
    }
}
