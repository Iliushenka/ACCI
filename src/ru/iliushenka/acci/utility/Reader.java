package ru.iliushenka.acci.utility;

import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static String read(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(path)) {
            int value;
            while ((value = fileReader.read()) != -1) {
                stringBuilder.append((char) value);
            }
        } catch (IOException ignore) {
            System.out.println("Файл не найден");
            System.exit(-1);
        }
        return stringBuilder.toString();
    }
}
