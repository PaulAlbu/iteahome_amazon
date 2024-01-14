package ro.amazon.utils;

import ro.amazon.exceptions.WrongInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    public static int validateAndReturnIntegerInput(Scanner scanner) throws WrongInputException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            throw new WrongInputException(e);
        }

    }
}

