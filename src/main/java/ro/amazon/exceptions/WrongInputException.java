package ro.amazon.exceptions;

public class WrongInputException extends Exception {
    public WrongInputException(){
        super("You have have not selected a valid option. Please try again");
    }
    public WrongInputException(Throwable e) {
        super("You have have not selected a valid option. Please try again", e);
    }
}
