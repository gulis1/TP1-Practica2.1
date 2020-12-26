package control.Exceptions;

public class InvalidTypeException extends CommandExecuteException{
    public InvalidTypeException(String text) {
        super(text);
    }
}
