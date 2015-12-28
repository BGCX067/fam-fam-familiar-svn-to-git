package fr.unice.deptinfo.lpl.ui;

import java.util.Scanner;

import fr.unice.deptinfo.lpl.fmConfiguration.ConfigurationFactory;
import fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory;

public class ConsoleUI {
	
private IConfigurationFactory cf;
	
	private static final String exit = "exit";
	private static final String show = "show";
	private static final String help = "help";
	private static final String showSelected = "show selected";
	private static final String showUnselected = "show unselected";
	private static final String showDeselected = "show deselected";
	
	public ConsoleUI() {
		cf = ConfigurationFactory.getInstance(); 
	}
	
	public void work() {
		Scanner scan = new Scanner(System.in);
		String input;
		
		do {
			System.out.println("Enter the command (select, deselect or unselect and "
					+ "the name of features you wish, or type \"" + exit + "\" to exit. \n"
							+ "Type help to see all the commads.");
			
			input = scan.nextLine();
			
			if(input.equals(help)) {
				executeHelpCommand();
			}
			else if (input.equals(show)) {
				executeShowCommand();
			}
			else if (input.equals(showSelected)) {
				executeShowSelectedCommand();;
			}
			else if (input.equals(showUnselected)) {
				executeShowUnselectedCommand();
			}
			else if (input.equals(showDeselected)) {
				executeShowDeselectedCommand();;
			}
			else if(input.equals(exit)) {
				break;
			} else {
				executeDefaultCommand(input);
			}
		}while(!cf.isComplete());
	}

	private void executeShowDeselectedCommand() {
		System.out.println(cf.getDeselectedFeature());
		
	}

	private void executeShowUnselectedCommand() {
		System.out.println(cf.getUnselectedFeature());
		
	}

	private void executeHelpCommand() {
		System.out.println("Show the feature model : " + show + "\n"
				+ "Exit the program : " + exit + "\n"
				+ "Familiar command : [select|deselect|unselect] [feature]");
		
	}

	private void executeShowCommand() {
		System.out.println(cf.getSyntacticalRepresentation());
	}

	private void executeDefaultCommand(String input) {
		String[] inputAfterSplit = input.split("\\s+");
		if(inputAfterSplit.length == 2) {
			cf.evalSelectionCommande(inputAfterSplit[0], inputAfterSplit[1]);
		} else {
			System.err.println("Invalid command.");
		}
		
	}
	
	private void executeShowSelectedCommand(){
		System.out.println(cf.getSelectedFeature());
	}

}
