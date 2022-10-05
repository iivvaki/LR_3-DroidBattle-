package com.droid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrintResult {
    public static String FILE = "result.txt";
    public void PrintFight() {

        try { // open file
            BufferedReader reader = new BufferedReader(new FileReader(FILE));

            while (true) {

                String line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                } else break;
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Cant open: " + FILE);
        }
    }
}

