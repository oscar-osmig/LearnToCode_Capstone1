package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws InterruptedException {
        loading();
    }
    public static void loading(){
        for ( int i = 0; i <= 100; i++){
            System.out.print("\rLoading " + i + "%");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error " + e);
            }
        }
    }
}
