package control.Commands;

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

    @Override
    public boolean execute(Game game) {
        String error = game.addVampire(letra, x, y);

        if (error != null) {
            System.out.println("[ERROR]: " + error);
            return false;
        }


        return true;
    }

    @Override
    public Command parse(String[] commandWords) {

        if (matchCommandName(commandWords[0])) {

            try {

                if (commandWords.length == 3)
                    return new AddVampireCommand("v", Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));

                else if (commandWords.length == 4)
                    return new AddVampireCommand(commandWords[1].toLowerCase(), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));

                else {
                    System.err.println(incorrectNumberOfArgsMsg);
                    return null;
                }
            }

            catch (NumberFormatException e) {
                return null;
            }

        }

        else
            return null;
    }
}
