package fr.unice.deptinfo.lpl.velocity;

import java.io.File;
import java.io.PrintWriter;

import org.apache.velocity.VelocityContext;

import fr.unice.deptinfo.lpl.xml.descriptor.IDescriptor;


public class FillTemplateUtility {

	private static final  String velocityVariableName = "simulator";
	public static final FillTemplateUtility instance = new FillTemplateUtility();
	private VelocityContext context = new VelocityContext();


	private FillTemplateUtility() {
	}

	public void FillTemplate(IDescriptor descriptor , String templateSrc , String dest) throws Exception {	 		 
		context.put(velocityVariableName, descriptor);

		File destFile = new File(dest);
		if(null!=destFile.getParent()){
			destFile.getParentFile().mkdirs();
		}

		PrintWriter writer = new PrintWriter(destFile);
		String output = VelocityUtils.applyTemplate(
				templateSrc, context);
		writer.write(output);
		writer.close();

	}





}
