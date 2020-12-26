package control;

import java.util.Scanner;

import control.Commands.Command;
import control.Commands.CommandGenerator;
import control.Exceptions.GameException;
import logic.Game;
import logic.GameObjects.GameObject;
import view.GamePrinter;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    private GamePrinter printer;

    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
		System.out.println(game);
    }
    
    public void run() {
    	boolean refreshDisplay = true;

	    while (!game.isFinished()){
	    		
	    	if (refreshDisplay) printGame();
	    	refreshDisplay = false;
        		 
	    	System.out.println(prompt);
	    	String s = scanner.nextLine();
	    	String[] parameters = s.toLowerCase().trim().split(" ");
	    	System.out.println("[DEBUG] Executing: " + s);

			try {
				Command command = CommandGenerator.parse(parameters);
				refreshDisplay = command.execute(game);
			}
			catch (GameException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}


		}

			printGame();
    		System.out.println ("[Game over] " + game.getWinnerMsg());

    }

}

