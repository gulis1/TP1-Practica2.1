package control.Exceptions;

public class InvalidPositionException extends CommandExecuteException {

    // Al constructor se le pasa la posición no válida, así como el objeto que se quería colocar.
      public InvalidPositionException(int x, int y, String type) {
        super(String.format("Position (%d, %d): Invalid position\n Failed to add %s.", x, y, type));

    }


}
