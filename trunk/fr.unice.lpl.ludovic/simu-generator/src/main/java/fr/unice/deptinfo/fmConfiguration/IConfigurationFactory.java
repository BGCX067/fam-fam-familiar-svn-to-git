package fr.unice.deptinfo.fmConfiguration;

import java.util.Collection;

public interface IConfigurationFactory {

	/**
	 * 
	 * @return true if the configuration is complete, false if it's not
	 */
	public abstract boolean isComplete();

	/**
	 * Select the specific feature
	 * @param featureName
	 */
	public abstract void selectFeature(String featureName);

	/**
	 * Deselect the specific feature
	 * @param featureName
	 */
	public abstract void deselectFeature(String featureName);

	/**
	 * Unselect the specific feature
	 * @param featureName
	 */
	public abstract void unselectFeature(String featureName);

	/**
	 * 
	 * @return selected features
	 */
	public abstract Collection<String> getSelectedFeature();

	/**
	 * 
	 * @return deselected features
	 */
	public abstract Collection<String> getDeselectedFeature();

	/**
	 * 
	 * @return unselected features
	 */
	public abstract Collection<String> getUnseselectedFeature();

	/**
	 * 
	 * @return a syntactical representation of the feature model
	 */
	public String getSyntacticalRepresentation();
	
	
	public void evalSelectionCommande(String cmd,String featureName);
}