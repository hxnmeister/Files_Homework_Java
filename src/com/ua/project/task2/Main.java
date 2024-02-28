package com.ua.project.task2;

import com.ua.project.util.helpers.InputHelpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try{
            Path pathToFile = InputHelpers.getPathFromInput(" Enter path to file: ");
            String longestLineInFile = getLongestLineInFile(pathToFile);

            displayLongestLineInFile(longestLineInFile, pathToFile.getFileName().toString());
        }
        catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayLongestLineInFile(final String longestLineInFile, final String fileName) {
        System.out.println(" Longest line in file \"" + fileName + "\": " + longestLineInFile);
        System.out.println(" It`s length: " + longestLineInFile.length());
        System.out.println("-".repeat(20));
    }

    private static String getLongestLineInFile(final Path pathToFile) {
        try(Stream<String> fileData = Files.lines(pathToFile)) {
            return fileData.max(Comparator.comparing(String::length)).orElse("No Data!");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
