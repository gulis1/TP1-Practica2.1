package control.Exceptions;

public class InvalidPositionException extends CommandExecuteException{
    public InvalidPositionException(int x, int y, String text) {
        super(String.format("Position (%d, %d): %s", x, y, text));
    }
}
