package control.Commands;

import logic.Game;

public class LightCommand extends Command {

    public LightCommand() {
        super("light", "l", "[l]ight", "kills all the vampires");
    }

    //ejecuta el lightFlash si este devuleve true actualiza el tablero.
    @Override
    public boolean execute(Game game) {

        if (game.lightFlash()) {
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
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);
    }
}
