package fr.unice.deptinfo.lpl.fmConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import fr.unice.deptinfo.familiar_interpreter.FMEngineException;
import fr.unice.deptinfo.familiar_interpreter.impl.FamiliarInterpreter;
import fr.unice.deptinfo.lpl.velocity.ExtractFiles;
import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.VariableAmbigousConflictException;

public class ConfigurationFactory implements IConfigurationFactory {
	
	private static String fmName = "simulateur";
	private static String configName = "config";
	private static FamiliarInterpreter familiarInterpreter = FamiliarInterpreter.getInstance();
	private static ConfigurationFactory singleton;
	
	
	private ConfigurationFactory() {
		if(loadFile()) {
			initConfiguration();
		}
	}

	private ConfigurationFactory(String scriptPath) {
		if(loadFile(scriptPath)) {
			initConfiguration();
		}
	}

	/**
	 * Initialize the configuration
	 */
	private void initConfiguration() {
		try {
			familiarInterpreter.eval(configName + " = configuration " + fmName);
		} catch (FMEngineException e) {}
	}
	
	public static void init() {
		if (singleton == null)
			singleton = new ConfigurationFactory();
	}
	
	public static void init(String scriptPath) {
		if (singleton == null)
			singleton = new ConfigurationFactory(scriptPath);
	}
	
	public static ConfigurationFactory getInstance() {
		return singleton;
	}
	
	/**
	 * Load the familiar script
	 * 
	 * @return false is the loading throws an exception, true if it is not
	 */
	public boolean loadFile() {
		try {
			ExtractFiles extractor = ExtractFiles.getInstance();
			familiarInterpreter.evalFile(extractor.getFMFile());
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("Error loading script. Check path.");
			return false;
		} catch (FMEngineException e) {
			System.err.println("Error in the script.");
			return false;
		} catch (IOException e) {
			System.err.println("Error loading script.");
			return false;
		}
	}
	
	/**
	 * Load the familiar script
	 * 	 * @param scriptPath
	 * 				Familiar Script Path
	 * 
	 * @return false is the loading throws an exception, true if it is not
	 */
	public boolean loadFile(String scriptPath) {
		try {
			familiarInterpreter.evalFile(scriptPath);
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("Error loading script. Check path.");
			return false;
		} catch (FMEngineException e) {
			System.err.println("Error in the script.");
			return false;
		} catch (IOException e) {
			System.err.println("Error loading script.");
			return false;
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#isComplete()
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
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#selectFeature(java.lang.String)
	 */
	public void selectFeature(String featureName) {
		evalSelectionCommande("select", featureName);
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#deselectFeature(java.lang.String)
	 */
	public void deselectFeature(String featureName) {
		evalSelectionCommande("deselect", featureName);
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#unselectFeature(java.lang.String)
	 */
	public void unselectFeature(String featureName) {
		evalSelectionCommande("unselect", featureName);
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#getSelectedFeature()
	 */
	public Collection<String> getSelectedFeature() {
		try {
			return familiarInterpreter.getSelectedFeature(configName);
		} catch (FMEngineException e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#getDeselectedFeature()
	 */
	public Collection<String> getDeselectedFeature() {
		try {
			return familiarInterpreter.getDeselectedFeature(configName);
		} catch (FMEngineException e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#getUnselectedFeature()
	 */
	public Collection<String> getUnselectedFeature() {
		try {
			return familiarInterpreter.getUnselectedFeature(configName);
		} catch (FMEngineException e) {
			return new ArrayList<String>();
		}
	}
	
	/**
	 * @see fr.unice.deptinfo.lpl.fmConfiguration.IConfigurationFactory#getSyntacticalRepresentation()
	 */
	public String getSyntacticalRepresentation() {
		String s = "";
		try {
			s =  familiarInterpreter.getFMVariable(fmName).getSyntacticalRepresentation();
		} catch (VariableNotExistingException e) {} 
		catch (VariableAmbigousConflictException e) {}
		return s;
	}

	public void clearInterpreter() {
		familiarInterpreter.clearInterpreter();
		
	}
	
}
