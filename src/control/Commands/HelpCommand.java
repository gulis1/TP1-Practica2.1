package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "h", "[h]elp", "show this help");
    }

    //ejecuta el commandHelp() en el CommandGenerator.
    @Override
    public boolean execute(Game game) {

        CommandGenerator.commandHelp();

        return false;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        return parseNoParamsCommand(commandWords);
    }
}
