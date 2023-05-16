package org.example;

import java.math.BigInteger;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String test = "Testujemy stringa";
        BigInteger b;
        byte[] tab = null;
        tab = test.getBytes();
        BigInteger z = new BigInteger(16,new Random());

        b = new BigInteger(tab);
        b = b.multiply(z);
        b = b.divide(z);

        tab = b.toByteArray();


        String test2 = new String(tab);
        System.out.println(test2);
    }
}