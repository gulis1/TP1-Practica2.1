package control.Exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {


    public NotEnoughCoinsException(String text) {
        super(text);
    }
}
