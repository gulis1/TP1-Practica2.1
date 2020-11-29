package control.Commands;

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

    //metodo que ejecuta el comando add (vease añade el vampiro), si da al algun error lo imprime,devuelve true o false si se pudo poner el slayer.
    public boolean execute(Game game) {

        String error = game.addSlayer(x, y);

        if (error != null) {
            System.out.println(error);
            return false;
        }

        game.update();
        return true;
    }

    // verifica si no hay ningun fallo en el add (intentando crea un comando add y si este no recoje ninguna exepcion devuelve el comando).
    public Command parse(String[] commandWords) {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length != 3) {
                System.err.println(incorrectNumberOfArgsMsg);
                return null;
            }

            else {
                try {
                    return new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
                }

                catch(NumberFormatException e){
                    return null;
                }
            }
        }

        return null;
    }
}
