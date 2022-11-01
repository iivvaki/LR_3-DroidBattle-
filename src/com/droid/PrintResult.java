package com.droid;

import battle.DroidBattle;

import java.io.*;

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

    public ByteArrayOutputStream StartPrintToFile(){
        // для запису консолі у текстовий документ
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new DroidBattle.TeeOutputStream(System.out, buffer);
        System.setOut(new PrintStream(teeStream));

        return buffer;
    }
    public void EndPrintToFile(ByteArrayOutputStream buffer){
        // Збереження буферу в текстовий документ
        try(OutputStream fileStream = new FileOutputStream("result.txt")){
            buffer.writeTo(fileStream);
            System.out.println(" \n\n The Game is successfully written in file! ");
        } catch(IOException e){
            System.out.println("error");
        }
    }


}


