package fr.unice.deptinfo.fmConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import fr.unice.deptinfo.familiar_interpreter.FMEngineException;
import fr.unice.deptinfo.familiar_interpreter.impl.FamiliarInterpreter;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;

public class ConfigurationFactory implements IConfigurationFactory {
	
	private static String scriptPath = "target/classes/simulateur.fml";
	private static String fmName = "simulateur";
	private static String configName = "config";
	private static FamiliarInterpreter familiarInterpreter = FamiliarInterpreter.getInstance();
	
	
	public ConfigurationFactory() {
		if(loadFile()) {

			try {
				
				familiarInterpreter.eval(configName + " = configuration " + fmName);
			} catch (FMEngineException e) {}
		}
	}
	
	/**
	 * Load the familiar script
	 * 
	 * @return false is the loading throws an exception, true if it is not
	 */
	public boolean loadFile() {
		try {
			familiarInterpreter.evalFile(scriptPath);
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("File \"simulateur.fml\" not found. Path : "+ scriptPath);
			return false;
		} catch (FMEngineException e) {
			System.err.println("Error in the script.");
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#isComplete()
	 */
	public boolean isComplete() { 
		try {
			return familiarInterpreter.getConfigurationVariable(configName).isComplete();
		} catch (VariableNotExistingException e) {
			return false;
		} catch (VariableAmbigousConflictException e) {
			return false;
		}
	}
	
	/**
	 * Execute the command in the feature model
	 * @param cmd command to execute
	 * @param featureName feature to select/deselect or unselect
	 */
	public void evalSelectionCommande(String cmd,String featureName) {
		try {
			familiarInterpreter.eval(cmd + " " + featureName + " in "+ configName);
		} catch (FMEngineException e) {
			System.err.println("Error : unknown command.");
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#selectFeature(java.lang.String)
	 */
	public void selectFeature(String featureName) {
		evalSelectionCommande("select", featureName);
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#deselectFeature(java.lang.String)
	 */
	public void deselectFeature(String featureName) {
		evalSelectionCommande("deselect", featureName);
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#unselectFeature(java.lang.String)
	 */
	public void unselectFeature(String featureName) {
		evalSelectionCommande("unselect", featureName);
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#getSelectedFeature()
	 */
	public Collection<String> getSelectedFeature() {
		try {
			return familiarInterpreter.getSelectedFeature(configName);
		} catch (FMEngineException e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#getDeselectedFeature()
	 */
	public Collection<String> getDeselectedFeature() {
		try {
			return familiarInterpreter.getDeselectedFeature(configName);
		} catch (FMEngineException e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#getUnseselectedFeature()
	 */
	public Collection<String> getUnseselectedFeature() {
		try {
			return familiarInterpreter.getUnselectedFeature(configName);
		} catch (FMEngineException e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.fmConfiguration.IConfigurationFactory#getSyntacticalRepresentation()
	 */
	public String getSyntacticalRepresentation() {
		String s = "";
		try {
			s =  familiarInterpreter.getFMVariable(fmName).getSyntacticalRepresentation();
		} catch (VariableNotExistingException e) {} 
		catch (VariableAmbigousConflictException e) {}
		return s;
	}
	
}
