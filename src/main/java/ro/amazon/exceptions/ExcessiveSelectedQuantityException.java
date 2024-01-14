package ro.amazon.exceptions;

public class ExcessiveSelectedQuantityException extends Throwable {
    public ExcessiveSelectedQuantityException() {
        super("The quantity selected can not be higher than the one available on our website or lower/ equal to 0.");
    }
}
