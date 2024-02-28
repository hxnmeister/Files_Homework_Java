package com.ua.project.task1;

import com.ua.project.util.helpers.InputHelpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path firstPathToFile;
        Path secondPathToFile;

        do {
            firstPathToFile = InputHelpers.getPathFromInput(" Enter path to first file: ");
            secondPathToFile = InputHelpers.getPathFromInput(" Enter path to second file: ");

            if(firstPathToFile.equals(secondPathToFile)) {
                System.out.println("\n Paths is the same, please enter different paths!");
                continue;
            }

            try{
                List<String> firstFileData = Files.readAllLines(firstPathToFile);
                List<String> secondFileData = Files.readAllLines(secondPathToFile);

                displayMismatchedLines(firstFileData, secondFileData);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while(firstPathToFile.equals(secondPathToFile));
    }

    private static void displayMismatchedLines(final List<String> firstFileData, final List<String> secondFileData) {
        final int MAX_LENGTH = Math.max(firstFileData.size(), secondFileData.size());

        for (int i = 0; i < MAX_LENGTH; i++) {
            String firstLine = i < firstFileData.size() ? firstFileData.get(i) : null;
            String secondLine = i < secondFileData.size() ? secondFileData.get(i) : null;

            if((firstLine != null && secondLine != null) && !firstLine.equals(secondLine)) {
                System.out.println(" Mismatch in line " + (i + 1) + ": \"" + firstLine + "\" - \"" + secondLine + "\"");
                System.out.println("-".repeat(20));
            }
        }
    }
}
