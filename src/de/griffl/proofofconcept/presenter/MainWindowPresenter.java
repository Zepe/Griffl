package de.griffl.proofofconcept.presenter;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;

import de.griffl.proofofconcept.ProofofconceptApplication;
import de.griffl.proofofconcept.pdf.PDFDocument;


public class MainWindowPresenter implements Presenter{
	public interface Display {
		Window asWindow();
		void setText(String txt);
		Button getCancelButton();
		void addUpload(Upload upload);
		ProgressIndicator getPI();
		void setProgressLayoutVisible(boolean visible);
		
	
	}
	private Display display;
	private Upload upload;
	public MainWindowPresenter(Display display){
		
		PDFReceiver receiver = new PDFReceiver();
		upload = new Upload(null, receiver);
		this.display = display;
		
		this.display.addUpload(upload);
		bind();
		
	}
	
	private void bind(){
		display.getCancelButton().addListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
                upload.interruptUpload();
            }
		});
		
		upload.addListener(new Upload.StartedListener() {
			
			public void uploadStarted(StartedEvent event) {
				 display.getCancelButton().setVisible(false);
	                display.setProgressLayoutVisible(true);
	                display.getPI().setValue(0f);
	                display.getPI().setPollingInterval(500);
	                display.setText("Dokument \"" + event.getFilename()
	                        + "\" wird hochgeladen.");
			}
		});
		
		upload.addListener(new Upload.ProgressListener() {
			
			public void updateProgress(long readBytes, long contentLength) {
				display.getPI().setValue(new Float(readBytes / (float) contentLength));
			
			}
		});
		
		upload.addListener(new Upload.SucceededListener() {
			
			public void uploadSucceeded(SucceededEvent event) {
				display.setText("Dokument \"" + event.getFilename()
                        + "\" erfolgreich hochgeladen");
				
				byte[] binaryFile = os.toByteArray();
				
				PDFDocument pdfDoc = new PDFDocument();
				
				pdfDoc.setDocument(binaryFile);
				
				ProofofconceptApplication.db.get().create(pdfDoc);
				
				
			}
		});
			
		upload.addListener(new Upload.FailedListener() {
			
			public void uploadFailed(FailedEvent event) {
				display.setText("Vorgang wurde unterbrochen");				
			}
		});
		
		upload.addListener(new Upload.FinishedListener() {
			
			public void uploadFinished(FinishedEvent event) {
				display.setProgressLayoutVisible(false);
				upload.setVisible(true);
                
               	
			}
		});
	}
	
	private ByteArrayOutputStream os = new ByteArrayOutputStream();
	
	class PDFReceiver implements Receiver {
		 	            private static final long serialVersionUID = -1276759102490466761L;
		  	
		  	            public String filename; // The original filename
		  	           
		  	            public OutputStream receiveUpload(String filename, String mimeType) {
		  	                this.filename = filename;
		  	                os.reset(); // If re-uploading
		  	                return os;
		  	            }
	}

	public void go(Application app) {
		app.setMainWindow(display.asWindow());
	}
	
	
	
}
