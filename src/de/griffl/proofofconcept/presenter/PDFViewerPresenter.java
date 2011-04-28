package de.griffl.proofofconcept.presenter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import de.griffl.proofofconcept.pdf.PDFDocument;

public class PDFViewerPresenter implements Presenter{

	public interface Display{
		public Window asWindow();
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
	}
	public Window go(Application app) {
		app.addWindow(display.asWindow());
		return display.asWindow();
	}

}
