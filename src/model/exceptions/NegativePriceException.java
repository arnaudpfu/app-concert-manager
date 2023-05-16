package model.exceptions;

public class NegativePriceException extends Exception {
    public NegativePriceException() {
        super("Le prix du concert doit être positif");
    }

}
