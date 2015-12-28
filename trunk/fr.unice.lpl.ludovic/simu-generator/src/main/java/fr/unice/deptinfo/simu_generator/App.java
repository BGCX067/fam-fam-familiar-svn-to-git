package fr.unice.deptinfo.simu_generator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import fr.unice.deptinfo.familiar_interpreter.FMEngineException;
import fr.unice.deptinfo.familiar_interpreter.impl.FamiliarInterpreter;
import fr.unice.deptinfo.fmConfiguration.ConfigurationFactory;
import fr.unice.deptinfo.fmConfiguration.IConfigurationFactory;
import fr.unice.deptinfo.ui.ConsoleUI;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * Hello world!
 * 
 */
public class App {
	private static FamiliarInterpreter familiarInterpreter = FamiliarInterpreter
			.getInstance();
	private static String pathScriptFM = "target/classes/simulateur.fml";
	private static String fmName = "simulateur";
	private static String configName = "config";

	/**
	 * Load the familiar script
	 * 
	 * @return true is the loading does not throw an exception, false either
	 */
	private static boolean loadFile() {
		try {
			familiarInterpreter.evalFile(pathScriptFM);
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("File \"simulateur.fml\" not found. Path : "
					+ pathScriptFM);
			return false;
		} catch (FMEngineException e) {
			System.err.println("Error in the script.");
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * 
	 * @return the feature model with fmName like root
	 */
	private static FeatureModelVariable loadFeatureModelVariable() {
		try {
			return familiarInterpreter.getFMVariable(fmName);
		} catch (VariableNotExistingException e1) {
			System.err.println("Name \"simulateur\" does not exist in the script.");
			return null;
		} 
		catch (VariableAmbigousConflictException e1) {
			System.err.println("Name \"simulateur\" is ambigous in the script.");
			return null;
		}
	}
	
	
	private static void selectFeatures() {
	
		IConfigurationFactory cf = new ConfigurationFactory();
		Scanner scan = new Scanner(System.in);
		String s = "";

		do {
			System.out
			.println("Enter the name of features you wish to select, or type exit to exit.");
			s = scan.nextLine();
			if (!s.equals("exit")) {
				cf.selectFeature(s);
				cf.deselectFeature(s);
				cf.unselectFeature(s);
			}
		} while (!s.equals("exit"));
		

	}

	public static void main(String[] args) {
		new ConsoleUI().work();
	}
}
