package control.Commands;

import logic.Game;

public class ExitCommand extends Command{


    public ExitCommand() {
        super("exit", "e", "[e]xit", "exit game");
    }


    @Override
    public boolean execute(Game game) {
        game.end();
        game.setWinnerMsg("Player quit the game.");

        return true;
    }

    @Override
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);
    }
}
