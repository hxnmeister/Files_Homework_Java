package com.ua.project.task3;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArraysFromFile {
    public static List<List<Integer>> getIntegerArraysFromFile(final Path pathToFile) {
        List<List<Integer>> integerList = new ArrayList<List<Integer>>(List.of(
                new ArrayList<Integer>()
        ));

        try(Reader fileReader = new FileReader(pathToFile.toFile())) {
            int currentSymbol;
            StringBuilder tempNumber = new StringBuilder();

            while((currentSymbol = fileReader.read()) >= 0){
                switch((char) currentSymbol) {
                    case '\n':
                        integerList.add(new ArrayList<Integer>());
                        break;
                    case ' ', '\r':
                        integerList.getLast().add(Integer.parseInt(tempNumber.toString()));
                        tempNumber.setLength(0);
                        break;
                    default:
                        tempNumber.append((char)currentSymbol);
                        break;
                }
            }

            if(!tempNumber.isEmpty()) {
                integerList.getLast().add(Integer.parseInt(tempNumber.toString()));
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return integerList;
    }
}
