package control.Commands;

public class CommandGenerator {

    private static Command[] availableCommands = {
            new AddCommand(),
            new HelpCommand(),
            new ResetCommand(),
            new ExitCommand(),
            new UpdateCommand(),

    };

    //retorna el comando si los comandos escritos en consola son correctos.
    public static Command parse(String[] commandWords) {
        Command comando = null;
        int i = 0;

        while(comando == null && i < availableCommands.length) {
            comando = availableCommands[i].parse(commandWords);
            i++;
        }

        return comando;
    }

    //imprime la lista de ayuda de toda la lista de comandos.
    public static void commandHelp() {
        String s = "Available commands:\n";

        for (Command c : availableCommands) {
            s += c.helpText();
        }

        System.out.println(s);
    }
}
