package fr.unice.deptinfo.descriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.management.Descriptor;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

public class TestJAXB3 {
	private static Logger log = Logger.getLogger(VelocityUtils.class);

	public static void main(String[] args) throws FileNotFoundException {
		try {
			JAXBContext context = JAXBContext
					.newInstance(SimulatorDescriptor.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			SimulatorDescriptor simu = new SimulatorDescriptor();
			
			simu.name = "name";
			simu.nameClass = "name.class";
			simu.nbColor = "3";
			simu.nbCreatures = "12";
			simu.width="200";
			simu.height="100";

			File file = new File("output.xml");
			FileOutputStream fop = new FileOutputStream(file);
			m.marshal(simu, fop);
			VelocityContext context1 = new VelocityContext();
			
			List<String> properties = new ArrayList<String>();

			Collection<Field> fields = Arrays.asList(SimulatorDescriptor.class.getDeclaredFields());

			for (Field f : fields) {
				String name = f.getName();
				Object value = null;

				try {
					value = f.get(simu);
				} catch (IllegalArgumentException e) {
					value = "unable to get value: " + e;
				} catch (IllegalAccessException e) {
					value = "unable to get value: " + e;
				} finally {
					properties.add(name + ": " + value);
					context1.put(name,value);
				}
			}

			
			String output = VelocityUtils.applyTemplate(
					"App.vm", context1);
			System.out.println(output);
			log.info(output);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}