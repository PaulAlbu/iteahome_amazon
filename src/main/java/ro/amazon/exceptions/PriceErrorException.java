package ro.amazon.exceptions;

public class PriceErrorException extends Exception{
    public PriceErrorException() {
        super("The price of this product is not displayed right. Please update!");
    }
}
