package velocity;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unice.deptinfo.lpl.velocity.FillTemplateUtility;
import fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor;

public class TestFillTemplateUtility {
	private SimulatorDescriptor desc;
	private FillTemplateUtility tu;
	
	private File root;
		
	@Before
	public void setUp() {
		desc = mock(SimulatorDescriptor.class);
		List<String> typesCreatures = new ArrayList<String>();
		typesCreatures.add("type1");
		typesCreatures.add("type2");
		when(desc.getList()).thenReturn(typesCreatures);
		when(desc.getSpeed()).thenReturn("speed");
		when(desc.getWidth()).thenReturn("width");
		when(desc.getHeight()).thenReturn("height");
		when(desc.getNbCreatures()).thenReturn("nbCreatures");
		when(desc.getParamColorCreation()).thenReturn("paramColorCreation");
		when(desc.getStrategyColor()).thenReturn("strategyColor");
		
		root  = new File("repertoire1");
		
		tu = FillTemplateUtility.instance;
	}
	
	@After
	public void tear() {
		try {
			FileUtils.deleteDirectory(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateFileWithVelocity(){
		File dest = new File("testVelocity.txt");
		dest.deleteOnExit();
		try {
			tu.FillTemplate(desc, "src/test/resources/testVelocity.vm", dest.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(dest.exists());
		try {
			assertTrue(FileUtils.contentEquals(dest, new File("src/test/resources/testVelocityResult.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testWithCreateFileWithVelocityWithInexistanteDirectory() {
		File dest = new File(root.getAbsolutePath() + "/repertoire2/repertoirefinal/testVelocity.txt");
		try {
			FileUtils.forceDeleteOnExit(dest);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			tu.FillTemplate(desc, "src/test/resources/testVelocity.vm", dest.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(dest.exists());
		
	}

}
