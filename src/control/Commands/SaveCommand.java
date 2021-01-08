package control.Commands;

import control.Exceptions.CommandParseException;
import logic.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command{

    private String filename;

    public SaveCommand() {
        super("save", "s", "[s]ave <filename>", "Save the game");
    }

    public SaveCommand(String filename){
       this();
       this.filename= filename + ".dat";
    }
    @Override
    public boolean execute(Game game) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(game.serialize());
            writer.close();

            System.out.println("Game succesfully saved.");
        }

        catch (IOException e) {                         //Carita triste
            System.out.printf("Error al guardar. \uD83E\uDD1F\uD83D\uDE14\n");
        }


        return false;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length == 2)
                return new SaveCommand(commandWords[1]);

            else {
                throw new CommandParseException("Command "+name+": "+incorrectNumberOfArgsMsg);
            }

        }

        else
            return null;
    }
}