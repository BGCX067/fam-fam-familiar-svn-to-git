package generator.basic.vm;

import generator.basic.xml.descriptor.IDescriptor;
import generator.basic.xml.descriptor.SimulatorDescriptor;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

public class FillTemplateUtility {
	
	public static final FillTemplateUtility instance = new FillTemplateUtility();
	private static Logger log = Logger.getLogger(VelocityUtils.class);
	private VelocityContext context = new VelocityContext();
	
	
	private FillTemplateUtility() {
	}

	 public void FillTemplate(IDescriptor descriptor , String templateSrc , String dest) throws Exception{
		 Collection<Field> fields = Arrays.asList(descriptor.getClass().getDeclaredFields());
	 
		 for (Field f : fields) {
				String name = f.getName();
				Object value = null;

				try {
					value = f.get(descriptor);
				} catch (IllegalArgumentException e) {
					value = "unable to get value: " + e;
				} catch (IllegalAccessException e) {
					value = "unable to get value: " + e;
				} finally {
					context.put(name,value);
				}
			}
		 File destFile = new File(dest);
		 if(null!=destFile.getParent()){
			 destFile.getParentFile().mkdirs();
		 }
		 
		 PrintWriter writer = new PrintWriter(destFile, "UTF-8");
			String output = VelocityUtils.applyTemplate(
					templateSrc, context);
			writer.write(output);
			writer.close();
		
	 }

	

	 public static void main(String[] args) {
		 SimulatorDescriptor simu = new SimulatorDescriptor();
			
			
			try {
				FillTemplateUtility.instance.FillTemplate(simu, "App.vm", "output.java");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
