package de.griffl.proofofconcept;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class ProofofconceptApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Proofofconcept Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
