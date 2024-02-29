package com.ua.project.task5.serialization;

import com.ua.project.task5.Corporation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Serialization {
    public static <T extends Serializable> void serializeData(final T serializationData, final Path pathToFile, final boolean isAppend) {
        try(OutputStream writeFile = new FileOutputStream(pathToFile.toFile(), isAppend);
            ObjectOutputStream writeObject = new ObjectOutputStream(writeFile)){
            writeObject.writeObject(serializationData);
            writeFile.write("\n".getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
