package control.Exceptions;

public class InvalidTypeException extends CommandExecuteException{
    public InvalidTypeException() {
        super("Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");
    }
}
