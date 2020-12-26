package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class SerializeCommand extends Command {


    public SerializeCommand() {
        super("serialize", "z", "[z]serialize", "Shows game info");
    }

    @Override
    public boolean execute(Game game) {
        System.out.println(game.serialize());
        return false;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
         return parseNoParamsCommand(commandWords);
    }
}
