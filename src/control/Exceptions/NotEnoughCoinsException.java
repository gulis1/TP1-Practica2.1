package control.Exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {


    public NotEnoughCoinsException(String type, int cost, String object) {
        super(String.format("%s cost is %d: not enough coins.\n[ERROR] Failed to %s", type, cost, object));
    }

  }
