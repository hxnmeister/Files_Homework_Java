package com.ua.project.util.helpers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelpers {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Path getPathFromInput(final String inputMessage) {
        Path inputPath;

        while(true) {
            System.out.print(inputMessage);
            inputPath = Paths.get(SCANNER.nextLine());

            if(Files.exists(inputPath)){
                return inputPath;
            }

            System.out.println("\n Please enter correct path to file!\n");
        }
    }

    public static int getPositiveIntegerInput(final String inputMessage) {
        int inputInteger;

        while(true) {
            try {
                System.out.print(inputMessage);
                inputInteger = SCANNER.nextInt();
                SCANNER.nextLine();

                if(inputInteger < 0){
                    throw new InputMismatchException();
                }

                return inputInteger;
            }
            catch(InputMismatchException e) {
                System.out.println("\n Please enter a number!");
            }
            catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getStringInputByLength(final String inputMessage, final int minLength) {
        String stringInput = "";

        while(true) {
            try{
                System.out.print(inputMessage);
                stringInput = SCANNER.nextLine();

                if(stringInput.length() < minLength){
                    throw new InputMismatchException();
                }

                return stringInput;
            }
            catch(InputMismatchException e) {
                System.out.print("\n Too short input \"" + stringInput + "\"");
                System.out.println(" Min length: " + minLength + " chars!");
            }
            catch(RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
