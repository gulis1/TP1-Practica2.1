package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "h", "[h]elp", "show this help");
    }

    //ejecuta el commandHelp() en el CommandGenerator.
    public boolean execute(Game game) {

        CommandGenerator.commandHelp();

        return false;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) {

        return parseNoParamsCommand(commandWords);
    }
}
