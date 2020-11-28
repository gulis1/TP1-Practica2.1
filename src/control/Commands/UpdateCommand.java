package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

    public UpdateCommand() {
        super("nonexx", "n", "[n]one | []", "update");
    }

    @Override
    protected boolean matchCommandName(String name) {
        return super.matchCommandName(name) || name.equals("");
    }

    public boolean execute(Game game) {

        game.update();
        return true;
    }

    @Override
    public Command parse(String[] commandWords) {

        return parseNoParamsCommand(commandWords);
    }
}

