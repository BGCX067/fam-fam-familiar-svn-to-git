package Familiar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.unice.deptinfo.lpl.fmConfiguration.ConfigurationFactory;

public class TestConfigurationFactory {
	private ConfigurationFactory cf;
	@Before
	public void setUp() {
		ConfigurationFactory.init("src/test/resources/simulateur.fml");
		cf = ConfigurationFactory.getInstance();
	}
	@Test
	public void testLoadingOfFM() {		
		String fmSyntacticalRepresentation = "Simulateur: [PluginLoader] Actionable Visualizer Speed ; \n"
				+ "Actionable: (Creature|Cell) ; \n"
				+ "Visualizer: [ColorStrategy] ; \n"
				+ "Creature: (Stupid|Bouncing|Smart)+ Dimension ; \n"
				+ "ColorStrategy: (ColorUnique|ColorCube) ; \n"
				+ "(Creature <-> ColorStrategy);\n"
				+ "(PluginLoader -> Creature);";
		
		assertEquals(fmSyntacticalRepresentation, cf.getSyntacticalRepresentation());
	}
	
	@Test
	public void testConfigurationWithCell() {		
		cf.selectFeature("Cell");
		assertTrue( cf.isComplete());
		
		//Cell, Visualizer, Speed, Actionable, Simulateur are selected
		assertTrue(cf.getSelectedFeature().contains("Cell"));
		assertTrue(cf.getSelectedFeature().contains("Visualizer"));
		assertTrue(cf.getSelectedFeature().contains("Speed"));
		assertTrue(cf.getSelectedFeature().contains("Actionable"));
		assertTrue(cf.getSelectedFeature().contains("Simulateur"));
		
		//ColorCube, Bouncing, PluginLoader, ColorUnique, 
		//ColorStrategy, Creature, Smart, Stupid, Dimension
		// are deselected
		assertTrue(cf.getDeselectedFeature().contains("ColorCube"));
		assertTrue(cf.getDeselectedFeature().contains("Bouncing"));
		assertTrue(cf.getDeselectedFeature().contains("PluginLoader"));
		assertTrue(cf.getDeselectedFeature().contains("ColorUnique"));
		assertTrue(cf.getDeselectedFeature().contains("ColorStrategy"));
		assertTrue(cf.getDeselectedFeature().contains("Creature"));
		assertTrue(cf.getDeselectedFeature().contains("Smart"));
		assertTrue(cf.getDeselectedFeature().contains("Stupid"));
		assertTrue(cf.getDeselectedFeature().contains("Dimension"));
		
		//No unselected feature
		assertEquals(0,cf.getUnselectedFeature().size());
		
		cf.deselectFeature("Cell");
		cf.selectFeature("Smart");
		assertFalse( cf.isComplete());
		assertTrue(cf.getSelectedFeature().contains("Smart"));
		
		cf.selectFeature("ColorCube");
		cf.deselectFeature("PluginLoader");
		cf.selectFeature("Bouncing");
		cf.selectFeature("Stupid");
		
		assertTrue(cf.isComplete());
			
		//ColorCube, Bouncing, Smart, Stupid, Visualizer, 
		//Speed, ColorStrategy, Creature, Actionable, 
		//Dimension, Simulateur are selected
		assertTrue(cf.getSelectedFeature().contains("ColorCube"));
		assertTrue(cf.getSelectedFeature().contains("Bouncing"));
		assertTrue(cf.getSelectedFeature().contains("Smart"));
		assertTrue(cf.getSelectedFeature().contains("Stupid"));
		assertTrue(cf.getSelectedFeature().contains("Visualizer"));
		assertTrue(cf.getSelectedFeature().contains("Speed"));
		assertTrue(cf.getSelectedFeature().contains("ColorStrategy"));
		assertTrue(cf.getSelectedFeature().contains("Creature"));
		assertTrue(cf.getSelectedFeature().contains("Dimension"));
		assertTrue(cf.getSelectedFeature().contains("Actionable"));
		assertTrue(cf.getSelectedFeature().contains("Simulateur"));
		
		
		//Cell,PluginLoader,ColorUnique are deselected
		assertTrue(cf.getDeselectedFeature().contains("Cell"));
		assertTrue(cf.getDeselectedFeature().contains("ColorUnique"));
		assertTrue(cf.getDeselectedFeature().contains("PluginLoader"));
		
		//No unselected feature
		assertEquals(0,cf.getUnselectedFeature().size());
		
	}
		
}
