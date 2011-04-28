import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;

import de.griffl.proofofconcept.db.DBsettings;
import de.griffl.proofofconcept.pdf.PDFDocument;


	
public class TestClass {
	public static void main(String ... args){
		File f = new File("/Users/sebastianschneider/Desktop/barcodes-new2.pdf");
		System.out.println(f.length());
		byte[] buffer = new byte[(int)f.length()];
		InputStream in = null;
		try {
			in = new FileInputStream(f);
			in.read(buffer);
			in.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PDFDocument pdfDoc = new PDFDocument();
		pdfDoc.setDocument(buffer);
		HttpClient httpClient = new StdHttpClient.Builder()
			.host(DBsettings.HOST)
			.port(DBsettings.PORT)
			.build();
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector dbC = new StdCouchDbConnector(DBsettings.DATABASE, dbInstance);
		dbC.create(pdfDoc);
		
		
		Document doc = new Document();
		InputStream inp = new ByteArrayInputStream(pdfDoc.getDocument());
		try {
			doc.setInputStream(inp, "Testfile");
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
}
