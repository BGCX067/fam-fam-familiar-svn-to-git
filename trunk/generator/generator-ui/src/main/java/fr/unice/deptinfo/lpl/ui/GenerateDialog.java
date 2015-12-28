package fr.unice.deptinfo.lpl.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fr.unice.deptinfo.lpl.generator.Generating;
import fr.unice.deptinfo.lpl.xml.descriptor.IDescriptor;

public class GenerateDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8541597042290436439L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private IDescriptor desc;
	private JCheckBox chckbxCompilerLapplication;

	public GenerateDialog(IDescriptor descr){
		this();
		this.desc=descr;
	}


	/**
	 * Create the dialog.
	 */
	private GenerateDialog() {
		setBounds(100, 100, 553, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblGenerationDuneConfiguration = new JLabel("Generation d'une configuration");
			lblGenerationDuneConfiguration.setFont(new Font("Dialog", Font.BOLD, 15));
			GridBagConstraints gbc_lblGenerationDuneConfiguration = new GridBagConstraints();
			gbc_lblGenerationDuneConfiguration.gridwidth = 3;
			gbc_lblGenerationDuneConfiguration.insets = new Insets(0, 0, 5, 0);
			gbc_lblGenerationDuneConfiguration.gridx = 0;
			gbc_lblGenerationDuneConfiguration.gridy = 0;
			contentPanel.add(lblGenerationDuneConfiguration, gbc_lblGenerationDuneConfiguration);
		}
		{
			JLabel lblDestinationApplication = new JLabel("Destination application:");
			GridBagConstraints gbc_lblDestinationApplication = new GridBagConstraints();
			gbc_lblDestinationApplication.ipady = 50;
			gbc_lblDestinationApplication.anchor = GridBagConstraints.WEST;
			gbc_lblDestinationApplication.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinationApplication.gridx = 0;
			gbc_lblDestinationApplication.gridy = 3;
			contentPanel.add(lblDestinationApplication, gbc_lblDestinationApplication);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 3;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JButton button = new JButton("Selectionner");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int result = chooser.showSaveDialog(chooser);

					if (result == JFileChooser.APPROVE_OPTION) { 
						textField_1.setText(chooser.getSelectedFile().getAbsolutePath());  
					}
				}
			});
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 0);
			gbc_button.gridx = 2;
			gbc_button.gridy = 3;
			contentPanel.add(button, gbc_button);
		}
		{
			JLabel lblNomDeLapplication = new JLabel("Nom de l'application:");
			GridBagConstraints gbc_lblNomDeLapplication = new GridBagConstraints();
			gbc_lblNomDeLapplication.ipady = 50;
			gbc_lblNomDeLapplication.anchor = GridBagConstraints.WEST;
			gbc_lblNomDeLapplication.insets = new Insets(0, 0, 5, 5);
			gbc_lblNomDeLapplication.gridx = 0;
			gbc_lblNomDeLapplication.gridy = 4;
			contentPanel.add(lblNomDeLapplication, gbc_lblNomDeLapplication);
		}
		{
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.gridwidth = 2;
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 4;
			contentPanel.add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
		}
		{
			chckbxCompilerLapplication = new JCheckBox("Compiler l'application");
			GridBagConstraints gbc_chckbxCompilerLapplication = new GridBagConstraints();
			gbc_chckbxCompilerLapplication.anchor = GridBagConstraints.WEST;
			gbc_chckbxCompilerLapplication.gridwidth = 3;
			gbc_chckbxCompilerLapplication.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxCompilerLapplication.gridy = 5;
			contentPanel.add(chckbxCompilerLapplication, gbc_chckbxCompilerLapplication);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(chckbxCompilerLapplication.isSelected()){
							Generating.getInstance().generateAndCompileProject(desc, textField_1.getText() + "/" + textField_2.getText());
						}else{
						Generating.getInstance().generateProject(desc, textField_1.getText() + "/" + textField_2.getText());}
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.setActionCommand("Cancel");
	            buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();

					}
				});
			}
		}
	}

}
