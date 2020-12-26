package control.Commands;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.CommandParseException;
import control.Exceptions.GameException;
import logic.Game;

public abstract class Command {

	  protected final String name;
	  protected final String shortcut;
	  private final String details; 
	  private final String help;

	  protected static final String incorrectNumberOfArgsMsg = "Incorrect number of arguments";
	  protected static final String incorrectArgsMsg = "Incorrect arguments format";
	  
	  public Command(String name,  String shortcut, String details, String help){    
	    this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }



	  //devuelve true si el comando introducido es igual al shortcut o al name de algun comando.
	  protected boolean matchCommandName(String name) {
		    return this.shortcut.equalsIgnoreCase(name) || 
		        this.name.equalsIgnoreCase(name);
	  }

	  //devuelve el comando si machCommandName es igual a true.
	  protected Command parseNoParamsCommand(String[] words) throws CommandParseException {
		  if (matchCommandName(words[0])) {
			  if (words.length != 1)
				  throw new CommandParseException("[ERROR]: Command "+name+" :"+incorrectNumberOfArgsMsg);
			  else return this;
		  }
		  return null;
	  }


	//devuelve en un string el details y el help de un comando.
	  public String helpText(){
	    return details + ": " + help + "\n";
	  }

	//metodos de abstractos.
	public abstract boolean execute(Game game)  throws  CommandExecuteException, GameException;

	public abstract Command parse(String[] commandWords) throws CommandParseException;
}
