package fr.unice.deptinfo.lpl.ui;

import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class DocumentNumberVerifier implements DocumentListener {

	private ConfiguratorPanel pane;
	private JComponent parent;

	public DocumentNumberVerifier(ConfiguratorPanel configuratorPanel,JComponent source) {
		this.pane=configuratorPanel;
		this.parent=source;
	}
	

	@Override
	public void insertUpdate(DocumentEvent e) {
		setup(e);

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		setup(e);

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		setup(e);

	}

	private void setup(DocumentEvent e) {
		boolean returnValue = false;
        try {
          Double.parseDouble(e.getDocument().getText(0, e.getDocument().getLength()));
          returnValue = true;
        } catch (NumberFormatException e1) {
          returnValue = false;
        } catch (BadLocationException e1) {
        	  returnValue = false;
			e1.printStackTrace();
		}
        pane.notifyTextFieldObserver(parent,!returnValue);
        pane.setState(true);
		
	}
	
	

}
