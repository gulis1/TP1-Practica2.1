package control.Commands;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.CommandParseException;
import control.Exceptions.GameException;
import logic.Game;

public class GarlicPushCommand extends Command{

    public GarlicPushCommand() {
        super("garlic", "g", "[g]arlic ", "pushes back vampires");
    }

    //ejecuta el garlicPush si este devuleve true actualiza el tablero.
    @Override
    public boolean execute(Game game) throws CommandExecuteException {

        game.garlicPush();
        game.update();
        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return parseNoParamsCommand(commandWords);
    }
}
