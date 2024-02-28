package com.ua.project.task3;

import com.ua.project.util.helpers.InputHelpers;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> integerList;
        Path pathToFile = InputHelpers.getPathFromInput(" Enter path to file: ");
        integerList =  ArraysFromFile.getIntegerArraysFromFile(pathToFile);

        System.out.println(" Arrays from file \"" + pathToFile.getFileName() + "\":");
        integerList.forEach((integerArray) -> {
            System.out.print("  ");
            integerArray.forEach((integer) -> System.out.print(integer + " "));
            System.out.println();
        });
        System.out.println("-".repeat(15));

        for (int i = 0; i < integerList.size(); i++) {
            System.out.println(" Array " + (i + 1) + ":");
            displayIntegerArrayInfo(integerList.get(i));
        }

        System.out.println(" Overall min: " + getOverallMin(integerList));
        System.out.println(" Overall max: " + getOverallMax(integerList));
        System.out.println(" Overall sum: " + getOverallSum(integerList));
    }

    private static void displayIntegerArrayInfo(List<Integer> list) {
        System.out.println("  Max: " + list.stream().max(Integer::compareTo).orElse(0));
        System.out.println("  Min: " + list.stream().min(Integer::compareTo).orElse(0));
        System.out.println("  Sum: " + list.stream().reduce(0, Integer::sum));
        System.out.println("-".repeat(15));
    }

    private static int getOverallMin(List<List<Integer>> list) {
        int minValue = list.getFirst().stream().min(Integer::compareTo).get();

        for (List<Integer> currentItem : list) {
            int tempMin = currentItem.stream().min(Integer::compareTo).get();

            if(minValue > tempMin){
                minValue = tempMin;
            }
        }

        return minValue;
    }

    private static int getOverallMax(List<List<Integer>> list) {
        int maxValue = list.getFirst().stream().max(Integer::compareTo).get();

        for (List<Integer> currentItem : list) {
            int tempMax = currentItem.stream().max(Integer::compareTo).get();

            if(maxValue < tempMax){
                maxValue = tempMax;
            }
        }

        return maxValue;
    }

    private static int getOverallSum(List<List<Integer>> list) {
        int overallSum = 0;

        for (List<Integer> currentItem : list) {
            overallSum += currentItem.stream().reduce(0, Integer::sum);
        }

        return overallSum;
    }
}
