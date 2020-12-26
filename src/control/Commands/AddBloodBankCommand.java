package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

public class AddBloodBankCommand extends Command {

    private int x, y, z;

    public AddBloodBankCommand() {
        super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank with cost z in position x, y.");
    }

    // Constructor utilizado para devolver un objeto al CommandGenerator en caso de que coincida con el comando que se introdujo
    public AddBloodBankCommand(int x, int y, int z) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //metodo que ejecuta el comando addBloodBank, si da al algun error lo imprime,devuelve true o false si se pudo poner el BloodBank.
    @Override
    public boolean execute(Game game) {

        String error = game.addBloodBank(x, y, z);

        if (error != null) {
            System.out.println("[ERROR]: " + error);
            return false;
        }

        game.update();
        return true;
    }

    // verifica si no hay ningun fallo en el add (intentando crea un comando AddBloodBankCommand y si este no recoje ninguna exepcion devuelve el comando).
    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length != 4) 
                throw new CommandParseException("[ERROR]: Command "+name+" :"+incorrectNumberOfArgsMsg);


            else {
                try {
                    return new AddBloodBankCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
                }

                catch(NumberFormatException e){
                    throw new CommandParseException("[ERROR]: Command "+name+": NumberFormatException");

                }
            }


        }

        return null;
    }
}
