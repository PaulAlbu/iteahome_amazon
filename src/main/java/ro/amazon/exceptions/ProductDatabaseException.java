package ro.amazon.exceptions;

public class ProductDatabaseException extends Exception {
    public ProductDatabaseException(){
        super("We are updating our database. We shall be live soon!");
    }

    public ProductDatabaseException(Throwable e){
        super("We are updating our database. We shall be live soon!", e);
    }

}
