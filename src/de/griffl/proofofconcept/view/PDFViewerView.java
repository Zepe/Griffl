package de.griffl.proofofconcept.view;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.vaadin.Application;
import com.vaadin.event.MouseEvents;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.gwt.client.ui.Icon;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.griffl.proofofconcept.ProofofconceptApplication;
import de.griffl.proofofconcept.presenter.PDFViewerPresenter;
import de.griffl.proofofconcept.presenter.PDFViewerPresenter.Display;

public class PDFViewerView extends Window implements Display {
	
	private Button forward = new Button(">");
	private Button backward = new Button ("<");
	private Panel ground = new Panel();
	private static final Logger logger = Logger.getLogger(PDFViewerView.class.getName());
	private Application app;
	
	public PDFViewerView(String name, Application app){
				super(name);
		
				setTheme("grifflmytesttheme");
				
				Label label = new Label("Document name");
				addComponent(label);
				
				HorizontalLayout hl = new HorizontalLayout();
				ground.setHeight("600px");
				ground.setWidth("400px");
				hl.addComponent(ground);
				
				final Panel marginalRight = new Panel();
				
				hl.addComponent(marginalRight);
			
			//	marginalRight.setLayout(new VerticalLayout());
				addComponent(hl);
				addComponent(marginalRight);
				
	
				this.app = app;
				
				
		}


	public Window asWindow() {
		return this;
	}


	public void setPage(Image currentPageIm) {
		ground.setIcon(createStreamResource(currentPageIm));
		requestRepaint();
	}


	public Button getForwardButton() {
		return forward;
	}


	public Button getBackwardButton() {
		return backward;
	}
	
	
	private Resource createStreamResource(final Image im) {
		StreamResource.StreamSource curIm = new StreamResource.StreamSource() {
			private ByteArrayOutputStream imagebuffer = null;
			
			public InputStream getStream() {
				imagebuffer = new ByteArrayOutputStream();
				try {
					ImageIO.write((RenderedImage) im, "png", imagebuffer);
				} catch (IOException e) {
					return null;
				}
				return new ByteArrayInputStream(imagebuffer.toByteArray());
			}
		};
		return new StreamResource(curIm, "myImage.png", app);
	}
	

}
