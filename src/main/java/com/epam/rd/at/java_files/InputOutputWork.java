package com.epam.rd.at.java_files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputOutputWork {

    public static void generateFoldersAndFiles() throws IOException {
        try {
            Path newFilePath = Paths.get("university/");
            //floor1
            Files.createDirectory(newFilePath);
            Files.createFile(newFilePath.resolve("file.txt"));
            Files.createDirectory(newFilePath.resolve("floor 1"));
            Files.createDirectory(newFilePath.resolve("floor 1/room 102"));
            Files.createFile(newFilePath.resolve("floor 1/room 102/file.txt"));
            //floor2
            Files.createDirectory(newFilePath.resolve("floor 2"));
            Files.createDirectory(newFilePath.resolve("floor 2/left wing"));
            Files.createDirectory(newFilePath.resolve("floor 2/right wing"));
            Files.createDirectory(newFilePath.resolve("floor 2/right wing/room 252"));
            Files.createFile(newFilePath.resolve("floor 2/right wing/room 252/file1.txt"));
            Files.createFile(newFilePath.resolve("floor 2/right wing/room 252/file2.txt"));
            Files.createDirectory(newFilePath.resolve("floor 2/left wing/kitchen"));
            Files.createDirectory(newFilePath.resolve("floor 2/left wing/room 232"));
            Files.createFile(newFilePath.resolve("floor 2/left wing/room 232/file.txt"));
            //floor3
            Files.createDirectory(newFilePath.resolve("floor 3"));
            Files.createDirectory(newFilePath.resolve("floor 3/room 374"));
            Files.createFile(newFilePath.resolve("floor 3/room 374/file.txt"));
        } catch (IOException e) {
            System.out.println("Error IO");
        }
    }

    public static void printTree() throws IOException {
            Path file = Paths.get("university");
            listFileTree(file, 0);
    }

    public static void listFileTree(Path path, int depth) throws IOException {
        if (Files.isDirectory(path)) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            if (depth == 0) {
                System.out.println(addFormat(depth) + path.getFileName());
            } else {
                System.out.println(addFormat(depth) + " " + path.getFileName());
            }

            for (Path tempPath : paths) {
                listFileTree(tempPath, depth + 1);
            }
        } else {
            System.out.println(addFormat(depth) + " " + path.getFileName());
        }
    }

    public static String addFormat(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            if (i == depth - 1 && i != 0) {
                builder.append("└──");
            } else {
                if (i > 0 && i < depth - 1) {
                    builder.append("    ");
                } else {
                    if (depth > 1) {
                        builder.append("│   ");
                    } else {
                        builder.append("├──");
                    }
                }
            }
        }
        return builder.toString();
    }
}

