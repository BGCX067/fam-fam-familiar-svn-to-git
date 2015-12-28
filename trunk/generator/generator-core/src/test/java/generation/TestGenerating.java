package generation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import fr.unice.deptinfo.lpl.generator.Generating;
import fr.unice.deptinfo.lpl.velocity.ExtractFiles;
import fr.unice.deptinfo.lpl.velocity.FillTemplateUtility;
import fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor;
import fr.unice.deptinfo.maven_compiler.MavenCompiler;

public class TestGenerating {
	
	@Test
	public void testGeneration() {
		ExtractFiles extractor = mock(ExtractFiles.class);
		when(extractor.getAppBase()).thenReturn("src/test/resources/app-base.vm");
		when(extractor.getPomCommun()).thenReturn("src/test/resources/pom-commun.vm");
		when(extractor.getAppTest()).thenReturn("src/test/resources/app-test.vm");
		Generating.init(FillTemplateUtility.instance, extractor);
		Generating generator = Generating.getInstance();
		
		SimulatorDescriptor desc = mock(SimulatorDescriptor.class);
		desc = mock(SimulatorDescriptor.class);
		
		List<String> typesCreatures = new ArrayList<String>();
		typesCreatures.add("SmartCreature");
		typesCreatures.add("BouncingCreature");
		when(desc.getList()).thenReturn(typesCreatures);
		when(desc.getSpeed()).thenReturn("50");
		when(desc.getWidth()).thenReturn("25");
		when(desc.getHeight()).thenReturn("9");
		when(desc.getNbCreatures()).thenReturn("10");
		when(desc.getParamColorCreation()).thenReturn("255,255,255");
		when(desc.getStrategyColor()).thenReturn("ColorUnique");
		when(desc.getPackage()).thenReturn("fr/unice/deptinfo/bestiole/simulator");
		when(desc.getJavaPackage()).thenReturn("fr.unice.deptinfo.bestiole.simulator");
		when(desc.getAppName()).thenReturn("src/test/resources/app-creatures.vm");
		when(desc.importAwtColor()).thenReturn(true);
		when(desc.getArtefactID()).thenReturn("smart-bouncing-");
		when(desc.getFileDependencies()).thenReturn("src/test/resources/pom-dependencies-creatures.vm");
		when(desc.getColorArtefactID()).thenReturn("colorstrategy-uniquecolor");
		when(desc.getArtefactID("SmartCreature")).thenReturn("creature-smart");
		when(desc.getArtefactID("BouncingCreature")).thenReturn("creature-bouncing");
		
		
		
		
		File dest = new File("AppTest");
				
		generator.generateProject(desc, dest.getAbsolutePath());
		
		assertTrue(dest.exists());
		
		MavenCompiler mc = new MavenCompiler();
		mc.compile(dest, null);
		assertTrue(mc.isLastCompilationSucessful());
		
		try {
			FileUtils.deleteDirectory(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
