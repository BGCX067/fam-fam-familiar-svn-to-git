package fr.unice.deptinfo.lpl.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StrategyColorActionListener implements ActionListener {

	private ConfiguratorPanel pane;
	private JComboBox strategyColorJComboBox;
	private JLabel paramColorCreationLabel;
	private JTextField paramColorCreationJTextField;
	private ColorUniqueVerifier colorVerif;
	private DocumentNumberVerifier numberVerif;

	public StrategyColorActionListener(ConfiguratorPanel configuratorPanel, JComboBox strategyColorJComboBox, JLabel paramColorCreationLabel, JTextField paramColorCreationJTextField) {
		this.pane=configuratorPanel;
		this.strategyColorJComboBox=strategyColorJComboBox;
		this.paramColorCreationLabel=paramColorCreationLabel;
		this.paramColorCreationJTextField=paramColorCreationJTextField;
		colorVerif=new ColorUniqueVerifier(pane,paramColorCreationJTextField);
		numberVerif=new DocumentNumberVerifier(pane,paramColorCreationJTextField);
	}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(strategyColorJComboBox.getSelectedItem().toString().equals("ColorUnique")){
				paramColorCreationLabel.setText("Couleur (R,G,B) :");
				paramColorCreationJTextField.getDocument().removeDocumentListener(numberVerif);
				paramColorCreationJTextField.getDocument().addDocumentListener(colorVerif);
				pane.notifyTextFieldObserver(paramColorCreationJTextField, true);
			}
			else{
				paramColorCreationLabel.setText("Nombre de couleurs:");
				paramColorCreationJTextField.getDocument().removeDocumentListener(colorVerif);
				paramColorCreationJTextField.getDocument().addDocumentListener(numberVerif);
				pane.notifyTextFieldObserver(paramColorCreationJTextField, true);	
			}
			if(strategyColorJComboBox.getSelectedObjects()!=null){
				paramColorCreationLabel.setVisible(true);
				paramColorCreationJTextField.setVisible(true);
			}
			
			
		}

}
