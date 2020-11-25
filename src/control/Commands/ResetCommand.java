package control.Commands;

import logic.Game;

public class ResetCommand extends Command {

    public ResetCommand() {
        super("reset", "r", "[r]eset", "reset game");
    }

    @Override
    public boolean execute(Game game) {
        game.reset();
        return true;
    }

    @Override
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);
    }
}
