package control.Commands;

import logic.Game;

public class  SuperCoinsCommand extends Command {


    public SuperCoinsCommand() {
        super("coins", "c", "[c]oins", "add 1000 coins to the player");
    }

    //ejecuta  el superCoins() en el game.
    @Override
    public boolean execute(Game game) {
        game.superCoins();
        return true;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) {
        return parseNoParamsCommand(commandWords);
    }
}
