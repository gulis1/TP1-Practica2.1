package control.Exceptions;

public class InvalidPositionException extends CommandExecuteException {

      public InvalidPositionException(int x, int y, String type) {
        super(String.format("Position (%d, %d): Invalid position\n[ERROR] Failed to add %s.", x, y, type));

    }


}
