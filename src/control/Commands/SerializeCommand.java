package control.Commands;

import logic.Game;

public class SerializeCommand extends Command {


    public SerializeCommand() {
        super("serialize", "z", "Shows game info", "[z]serialize");
    }

    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
         return parseNoParamsCommand(commandWords);
    }
}
