package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class SerializeCommand extends Command {

//Constructor
    public SerializeCommand() {
        super("serialize", "z", "[z]serialize", "Shows game info");
    }

    //se imprime por pantalla el formato con el se va a guardar la partida.
    @Override
    public boolean execute(Game game) {
        System.out.println(game.serialize());
        return false;
    }

    //devuelve el comando si el matchcomand devuelve el true.
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
         return parseNoParamsCommand(commandWords);
    }
}
