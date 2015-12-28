package fr.unice.deptinfo.descriptor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *This class represent a configuration 
 *
 */
@XmlRootElement 
@XmlType(propOrder = {"name", "nameClass", "nbCreatures","nbColor","width","height"})
public class SimulatorDescriptor {
	
	public String name;
	
	public String nameClass;
	
	public String nbCreatures;
	
	public String nbColor;
	
	public String width;
	
	public String height;
	
	
	

}
