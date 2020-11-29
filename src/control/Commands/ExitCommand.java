package control.Commands;

import logic.Game;

public class ExitCommand extends Command{


    public ExitCommand() {
        super("exit", "e", "[e]xit", "exit game");
    }

    //ejecuta el metodo end() del game.
    @Override
    public boolean execute(Game game) {
        game.end();
        game.setWinnerMsg("Player quit the game.");

        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);

    }
}
