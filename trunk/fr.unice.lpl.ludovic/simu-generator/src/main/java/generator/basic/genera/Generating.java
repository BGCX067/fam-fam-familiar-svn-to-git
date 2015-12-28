package generator.basic.genera;

import generator.basic.vm.FillTemplateUtility;
import generator.basic.xml.JaxBUtilityProvider;
import generator.basic.xml.descriptor.IDescriptor;
import generator.basic.xml.descriptor.SimulatorDescriptor;

public class Generating {

	private IDescriptor descr= new SimulatorDescriptor();
	private JaxBUtilityProvider jaxb = JaxBUtilityProvider.singleton;
	private FillTemplateUtility tempUtil=FillTemplateUtility.instance;
	private final String pathToAppTemplate = "App.vm";
	private final String pathToPomTemplate = "pom.vm";
	
	
	public Generating() {}
	
	
	public void generateProject(String pathToXml, String dest){
		try {
			descr=jaxb.fill(descr, pathToXml);
			tempUtil.FillTemplate(descr, pathToAppTemplate,dest+"/App.java" );
			tempUtil.FillTemplate(descr, pathToPomTemplate,dest+"/pom.xml" );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new Generating().generateProject("output.xml", "dst/App.java");
	}
}
