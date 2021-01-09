package control.Exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {

    // Al constructor se le pasan el tipo de objeto (Defender, Garlic, Flash), el coste de dicho objeto y la acción que se iba a realizar (add slayer, add bloodbank, lighflash o garlicpush)
    public NotEnoughCoinsException(String type, int cost, String object) {
        super(String.format("%s cost is %d: not enough coins.\n Failed to %s", type, cost, object));
    }

  }
