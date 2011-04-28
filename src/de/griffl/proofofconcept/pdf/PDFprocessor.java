package de.griffl.proofofconcept.pdf;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public final class PDFprocessor {
	private static final Logger logger = Logger.getLogger(PDFprocessor.class.getName());
	private static final Document document = new Document();
	
	public PDFprocessor(){
	}
	
	public static void setDocument(InputStream is, String filepath){
		try {
			document.setInputStream(is, filepath);
		} catch (PDFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PDFSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			File t = new File(filepath);
			t.delete();
			logger.info("file "+filepath+" deleted successfully");
		}
	}
	
	public static Image getImage(int page){
		return document.getPageImage(page,  GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, 0, 1);
	}
}
