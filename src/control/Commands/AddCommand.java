package control.Commands;

import logic.Game;

public class AddCommand extends Command {

    private int x, y;

    public AddCommand() {
        super("add", "a", "Añade un nuevo slayer", "add x y");
    }

    // Constructor utilizado para devolver un objeto al CommandGenerator en caso de que coincida con el comando que se introdujo
    public AddCommand(int x, int y) {
        super("add", "a", "Añade un nuevo slayer", "add x y");
        this.x = x;
        this.y = y;
    }

    public boolean execute(Game game) {
        return game.addSlayer(x, y);
        // Da error porque hay cambiar la forma en que saltan los mensajes de error
    }

    public Command parse(String[] commandWords) {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length != 3) {
                System.err.println(incorrectNumberOfArgsMsg);
                return null;
            }

            else
                return new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));

        }

        return null;

    }
}
