package model.exceptions;

public class UnknownClubException extends UnknownError {

    public UnknownClubException(String memberName) {
        super("Le club \"" + memberName + "\" n'existe pas !");
    }

}
