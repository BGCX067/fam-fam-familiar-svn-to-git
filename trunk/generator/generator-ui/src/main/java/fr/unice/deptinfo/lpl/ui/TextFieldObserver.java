package fr.unice.deptinfo.lpl.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class TextFieldObserver {
	
	private Map<JComponent,Boolean> listState = new HashMap<JComponent,Boolean>();
	private List<JComponent> listTonotify= new ArrayList<JComponent>();
	private JLabel error;

	public void changeState(JComponent document, boolean b) {
		this.listState.put(document, b);
		verify();
	}

	private void verify() {
		boolean state=false;
		for(Map.Entry<JComponent,Boolean> entry : listState.entrySet() ){
			if(entry.getValue().booleanValue()==true){
				state=true;
			};
		}
		error.setVisible(state);
		notifyWidget(state);
	}

	private void notifyWidget(boolean state) {
		for(JComponent component :  listTonotify){
			component.setEnabled(!state);
		}
		
	}

	public void addWidgetToNotify(JComponent jbuttonGenerating) {
		this.listTonotify.add(jbuttonGenerating);
		
	}

	public void setErrorMessage(JLabel lblNewLabel_1) {
		this.error=lblNewLabel_1;
	}

}
