package control.Commands;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.CommandParseException;
import control.Exceptions.GameException;
import logic.Game;

public class AddCommand extends Command {

    private int x, y;

    public AddCommand() {
        super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
    }

    // Constructor utilizado para devolver un objeto al CommandGenerator en caso de que coincida con el comando que se introdujo
    public AddCommand(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    //metodo que ejecuta el comando add (vease añade el slayer), si da al algun error lo imprime,devuelve true o false si se pudo poner el slayer.
    @Override
    public boolean execute(Game game) throws GameException {

        game.addSlayer(x, y);

        game.update();
        return true;
    }

    // verifica si no hay ningun fallo en el add (intentando crea un comando add y si este no recoje ninguna exepcion devuelve el comando).
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length != 3)
                throw new CommandParseException("[ERROR]: Command "+name+" :"+incorrectNumberOfArgsMsg);

            else {
                try {
                    return new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
                }

                catch(NumberFormatException e){
                    throw new CommandParseException("[ERROR]: Command "+name+": NumberFormatException");
                }
            }


        }

        return null;
    }
}
