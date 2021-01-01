package control.Exceptions;

public class NoMoreVampiresException extends CommandExecuteException {

    public NoMoreVampiresException() {
        super("No more remaining vampires left");
    }
}
