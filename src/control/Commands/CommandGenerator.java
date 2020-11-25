package control.Commands;

public class CommandGenerator {

    private static Command[] availableCommands = {
            new AddCommand(),
            new HelpCommand(),
            new UpdateCommand(),
            new ExitCommand(),
            new ResetCommand()
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

    public static void commandHelp() {
        String s = "";

        for (Command c : availableCommands) {
            s += c.helpText();
        }

        System.out.println(s);
    }
}
