package model.exceptions;

public class PassedDateException extends Exception {
    public PassedDateException() { super("La date est déjà passée !"); }
}
