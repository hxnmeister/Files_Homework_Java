package com.ua.project.task4;

import com.ua.project.util.helpers.InputHelpers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int arrayLength = 15;
        Random random = new Random();
        Path pathToFile = InputHelpers.getPathFromInput(" Enter path to file: ");
        List<Integer> randomValues = new ArrayList<Integer>();

        System.out.print(" Your array: ");
        for (int i = 0; i < arrayLength; i++) {
            randomValues.add(random.nextInt(0, 35));
            System.out.print(randomValues.get(i) + " ");
        }
        System.out.println();
        System.out.println("-".repeat(50));

        try(Writer fileWriter = new FileWriter(pathToFile.toFile())){
            fileWriter.write(randomValues.toString() + "\n");
            fileWriter.write(randomValues.stream().filter((value) -> value % 2 == 0).toList().toString() + "\n");
            fileWriter.write(randomValues.stream().filter((value) -> value % 2 != 0).toList().toString() + "\n");
            fileWriter.write(randomValues.reversed().toString() + "\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
