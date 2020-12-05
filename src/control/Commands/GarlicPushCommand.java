package control.Commands;

import logic.Game;

public class GarlicPushCommand extends Command{

    public GarlicPushCommand() {
        super("garlic", "g", "[g]arlic", "pushes back vampires");
    }


    @Override
    public boolean execute(Game game) {

            game.garlicPush();
            game.update();
        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);
    }
}
