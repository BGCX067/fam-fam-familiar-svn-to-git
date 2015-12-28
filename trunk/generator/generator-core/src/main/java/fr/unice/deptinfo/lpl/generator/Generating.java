package fr.unice.deptinfo.lpl.generator;

import java.io.File;

import fr.unice.deptinfo.lpl.velocity.ExtractFiles;
import fr.unice.deptinfo.lpl.velocity.FillTemplateUtility;
import fr.unice.deptinfo.lpl.xml.descriptor.IDescriptor;
import fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor;
import fr.unice.deptinfo.maven_compiler.MavenCompiler;

public class Generating {

	private FillTemplateUtility tempUtil;
	private ExtractFiles extractor;
	private static Generating singleton;
	private static MavenCompiler mvn = new MavenCompiler();
	
	private Generating(FillTemplateUtility tempUtil, ExtractFiles extractor) {
		this.tempUtil = tempUtil;
		this.extractor = extractor;
	}
	
	public static void init(FillTemplateUtility tempUtil, ExtractFiles extractor) {
		if(singleton==null)
			singleton = new Generating(tempUtil, extractor);
	}
	
	public static Generating getInstance() {
		return singleton;
	}
	
				
	public void generateProject(IDescriptor descr, String dest){
		try {						
			String projectPath = dest + "/src/main/java/" + ((SimulatorDescriptor)descr).getPackage();
			
			File projectDirectory = new File(projectPath);
			projectDirectory.mkdirs();
			
			String testPath = projectPath.replace("main", "test");
			File projectTest = new File(testPath);
			projectTest.mkdirs();
			
			
			tempUtil.FillTemplate(descr, extractor.getAppBase(), projectPath +"/App.java");
			tempUtil.FillTemplate(descr, extractor.getPomCommun(), dest + "/pom.xml");
			tempUtil.FillTemplate(descr, extractor.getAppTest(), testPath + "/AppTest.java");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateAndCompileProject(IDescriptor descr, String dest){
		generateProject( descr, dest);
		mvn.compile(new File(dest),new File(dest+"/target"));
	}
}
