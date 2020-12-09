package control.Commands;

import logic.Game;

public class AddBloodBankCommand extends Command {

    private int x, y, z;

    public AddBloodBankCommand() {
        super("bank", "b", "[b]ank <x><y><z>", "add a blood bank in position x y");
    }

    // Constructor utilizado para devolver un objeto al CommandGenerator en caso de que coincida con el comando que se introdujo
    public AddBloodBankCommand(int x, int y, int z) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //metodo que ejecuta el comando add (vease añade el vampiro), si da al algun error lo imprime,devuelve true o false si se pudo poner el slayer.
    @Override
    public boolean execute(Game game) {

        String error = game.addBloodBank(x, y, z);

        if (error != null) {
            System.out.println(error);
            return false;
        }

        game.update();
        return true;
    }

    // verifica si no hay ningun fallo en el add (intentando crea un comando add y si este no recoje ninguna exepcion devuelve el comando).
    @Override
    public Command parse(String[] commandWords) {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length != 4) {
                System.err.println(incorrectNumberOfArgsMsg);
                return null;
            }

            else {
                try {
                    return new AddBloodBankCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
                }

                catch(NumberFormatException e){
                    return null;
                }
            }


        }

        return null;
    }
}
