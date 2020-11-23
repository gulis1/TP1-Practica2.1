package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

    public UpdateCommand() {
        super("update", "", "Pasa al siguiente turno", "update");
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

