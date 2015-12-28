package jaxb;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import fr.unice.deptinfo.lpl.xml.JaxBUtilityProvider;
import fr.unice.deptinfo.lpl.xml.descriptor.IDescriptor;
import fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor;

public class TestJaxB {

	JaxBUtilityProvider prov = JaxBUtilityProvider.singleton;
	
	@Test
	public void testFlushJaxb() {
		
		
		try {
			prov.flush(createsDescr(),"output.xml");
			 File actual= new File("output.xml");
			 actual.deleteOnExit();
			 File expected= new File("src/test/resources/output.xml");
			 assertTrue(FileUtils.contentEquals(actual, expected));
			 assertTrue(actual.exists() && expected.exists());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

}
	@Test
	public void testFillJaxb(){
		SimulatorDescriptor simu= new SimulatorDescriptor();
		try {
			simu=(SimulatorDescriptor) prov.fill(simu, "src/test/resources/output.xml");
			assertTrue(simu.getHeight().equals("123"));
			assertTrue(simu.getNbCreatures().equals("23"));
			assertTrue(simu.getParamColorCreation().equals("a"));
			assertTrue(simu.getSpeed().equals("2"));
			assertTrue(simu.getList().get(0).equals("type1"));
			assertTrue(simu.getList().get(1).equals("type44"));

			assertTrue(simu.getWidth().equals("200"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testErrorOnFileNotFoundXML() throws FileNotFoundException,JAXBException{
		SimulatorDescriptor simu= new SimulatorDescriptor();
		simu=(SimulatorDescriptor) prov.fill(simu, "inconnu/incc");
	}
	
	@Test(expected=JAXBException.class)
	public void testErrorOnInvalidXML() throws FileNotFoundException,JAXBException{
		SimulatorDescriptor simu= new SimulatorDescriptor();
		simu=(SimulatorDescriptor) prov.fill(simu, "src/test/resources/invalid.xml");
	}
	
	

	private IDescriptor createsDescr() {
		SimulatorDescriptor descr = new SimulatorDescriptor();
		descr.setHeight("123");
		descr.setNbCreatures("23");
		descr.setParamColorCreation("a");
		descr.setSpeed("2");
		ArrayList<String> typeCreat= new ArrayList<String>();
		typeCreat.add("type1");
		typeCreat.add("type44");
		descr.setList(typeCreat);
		descr.setWidth("200");
		return descr;
	}
}
