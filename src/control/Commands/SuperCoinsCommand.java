package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class  SuperCoinsCommand extends Command {


    public SuperCoinsCommand() {
        super("coins", "c", "[c]oins", "add 1000 coins");
    }

    // Ejecuta  el superCoins() en el game.
    @Override
    public boolean execute(Game game) {
        game.addCoinsToPlayer(1000);
        return true;
    }

    // Devuelve el comando si el match command devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        return parseNoParamsCommand(commandWords);
    }
}
