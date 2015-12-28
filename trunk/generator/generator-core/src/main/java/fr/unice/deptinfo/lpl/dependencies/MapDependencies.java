package fr.unice.deptinfo.lpl.dependencies;

import java.util.HashMap;
import java.util.Map;

public class MapDependencies {

	/**
	 * Map of values result in feature 
	 * Key : type of values (artefactID,strategyColor, ...), {@link KeyValues}		
	 * 			Map : Key : a feature name 
	 * 			Value : the value
	 * 
	 */
	private static Map<String, Map<String, String>> mapDependencies;
	private static MapDependencies singleton;
	
	public MapDependencies() {
		mapDependencies = new HashMap<String, Map<String, String>>();
		Map<String, String> mapInMap = new HashMap<String, String>();

		// Name of velocity file in app-base.vm
		mapInMap.put("Cell", "app-cell.vm");
		mapInMap.put("SmartCreature", "app-creatures.vm");
		mapInMap.put("StupidCreature", "app-creatures.vm");
		mapInMap.put("BouncingCreature", "app-creatures.vm");
		mapDependencies.put(KeyValues.APP_NAME, mapInMap);

		// Name of velocity file for dependencies
		mapInMap = new HashMap<String, String>();
		mapInMap.put("Cell", "pom-dependencies-cell.vm");
		mapInMap.put("SmartCreature", "pom-dependencies-creatures.vm");
		mapInMap.put("StupidCreature", "pom-dependencies-creatures.vm");
		mapInMap.put("BouncingCreature", "pom-dependencies-creatures.vm");
		mapDependencies.put(KeyValues.DEPENDENCE_FILE_NAME, mapInMap);

		// Package Name
		mapInMap = new HashMap<String, String>();
		mapInMap.put("Cell", "fr.unice.deptinfo.cellsimulator");
		mapInMap.put("SmartCreature", "fr.unice.deptinfo.bestiole.simulator");
		mapInMap.put("StupidCreature", "fr.unice.deptinfo.bestiole.simulator");
		mapInMap.put("BouncingCreature", "fr.unice.deptinfo.bestiole.simulator");
		mapDependencies.put(KeyValues.PACKAGE_NAME, mapInMap);

		// Package Name
		mapInMap = new HashMap<String, String>();
		mapInMap.put("SmartCreature", "creature-smart");
		mapInMap.put("StupidCreature", "creature-stupid");
		mapInMap.put("BouncingCreature", "creature-bouncing");
		mapDependencies.put(KeyValues.ARTEFACT_ID_DEPENDENCY, mapInMap);

		//Presence of java.awt.Color import
		mapInMap = new HashMap<String, String>();
		mapInMap.put("ColorCube", "false");
		mapInMap.put("ColorUnique", "true");
		mapDependencies.put(KeyValues.IMPORT_AWT_COLOR, mapInMap);

		//Presence of java.awt.Color import
		mapInMap = new HashMap<String, String>();
		mapInMap.put("ColorCube", "colorstrategy-colorcube");
		mapInMap.put("ColorUnique", "colorstrategy-uniquecolor");
		mapDependencies.put(KeyValues.ARTEFACT_ID_STRATEGY_COLOR, mapInMap);
	}

	public static void init() {
		if(singleton == null) {
			singleton = new MapDependencies();
		}

	}

	
	public static MapDependencies getInstance() {
		return singleton;
	}
	
	public String getValue(String typeValue, String featureName) {
		return mapDependencies.get(typeValue).get(featureName);
	}

	public String getAppName(String featureName) {
		return getValue(KeyValues.APP_NAME, featureName);
	}

	public String getDependenceFileName(String featureName) {
		return getValue(KeyValues.DEPENDENCE_FILE_NAME, featureName);
	}

	public String getPackageName(String featureName) {
		return getValue(KeyValues.PACKAGE_NAME, featureName);
	}

	public String getArtefactIdDependency(String featureName) {
		return getValue(KeyValues.ARTEFACT_ID_DEPENDENCY, featureName);
	}

	public String getImportAwtColor(String featureName) {
		return getValue(KeyValues.IMPORT_AWT_COLOR, featureName);
	}

	public String getArtefactIdStrategyColor(String featureName) {
		return getValue(KeyValues.ARTEFACT_ID_STRATEGY_COLOR, featureName);
	}
}
