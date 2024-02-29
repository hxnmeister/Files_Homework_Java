package com.ua.project.task5.serialization;

import com.ua.project.task5.Corporation;
import com.ua.project.task5.models.Employee;

import java.io.*;
import java.nio.file.Path;

public class Deserialization {
    public static Corporation deserializeCorporationData(final Path pathToFile) {
        try(InputStream readFile = new FileInputStream(pathToFile.toString());
            ObjectInputStream readObject = new ObjectInputStream(readFile)){
            Object object = readObject.readObject();

            return object instanceof Corporation ? (Corporation) object : null;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Employee deserializeEmployeeData(final Path pathToFile) {
        try(InputStream readFile = new FileInputStream(pathToFile.toString());
            ObjectInputStream readObject = new ObjectInputStream(readFile)){
            Object object = readObject.readObject();

            return object instanceof Employee ? (Employee) object : null;
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
