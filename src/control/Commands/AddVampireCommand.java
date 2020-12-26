package control.Commands;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.CommandParseException;
import control.Exceptions.GameException;
import logic.Game;

import java.util.Locale;

public class AddVampireCommand extends Command{
    private int x;
    private int y;
    private String letra;

    public AddVampireCommand() {
        super("vampire", "v", "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}", "add a vampire in position x, y");
    }

    // Constructor utilizado para devolver un objeto al CommandGenerator en caso de que coincida con el comando que se introdujo
    public AddVampireCommand(String letra, int x, int y) {
        this();
        this.x = x;
        this.y = y;
        this.letra=letra;
    }

    //metodo que ejecuta el comando addVampire (vease añade el vampiro), si da al algun error lo imprime,devuelve true o false si se pudo poner el vampiro.
    @Override
    public boolean execute(Game game) throws GameException {

        game.addVampire(letra, x, y);

        return true;
    }

    // verifica si no hay ningun fallo en el add (intentando crea un comando add y si este no recoge ninguna exepcion devuelve el comando).
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (matchCommandName(commandWords[0])) {

            try {

                if (commandWords.length == 3)
                    return new AddVampireCommand("v", Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));

                else if (commandWords.length == 4)
                    return new AddVampireCommand(commandWords[1].toLowerCase(), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));

                else {
                    throw new CommandParseException("[ERROR]: Command "+name+" :"+incorrectNumberOfArgsMsg);
                }
            }

            catch (NumberFormatException e) {
                throw new CommandParseException("[ERROR]: Command "+name+": NumberFormatException");
            }

        }

        else
            return null;
    }
}
