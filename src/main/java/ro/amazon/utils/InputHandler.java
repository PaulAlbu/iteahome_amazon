package ro.amazon.utils;

import ro.amazon.exceptions.WrongInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    public static int validateAndReturnIntegerInput(Scanner scanner) throws WrongInputException {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new WrongInputException(e);
        }

    }
}

