package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class ResetCommand extends Command {

    public ResetCommand() {
        super("reset", "r", "[r]eset", "reset game");
    }

    //ejecuta el metodo reset() del game.
    @Override
    public boolean execute(Game game) {
        game.reset();
        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return parseNoParamsCommand(commandWords);
    }
}
