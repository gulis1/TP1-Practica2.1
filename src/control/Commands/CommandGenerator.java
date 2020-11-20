package control.Commands;

public class CommandGenerator {

    private static Command[] availableCommands = {
            new AddCommand()
    };


    public static Command parse(String[] commandWords) {
        Command comando = null;
        int i = 0;

        while(comando == null && i < availableCommands.length) {
            comando = availableCommands[i].parse(commandWords);
            i++;
        }

        return comando;
    }
}
