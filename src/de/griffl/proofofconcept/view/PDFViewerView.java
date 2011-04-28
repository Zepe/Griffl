package de.griffl.proofofconcept.view;

import com.vaadin.ui.Window;

import de.griffl.proofofconcept.presenter.PDFViewerPresenter.Display;

public class PDFViewerView extends Window implements Display {
	public PDFViewerView(String name){
		super(name);
		
	}

	public Window asWindow() {
		return this;
	}

}
