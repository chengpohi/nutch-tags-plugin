package org.apache.nutch.parse;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.lang.StringBuilder;
import java.lang.System;
import java.util.ArrayList;
import java.io.*;

public class FileReader {
    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> content = new ArrayList<String>();
        File file = null;
        try {
            file = new File(fileName);
        }catch (Exception e) {
            System.out.println(e.toString());
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new java.io.FileReader(file));
            String str = "";
            System.out.println("Begin to read file ~~~");
            while((str = reader.readLine()) != null) {
                System.out.println(str);
                content.add(str.trim());
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}