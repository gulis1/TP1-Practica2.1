package control.Commands;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.CommandParseException;
import logic.Game;

import java.io.*;


public class LoadCommand extends Command {
    private String filename;

    public LoadCommand() {
        super("Load", "i", "[l]oad <filename>", "load a game from a file");
    }

    public LoadCommand(String filename){
        this();
        this.filename = filename;
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {
        StringBuilder partida = new StringBuilder();
        String linea;

        game.reset();

        // El archivo se cierra automáticamente al salir del try.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            linea = reader.readLine();


            if (linea.equals("Buffy the Vampire Slayer v3.0")) {
                linea = reader.readLine();

                while(linea!=null){
                    linea = reader.readLine();
                    if (linea != null && !linea.equals(""))
                        partida.append(linea).append("\n");

                }

                game.load(partida.toString());



            }

        }

        catch (IOException e) {                         //Carita triste
            System.out.println("Error al cargar. \uD83E\uDD1F\uD83D\uDE14");
        }



        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (matchCommandName(commandWords[0])) {

            if (commandWords.length == 2)
                return new LoadCommand(commandWords[1]);

            else {
                throw new CommandParseException("Command "+name+": "+incorrectNumberOfArgsMsg);
            }

        }

        else
            return null;
    }
}
