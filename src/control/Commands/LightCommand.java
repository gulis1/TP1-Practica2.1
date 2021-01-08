package control.Commands;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.CommandParseException;
import control.Exceptions.GameException;
import logic.Game;

public class LightCommand extends Command {

    public LightCommand() {
        super("light", "l", "[l]ight", "kills all the vampires");
    }

    //ejecuta el lightFlash si este devuleve true actualiza el tablero.
    @Override
    public boolean execute(Game game) throws CommandExecuteException {

        game.lightFlash();
        game.update();
        return true;


    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return parseNoParamsCommand(commandWords);
    }
}
