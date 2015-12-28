package fr.unice.deptinfo.lpl.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

import fr.unice.deptinfo.lpl.xml.JaxBUtilityProvider;
import fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor;

public class ConfiguratorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BindingGroup m_bindingGroup;
	private fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor simulatorDescriptor = new fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor();
	private JTextField heightJTextField;
	private JTextField nbCreaturesJTextField;
	private JTextField paramColorCreationJTextField;
	private JTextField speedJTextField;
	private JComboBox strategyColorJComboBox;
	private JTextField widthJTextField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel speedLabel;
	private JLabel paramColorCreationLabel;
	private JMenuItem jbuttonGenerating;
	private TextFieldObserver observer = new TextFieldObserver();
	private JFrame frame;
	private boolean state;
	private JList listCreature;
	private JLabel lblCreaturesType;

	public ConfiguratorPanel(
			fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor newSimulatorDescriptor) {
		this();
		setSimulatorDescriptor(newSimulatorDescriptor);
		if (this.simulatorDescriptor.getParamColorCreation() != "") {
			paramColorCreationJTextField.setVisible(true);
			paramColorCreationLabel.setVisible(true);
			this.getParent().repaint();
		}
	}

	public void setSimulatorDescriptor(File file) {
		try {
			this.setSimulatorDescriptor(
					(SimulatorDescriptor) JaxBUtilityProvider.singleton.fill(
							simulatorDescriptor, file.getAbsolutePath()), true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,
					"Erreur veuillez selectionner une configuration valide");
		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(this,
					"Erreur veuillez selectionner une configuration");
		}
	}

	public ConfiguratorPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		setLayout(gridBagLayout);

		lblNewLabel = new JLabel("Configure le projet");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		lblNewLabel_1 = new JLabel("Erreur configuration invalide");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setIcon(new ImageIcon(ConfiguratorPanel.class
				.getResource("/icons/full/etool16/delete_edit.gif")));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		lblCreaturesType = new JLabel("Creature(s) :");
		GridBagConstraints gbc_lblCreaturesPresentesDans = new GridBagConstraints();
		gbc_lblCreaturesPresentesDans.anchor = GridBagConstraints.WEST;
		gbc_lblCreaturesPresentesDans.gridheight = 2;
		gbc_lblCreaturesPresentesDans.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreaturesPresentesDans.gridx = 0;
		gbc_lblCreaturesPresentesDans.gridy = 3;
		add(lblCreaturesType, gbc_lblCreaturesPresentesDans);

		listCreature = new JList(new DefaultComboBoxModel(
				JComboBoxChoices.CreatureType));
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		listCreature.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		gbc_list_1.gridheight = 2;
		gbc_list_1.insets = new Insets(0, 0, 5, 0);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 1;
		gbc_list_1.gridy = 3;
		add(listCreature, gbc_list_1);

		JLabel nbCreaturesLabel = new JLabel("Nombre de Creatures:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.anchor = GridBagConstraints.WEST;
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 5;
		add(nbCreaturesLabel, labelGbc_1);

		nbCreaturesJTextField = new JTextField();

		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 5, 0);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 5;
		add(nbCreaturesJTextField, componentGbc_1);

		speedLabel = new JLabel("Vitesse:");
		GridBagConstraints labelGbc_3 = new GridBagConstraints();
		labelGbc_3.anchor = GridBagConstraints.WEST;
		labelGbc_3.insets = new Insets(5, 5, 5, 5);
		labelGbc_3.gridx = 0;
		labelGbc_3.gridy = 6;
		add(speedLabel, labelGbc_3);

		speedJTextField = new JTextField();
		GridBagConstraints componentGbc_3 = new GridBagConstraints();
		componentGbc_3.insets = new Insets(5, 0, 5, 0);
		componentGbc_3.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_3.gridx = 1;
		componentGbc_3.gridy = 6;
		add(speedJTextField, componentGbc_3);

		JLabel strategyColorLabel = new JLabel("Couleur Strategie:");
		GridBagConstraints labelGbc_4 = new GridBagConstraints();
		labelGbc_4.anchor = GridBagConstraints.WEST;
		labelGbc_4.insets = new Insets(5, 5, 5, 5);
		labelGbc_4.gridx = 0;
		labelGbc_4.gridy = 7;
		add(strategyColorLabel, labelGbc_4);

		strategyColorJComboBox = new JComboBox(new DefaultComboBoxModel(
				JComboBoxChoices.ColorStrategy));
		GridBagConstraints componentGbc_4 = new GridBagConstraints();
		componentGbc_4.insets = new Insets(5, 0, 5, 0);
		componentGbc_4.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_4.gridx = 1;
		componentGbc_4.gridy = 7;
		add(strategyColorJComboBox, componentGbc_4);

		paramColorCreationLabel = new JLabel("ParamColorCreation:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.anchor = GridBagConstraints.WEST;
		labelGbc_2.insets = new Insets(5, 5, 5, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 8;
		add(paramColorCreationLabel, labelGbc_2);
		paramColorCreationLabel.setVisible(false);

		paramColorCreationJTextField = new JTextField();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.insets = new Insets(5, 0, 5, 0);
		componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 8;
		add(paramColorCreationJTextField, componentGbc_2);
		paramColorCreationJTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (paramColorCreationLabel.getText().equals(
						"Couleur (R,G,B) :")) {

					Color color = JColorChooser.showDialog(new JDialog(),
							"Choose a color", Color.white);
					if (color != null) {
						paramColorCreationJTextField.setText(color.getRed()
								+ "," + color.getGreen() + ","
								+ color.getBlue());
					}
				}
			}
		});
		paramColorCreationJTextField.setVisible(false);

		JLabel widthLabel = new JLabel("Largeur:");
		GridBagConstraints labelGbc_6 = new GridBagConstraints();
		labelGbc_6.anchor = GridBagConstraints.WEST;
		labelGbc_6.insets = new Insets(5, 5, 5, 5);
		labelGbc_6.gridx = 0;
		labelGbc_6.gridy = 9;
		add(widthLabel, labelGbc_6);

		widthJTextField = new JTextField();

		GridBagConstraints componentGbc_6 = new GridBagConstraints();
		componentGbc_6.insets = new Insets(5, 0, 5, 0);
		componentGbc_6.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_6.gridx = 1;
		componentGbc_6.gridy = 9;
		add(widthJTextField, componentGbc_6);

		JLabel heightLabel = new JLabel("Hauteur:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.anchor = GridBagConstraints.WEST;
		labelGbc_0.insets = new Insets(5, 5, 0, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 10;
		add(heightLabel, labelGbc_0);

		heightJTextField = new JTextField();

		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 0, 0);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 10;
		add(heightJTextField, componentGbc_0);

		if (simulatorDescriptor != null) {
			m_bindingGroup = initDataBindings();
		}
		
		this.listCreature.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				List value = Arrays.asList(listCreature.getSelectedValues());
				if ((value.contains("Cell") && value.size() > 1)
						|| listCreature.isSelectionEmpty()) {
					notifyTextFieldObserver(listCreature, true);
				} else {
					speedJTextField.setEnabled(true);
					notifyTextFieldObserver(listCreature, false);
				}

				if (value.contains("Cell")) {
					speedJTextField.setEnabled(false);
				} else {
					speedJTextField.setEnabled(true);
				}

			}
		});

		fill();
		nbCreaturesJTextField.getDocument().addDocumentListener(
				new DocumentNumberVerifier(this, nbCreaturesJTextField));
		speedJTextField.getDocument().addDocumentListener(
				new DocumentNumberVerifier(this, speedJTextField));
		widthJTextField.getDocument().addDocumentListener(
				new DocumentNumberVerifier(this, widthJTextField));
		heightJTextField.getDocument().addDocumentListener(
				new DocumentNumberVerifier(this, heightJTextField));
		this.strategyColorJComboBox
		.addActionListener(new StrategyColorActionListener(this,
				strategyColorJComboBox, paramColorCreationLabel,
				paramColorCreationJTextField));
	}

	private void fill() {
		this.heightJTextField.setText("300");
		this.nbCreaturesJTextField.setText("20");
		this.paramColorCreationJTextField.setText("255,255,255");
		this.speedJTextField.setText("3");
		this.strategyColorJComboBox.setSelectedIndex(0);
		this.widthJTextField.setText("300");

		observer.setErrorMessage(lblNewLabel_1);
		this.listCreature.setSelectedIndex(0);
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> heightProperty = BeanProperty
				.create("height");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty = BeanProperty
				.create("text");
		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						simulatorDescriptor, heightProperty, heightJTextField,
						textProperty);
		autoBinding.bind();
		//
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> nbCreaturesProperty = BeanProperty
				.create("nbCreatures");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						simulatorDescriptor, nbCreaturesProperty,
						nbCreaturesJTextField, textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> paramColorCreationProperty = BeanProperty
				.create("paramColorCreation");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_2 = BeanProperty
				.create("text");
		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_2 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						simulatorDescriptor, paramColorCreationProperty,
						paramColorCreationJTextField, textProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> speedProperty = BeanProperty
				.create("speed");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_3 = BeanProperty
				.create("text");
		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_3 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						simulatorDescriptor, speedProperty, speedJTextField,
						textProperty_3);
		autoBinding_3.bind();
		//
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> strategyColorProperty = BeanProperty
				.create("strategyColor");
		BeanProperty<javax.swing.JComboBox, java.lang.String> selectedIndexProperty = BeanProperty
				.create("selectedItem");
		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String, javax.swing.JComboBox, java.lang.String> autoBinding_4 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						simulatorDescriptor, strategyColorProperty,
						strategyColorJComboBox, selectedIndexProperty);
		autoBinding_4.bind();
		//
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> typeCreatureProperty = BeanProperty
				.create("typeCreature");
		BeanProperty<javax.swing.JComboBox, java.lang.String> selectedIndexProperty_1 = BeanProperty
				.create("selectedItem");
		//
		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String> widthProperty = BeanProperty
				.create("width");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_4 = BeanProperty
				.create("text");
		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_6 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						simulatorDescriptor, widthProperty, widthJTextField,
						textProperty_4);
		autoBinding_6.bind();

		BeanProperty<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, List<String>> simulatorDescriptorBeanProperty = BeanProperty
				.create("list");
		BeanProperty<JList, List<String>> jListBeanProperty_1 = BeanProperty
				.create("selectedElements");

		AutoBinding<fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor, List<String>, JList, List<String>> autoBinding_10 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						simulatorDescriptor, simulatorDescriptorBeanProperty,
						listCreature, jListBeanProperty_1);
		autoBinding_10.bind();

		BindingGroup bindingGroup = new BindingGroup();
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_4);
		bindingGroup.addBinding(autoBinding_6);
		bindingGroup.addBinding(autoBinding_10);
		return bindingGroup;
	}

	public fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor getSimulatorDescriptor() {
		return simulatorDescriptor;
	}

	public void setSimulatorDescriptor(
			fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor newSimulatorDescriptor) {
		setSimulatorDescriptor(newSimulatorDescriptor, true);
	}

	public void setSimulatorDescriptor(
			fr.unice.deptinfo.lpl.xml.descriptor.SimulatorDescriptor newSimulatorDescriptor,
			boolean update) {
		simulatorDescriptor = newSimulatorDescriptor;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (simulatorDescriptor != null) {
				
				int[] selectedValues = new int[simulatorDescriptor.getList()
						.size()];
				for (int i = 0, k =0; k<JComboBoxChoices.CreatureType.length;k++) {
					if (simulatorDescriptor.getList().contains(JComboBoxChoices.CreatureType[k])) {
						selectedValues[i++] = k;
					}
					
				}
				listCreature.setSelectedIndices(selectedValues);
				m_bindingGroup = initDataBindings();
			}

		}
	}

	public void notifyTextFieldObserver(JComponent document, boolean b) {
		observer.changeState(document, b);
	}

	public void setGeneratorButton(JMenuItem mntmNewMenuItem) {
		this.jbuttonGenerating = mntmNewMenuItem;
		observer.addWidgetToNotify(mntmNewMenuItem);
	}

	public void setState(boolean state) {
		this.state = state;
		if (state) {
			frame.setTitle("*Generator");
		} else {
			frame.setTitle("Generator");
		}
	}
	public boolean getState() {
		return this.state;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
