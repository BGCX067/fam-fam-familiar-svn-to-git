package fr.unice.deptinfo.lpl.ui;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.commons.lang.math.NumberUtils;

public class ColorUniqueVerifier implements DocumentListener {

	private ConfiguratorPanel pane;
	private JTextField paramColorCreationJTextField;

	public ColorUniqueVerifier(ConfiguratorPanel pane,
			JTextField paramColorCreationJTextField) {
		this.pane = pane;
		this.paramColorCreationJTextField = paramColorCreationJTextField;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		check(e);

	}

	private void check(DocumentEvent e) {
		try {
			String txt = e.getDocument()
					.getText(0, e.getDocument().getLength());
			String[] result = txt.split(",");
			if (result.length == 3) {
				test:for (String color : txt.split(",")) {
					if((!NumberUtils.isDigits(color))||(color.length()<1)){
						pane.notifyTextFieldObserver(paramColorCreationJTextField, true);
						break test;
					}
					else{
						pane.notifyTextFieldObserver(paramColorCreationJTextField, false);
						
					}
				}
			}
			else{
				pane.notifyTextFieldObserver(paramColorCreationJTextField, true);
			}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		check(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		check(e);
	}

}
