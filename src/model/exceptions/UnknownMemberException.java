package model.exceptions;

public class UnknownMemberException extends UnknownError {

    public UnknownMemberException(String memberName) {
        super("Le membre \"" + memberName + "\" n'existe pas !");
    }

}
