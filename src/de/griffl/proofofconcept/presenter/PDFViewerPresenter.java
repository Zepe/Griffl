package de.griffl.proofofconcept.presenter;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;

import de.griffl.proofofconcept.pdf.PDFDocument;

public class PDFViewerPresenter implements Presenter{

	public interface Display{
		public Window asWindow();
		public void setPage(Image currentPageIm);
		public Button getForwardButton();
		public Button getBackwardButton();
	}
	private Display display;
	private Document pdfDoc;
	
	public PDFViewerPresenter(Display display, PDFDocument doc){
		this.display = display;
		pdfDoc = new Document();
		InputStream in = new ByteArrayInputStream(doc.getDocument());
		try {
			pdfDoc.setInputStream(in, "Name :)");
		} catch (PDFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PDFSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bind();
		Image im = pdfDoc.getPageImage(2, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, 0, 1);
		display.setPage(im);
		
	}
	public Window go(Application app) {
//		app.addWindow(display.asWindow());
		return display.asWindow();
	}
	
	public void bind()	{
		
		display.getForwardButton().addListener(new ClickListener(){

			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		display.getBackwardButton().addListener(new ClickListener()	{

			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	

}
