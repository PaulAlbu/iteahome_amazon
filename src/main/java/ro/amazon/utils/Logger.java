package ro.amazon.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Logger {

    public static void debugInfo(String text, Throwable error) {
        try {
            File logs = new File("debugLogs_new_version.txt");
            if (logs.createNewFile()) {
                logMessagesInFile(text, error);
            } else {
                logMessagesInFile(text, error);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void logMessagesInFile(String text, Throwable error) throws IOException {
        Files.writeString(
                Path.of(".", "debugLogs_new_version.txt"),
                System.lineSeparator() + text
                        + System.lineSeparator() + Arrays.toString(error.getStackTrace()),
                CREATE, APPEND
        );
    }
}
