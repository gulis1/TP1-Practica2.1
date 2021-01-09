package control.Exceptions;


public class DraculaIsAliveException extends CommandExecuteException{
    public DraculaIsAliveException() {
        super("Dracula is already on board\n Failed to add this vampire");
    }

}
