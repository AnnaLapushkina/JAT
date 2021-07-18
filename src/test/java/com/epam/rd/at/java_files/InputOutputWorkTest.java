package com.epam.rd.at.java_files;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class InputOutputWorkTest {

    private static final List<String> EXPECTED_TREE_OUTPUT = Arrays.asList(
            "university",
            "├── file.txt",
            "├── floor 1",
            "│   └── room 102",
            "│       └── file.txt",
            "├── floor 2",
            "│   ├── left wing",
            "│   │   ├── kitchen",
            "│   │   │   └── kitchen.txt",
            "│   │   └── room 232",
            "│   └── right wing",
            "│       └── room 252",
            "│           ├── file1.txt",
            "│           └── file2.txt",
            "└── floor 3",
            "    └── room 374",
            "        └── file.txt");

    @BeforeAll
    static void beforeAll() {
        FileSystem fileSystem = Jimfs.newFileSystem(Configuration.windows());
        MockedStatic<Paths> pathsMocked = Mockito.mockStatic(Paths.class);
        pathsMocked.when(() -> Paths.get(anyString(), any())).then(i -> {
            String[] vararg = Stream.of(i.getArguments()).skip(1).map(Object::toString).toArray(String[]::new);
            return fileSystem.getPath(i.getArgument(0), vararg);
        });
    }

    @Test
    public void test() throws IOException {
        InputOutputWork.generateFoldersAndFiles();
        List<String> actualTree = OutputHelper.readOutput(InputOutputWork::printTree);
        assertEquals(EXPECTED_TREE_OUTPUT, actualTree, "printTree output does not match");
    }

}
