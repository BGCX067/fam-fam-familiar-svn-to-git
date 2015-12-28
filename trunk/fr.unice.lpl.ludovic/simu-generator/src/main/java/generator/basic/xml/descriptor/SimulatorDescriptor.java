package generator.basic.xml.descriptor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *This class represent a configuration 
 *
 */
@XmlRootElement 
@XmlType(propOrder = {"appName","packageName","name", "nameClass", "nbCreatures","nbColor","width","height","classColorStrategy"})
public class SimulatorDescriptor implements IDescriptor{
	
	public String appName;
	
	public String packageName;
	
	public String name;
	
	public String nameClass;
	
	public String nbCreatures;
	
	public String nbColor;
	
	public String width;
	
	public String height;
	
	public String classColorStrategy;

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	

}
