package fr.unice.deptinfo.lpl.xml.descriptor;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import fr.unice.deptinfo.lpl.dependencies.MapDependencies;

/**
 *This class represent a configuration 
 *
 */
@XmlRootElement 
@XmlType(propOrder = {"strategyColor", "speed","width","height","nbCreatures","paramColorCreation","list"})
public class SimulatorDescriptor extends AbstractModelObject implements IDescriptor{
	
	private String strategyColor; 
	
	private String speed;
	
	private String width;
	
	private String height;
	
	private String nbCreatures;
	
	private String paramColorCreation;
	
	/**
	 * List of type creatures 
	 */
	private List<String> list = new ArrayList<String>();
		
	public List<String> getList() {
		return list;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}

	public String getParamColorCreation() {
		
		return paramColorCreation;
	}
	
	public String getNbCreatures() {
		return nbCreatures;
	}
	public String getSpeed() {
		return speed;
	}
	public String getStrategyColor() {
		return strategyColor;
	}
	public String getWidth() {
		return width;
	}
	
	public String getHeight() {
		return height;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public void setNbCreatures(String nbCreatures) {
		 this.nbCreatures=nbCreatures;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public void setStrategyColor(String strategyColor) {
		this.strategyColor = strategyColor;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	public void setParamColorCreation(String paramColorCreation) {
		this.paramColorCreation = paramColorCreation;
	}

	public String getPackage() {			
		return getJavaPackage().replace(".", "/");
	}
	
	public String getJavaPackage() {
		return MapDependencies.getInstance().getPackageName(list.get(0));
	}
	
	/**
	 * 
	 * @return
	 * 		name of velocity file according to the creature type
	 */
	public String getAppName() {
		return MapDependencies.getInstance().getAppName(list.get(0));
	}
	
	/**
	 * 
	 * @return
	 * 		true if the simulator must import java.awt.Color, false if it is not
	 */
	public boolean importAwtColor() {
		return MapDependencies.getInstance().getImportAwtColor(strategyColor).equals("true");
	}
	
	/**
	 * 
	 * @return
	 * 		artefactId of the application
	 */
	public String getArtefactID() {
		String res = "";
		
		for (String type : list) {
			res += type + "-";
		}
		return res;
	}
	
	public String getFileDependencies() {
		return MapDependencies.getInstance().getDependenceFileName(list.get(0));
	}
	
	/**
	 * 
	 * @return
	 * 		artefactID according to the strategy color
	 */
	public String getColorArtefactID() {
		return MapDependencies.getInstance().getArtefactIdStrategyColor(strategyColor);
	}
	
	/**
	 * 
	 * @param featureName
	 * @return
	 * 		artefactID according the deature name
	 */
	public String getArtefactID(String featureName) {
		return MapDependencies.getInstance().getArtefactIdDependency(featureName);
	}
	
}
	
	
	

