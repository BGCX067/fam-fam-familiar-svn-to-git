package fr.unice.deptinfo.lpl.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.unice.deptinfo.lpl.fmConfiguration.ConfigurationFactory;
import fr.unice.deptinfo.lpl.xml.JaxBUtilityProvider;

public class MainFrame {

	private JFrame frame;
	final private JFileChooser chooser = new JFileChooser();
	private final ConfiguratorPanel configuratorPanel = new ConfiguratorPanel();
	private String pathToConf = "";
	private JaxBUtilityProvider jaxb = JaxBUtilityProvider.singleton;

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();

	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Generator");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenConfiguration = new JMenuItem("Ouvrir une Configuration");
		mntmOpenConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
						"xml files (*.xml)", "xml");
				chooser.setFileFilter(xmlfilter);
				chooser.setDialogTitle("Open configuration file");

				// set selected filter
				chooser.setFileFilter(xmlfilter);
				int returnVal = chooser.showOpenDialog(new JDialog());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					if (file != null) {
						configuratorPanel.setSimulatorDescriptor(file);
						pathToConf = file.getAbsolutePath();
					}
				}
			}
		});

		JMenuItem mntmSaveConfiguration = new JMenuItem("Sauvegarder Configuration");
		mntmSaveConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		mnFile.add(mntmSaveConfiguration);
		mnFile.add(mntmOpenConfiguration);

		JMenuItem mntmVoirLaFm = new JMenuItem("Voir la FM");
		mntmVoirLaFm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, ConfigurationFactory
						.getInstance().getSyntacticalRepresentation());
			}
		});
		menuBar.add(mntmVoirLaFm);

		JMenuItem mntmNewMenuItem = new JMenuItem("Generer");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (configuratorPanel.getState()) {
					Object[] options = { "Oui", "Non" };
					int n = JOptionPane.showOptionDialog(frame,
							"Voulez vous sauvegarder votre configuration?",
							"Configuration",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, // the
																			// titles
																			// of
																			// buttons
							options[0]); // default button title
					if (n == JOptionPane.YES_OPTION) {
						save();
					}
				}
				
					GenerateDialog dialog = new GenerateDialog(
							configuratorPanel.getSimulatorDescriptor());
					dialog.setVisible(true);
				
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class
				.getResource("/icons/full/eview16/new_persp.gif")));
		menuBar.add(mntmNewMenuItem);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		frame.getContentPane().setLayout(gridBagLayout);
		configuratorPanel.setGeneratorButton(mntmNewMenuItem);

		GridBagConstraints gbc_configuratorPanel = new GridBagConstraints();
		gbc_configuratorPanel.fill = GridBagConstraints.BOTH;
		gbc_configuratorPanel.gridx = 0;
		gbc_configuratorPanel.gridy = 0;
		frame.getContentPane().add(configuratorPanel, gbc_configuratorPanel);
		configuratorPanel.setFrame(frame);
	}

	private File getSaveLocation() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("pathToConf"));
		chooser.setSelectedFile(new File("unkownFileName.xml"));
		int result = chooser.showSaveDialog(chooser);

		if (result == JFileChooser.APPROVE_OPTION) {
			configuratorPanel.setState(false);
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

	private void save() {
		try {
			jaxb.flush(configuratorPanel.getSimulatorDescriptor(),
					pathToConf = getSaveLocation().getAbsolutePath());
		} catch (Exception e) {
		}
	}

	public void setVisible(boolean visibility) {
		frame.setVisible(true);

	}

}
