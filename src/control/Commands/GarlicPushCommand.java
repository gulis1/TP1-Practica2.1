package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class GarlicPushCommand extends Command{

    public GarlicPushCommand() {
        super("garlic", "g", "[g]arlic ", "pushes back vampires");
    }

    //ejecuta el garlicPush si este devuleve true actualiza el tablero.
    @Override
    public boolean execute(Game game) {

        if (game.garlicPush()) {
            game.update();
            return true;
        }

        else {
            System.out.println("[ERROR]: Not enough coins");
            return false;
        }
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return parseNoParamsCommand(commandWords);
    }
}
