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
    //Guarda la partida con el nombre dado por el usuario(pasado por el constructor).
    @Override
    public boolean execute(Game game) {

        // El archivo se cierra automáticamente al salir del try.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Buffy the Vampire Slayer v3.0\n\n" + game.serialize());


            System.out.println("Game successfully saved.");
        }

        catch (IOException e) {                         //Carita triste
            System.out.println("Error al guardar. \uD83E\uDD1F\uD83D\uDE14");
        }


        return false;
    }

    //Revisa si el comando tiene todos los argumentos necesario(el shortcut y el nombre del archivo).
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