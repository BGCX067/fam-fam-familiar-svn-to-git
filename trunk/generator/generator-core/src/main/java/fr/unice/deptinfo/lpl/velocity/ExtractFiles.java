package fr.unice.deptinfo.lpl.velocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ExtractFiles {
	
	private static Collection<String> listeFilesToExtract;
	
	private static final String rootDirectory = "velocity";
	private static String rootPath;
	private static ExtractFiles singleton;
	
	private ExtractFiles() {
		listeFilesToExtract = new ArrayList<String>();
		listeFilesToExtract.add("app-base.vm");
		listeFilesToExtract.add("app-cell.vm");
		listeFilesToExtract.add("app-creatures.vm");
		listeFilesToExtract.add("pom-commun.vm");
		listeFilesToExtract.add("pom-dependencies-cell.vm");
		listeFilesToExtract.add("pom-dependencies-creatures.vm");
		listeFilesToExtract.add("simulateur.fml");
		listeFilesToExtract.add("app-test.vm");
	}
	
	public static void init() {
		if(singleton == null)
			singleton = new ExtractFiles();
	}
	
	public static ExtractFiles getInstance() {
		return singleton;
	}
	
	/**
	 * Extract the script in tempory file
	 */
	public File extractFile(String fileName) {
		File script = null;
		
		try {
			// Jar path
			String path = ExtractFiles.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");

			JarFile jarFile = new JarFile(decodedPath); 
			JarEntry je = jarFile.getJarEntry(fileName);
			script = new File(rootDirectory+"/"+fileName);

			InputStream is = jarFile.getInputStream(je);
			FileOutputStream fos = new FileOutputStream(script);
			while (is.available() > 0) { 
				fos.write(is.read());
			}
			fos.close();
			is.close();
		}
		catch (FileNotFoundException e) {} 
		catch (UnsupportedEncodingException e) {} 
		catch (IOException e) {}
		
		return script;
	}
	
	public void extractFiles() {
		File root = new File(rootDirectory);
		root.mkdir();
		root.deleteOnExit();
		rootPath = root.getAbsolutePath();
		for (String path : listeFilesToExtract) {
			File f = extractFile(path);
			f.deleteOnExit();
		}
	}
	
	public String getTemplatePath() { 
		return rootDirectory+"/template.xml";
	}

	public String getAppBase() {
		return rootDirectory + "/app-base.vm";
	}

	public String getPomCommun() {
		return rootDirectory + "/pom-commun.vm";
	}

	public String getFMFile() {
		return rootPath + "/simulateur.fml";
		
	}
	
	public String getAppTest() {
		return rootDirectory + "/app-test.vm";
	}
	
	

}
