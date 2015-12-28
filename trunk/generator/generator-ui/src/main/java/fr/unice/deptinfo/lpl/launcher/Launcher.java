package fr.unice.deptinfo.lpl.launcher;

import fr.unice.deptinfo.lpl.dependencies.MapDependencies;
import fr.unice.deptinfo.lpl.fmConfiguration.ConfigurationFactory;
import fr.unice.deptinfo.lpl.generator.Generating;
import fr.unice.deptinfo.lpl.ui.MainFrame;
import fr.unice.deptinfo.lpl.velocity.ExtractFiles;
import fr.unice.deptinfo.lpl.velocity.FillTemplateUtility;

public class Launcher {
	public static void main(String[] args) {
		ExtractFiles.init();
		ExtractFiles extractor = ExtractFiles.getInstance();
		extractor.extractFiles();
		
		ConfigurationFactory.init();
		
		Generating.init(FillTemplateUtility.instance, ExtractFiles.getInstance());
		MapDependencies.init();
		
		MainFrame window = new MainFrame();
		
		
		window.setVisible(true);
	}
}
