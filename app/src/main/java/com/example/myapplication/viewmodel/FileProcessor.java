package com.example.myapplication.viewmodel;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileProcessor {
    public static String loadFileContent(String fileName) {
        String rezult = "";
        StringBuilder stringBuilder = new StringBuilder();
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(downloadsDir, fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));

            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        rezult = stringBuilder.toString();
        return rezult;
    }
    public static void saveToFile(String fileName, String message){
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(downloadsDir, fileName);
        try {
            FileWriter out = new FileWriter(myFile, true);
            out.write(message+"\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
