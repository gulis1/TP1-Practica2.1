package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "h", "[h]elp", "show this help");
    }

    public boolean execute(Game game) {

        CommandGenerator.commandHelp();

        return false;
    }

    @Override
    public Command parse(String[] commandWords) {

        return parseNoParamsCommand(commandWords);
    }
}
