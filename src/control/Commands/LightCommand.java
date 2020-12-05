package control.Commands;

import logic.Game;

public class LightCommand extends Command {

    public LightCommand() {
        super("light", "l", "[l]ight", "kills all the vampires");
    }

    @Override
    public boolean execute(Game game) {

        game.lightFlash();
        game.update();
        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);
    }
}
