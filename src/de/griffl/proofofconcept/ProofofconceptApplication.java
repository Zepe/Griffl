package de.griffl.proofofconcept;

// hello

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.vaadin.Application;
import com.vaadin.ui.Window;

import de.griffl.proofofconcept.db.DBsettings;
import de.griffl.proofofconcept.db.PDFRepository;
import de.griffl.proofofconcept.pdf.PDFDocument;
import de.griffl.proofofconcept.presenter.MainWindowPresenter;
import de.griffl.proofofconcept.presenter.PDFViewerPresenter;
import de.griffl.proofofconcept.view.MainWindowView;
import de.griffl.proofofconcept.view.PDFViewerView;


public class ProofofconceptApplication extends Application {
//public static final ThreadLocal<CouchDbConnector> db = new ThreadLocal<CouchDbConnector>();
	private static  HttpClient httpClient = new StdHttpClient.Builder()
	.host(DBsettings.HOST)
	.port(DBsettings.PORT)
	.build();
	
	private static CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
	public static CouchDbConnector dbC = new StdCouchDbConnector(DBsettings.DATABASE, dbInstance);
	public static PDFRepository repository = new PDFRepository(PDFDocument.class, dbC);
	
	@Override
	public void init() {
		 HttpClient httpClient = new StdHttpClient.Builder()
		.host(DBsettings.HOST)
		.port(DBsettings.PORT)
		.build();
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector dbC = new StdCouchDbConnector(DBsettings.DATABASE, dbInstance);
		//db.set(dbC);
		
		MainWindowView mwv = new MainWindowView("Hauptfenster");
		MainWindowPresenter mwp = new MainWindowPresenter(mwv);
		 
		mwp.go(this);
	}
	
	
	public Window getWindow(String name){
		
		if(super.getWindow(name) == null && repository.contains(name)){
			
			PDFDocument doc = repository.get(name);
			PDFViewerView pvv = new PDFViewerView(name);
			PDFViewerPresenter pvp = new PDFViewerPresenter(pvv, doc);
			
			return pvp.go(this); 
		}
		
		return super.getWindow(name);
		
	}

}
