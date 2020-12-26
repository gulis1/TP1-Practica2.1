package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class UpdateCommand extends Command {

    public UpdateCommand() {
        super("none", "n", "[n]one | []", "update");
    }

    //sobre escribe el metodo matchCommandName para tambien incluya como valido el espacio vacio.
    @Override
    protected boolean matchCommandName(String name) {
        return super.matchCommandName(name) || name.equals("");
    }

    //ejecuta el metodo update() del game.
    @Override
    public boolean execute(Game game) {

        game.update();
        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        return parseNoParamsCommand(commandWords);
    }
}

