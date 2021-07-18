package com.epam.rd.at.java_files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputHelper {

    public static List<String> readOutput(Action action) throws IOException {
        PrintStream systemOut = System.out;
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        try {
            action.callAction();
            String result = testOut.toString();
            systemOut.println(result);
            return Stream.of(result.split("\r?\n"))
                    .filter(s -> !s.isEmpty()).collect(Collectors.toList());
        } finally {
            System.setOut(systemOut);
        }
    }

    public interface Action {

        void callAction() throws IOException;
    }

}
