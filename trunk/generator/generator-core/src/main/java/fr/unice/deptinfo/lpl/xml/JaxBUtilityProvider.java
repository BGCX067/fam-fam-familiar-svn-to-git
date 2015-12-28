package fr.unice.deptinfo.lpl.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fr.unice.deptinfo.lpl.xml.descriptor.IDescriptor;
import fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor;


public class JaxBUtilityProvider {

	private JAXBContext context;
	private Marshaller m;
	private Class<? extends IDescriptor> descriptorXML;
	private  Unmarshaller unmarshaller;
	public static JaxBUtilityProvider singleton = new JaxBUtilityProvider();
	
	private JaxBUtilityProvider() {
		descriptorXML=SimulatorDescriptor.class;
		init();
	}
	
	
	
	private void init() {
		try {
			context = JAXBContext.newInstance(descriptorXML);
			m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

		
	}


/**
 * Fill the IDescriptor class parameter with the XML src
 * @param descr , represent destination class
 * @param src , path source to fill xml
 * @return IDescriptor fill with the xml
 * @throws FileNotFoundException
 * @throws JAXBException
 */
	public IDescriptor fill( IDescriptor descr ,String src) throws FileNotFoundException, JAXBException{
		descr = (IDescriptor) unmarshaller.unmarshal(new FileReader(src));
		return descr;
	}
	/**
	 * Save the class to XML
	 * @param descr , class to Save
	 * @param dest , Destination file
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 */
	public void flush(IDescriptor descr, String dest) throws FileNotFoundException, JAXBException{
		File file = new File(dest);
		FileOutputStream fop = new FileOutputStream(file);
		m.marshal(descr, fop);
	}
		
}
