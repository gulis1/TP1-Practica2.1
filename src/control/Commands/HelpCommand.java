package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "h", "Muestra ayuda sobre el juego", "help");
    }

    public boolean execute(Game game) {

        CommandGenerator.commandHelp();

        return true;
    }

    @Override
    public Command parse(String[] commandWords) {

        return parseNoParamsCommand(commandWords);
    }
}
