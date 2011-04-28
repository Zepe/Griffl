package de.griffl.proofofconcept.view;

import java.util.logging.Logger;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.griffl.proofofconcept.presenter.*;


public class MainWindowView extends Window implements MainWindowPresenter.Display{

	private static final Logger logger = Logger.getLogger(MainWindowView.class.getName());
	
	private Label status = new Label("Bitte wählen Sie eine PDF Datei aus, um sie hochzuladen.");

    private ProgressIndicator pi = new ProgressIndicator();


    private HorizontalLayout progressLayout = new HorizontalLayout();

    
    private Upload upload;
    
    private Button cancelProcessing = new Button("abbrechen");
    
	public MainWindowView(String name){
		super(name);
		
		progressLayout.setSpacing(true);
		progressLayout.setVisible(false);
		progressLayout.addComponent(pi);
		progressLayout.setComponentAlignment(pi, Alignment.MIDDLE_LEFT);
		progressLayout.addComponent(cancelProcessing);
		
		
	}

	public Window asWindow() {
		return this;
	}

	public void setText(String txt) {
		status.setValue(txt);
	}

	public Button getCancelButton() {
		return cancelProcessing;
	}

	public Upload getUpload() {
		return upload;
	}

	public ProgressIndicator getPI() {
		return pi;
	}

	public void setProgressLayoutVisible(boolean visible) {
		progressLayout.setVisible(visible);
	}

	public void addUpload(Upload upload) {
		VerticalLayout vp = new VerticalLayout();
		vp.setSpacing(true);
		setContent(vp);
		
		vp.addComponent(status);
		vp.addComponent(upload);
		vp.addComponent(progressLayout);
		
		upload.setImmediate(true);
		upload.setButtonCaption("PDF-Datei wählen");
		
	}
	
	
}
