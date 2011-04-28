package de.griffl.proofofconcept;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import com.vaadin.Application;
import com.vaadin.ui.*;

import de.griffl.proofofconcept.presenter.MainWindowPresenter;
import de.griffl.proofofconcept.view.MainWindowView;


public class ProofofconceptApplication extends Application {
public static ThreadLocal<CouchDbConnector> db = new ThreadLocal<CouchDbConnector>();
	
	
	@Override
	public void init() {
		 HttpClient httpClient = new StdHttpClient.Builder()
		.host("localhost")
		.port(5984)
		.build();
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector dbC = new StdCouchDbConnector("testgriffl", dbInstance);
		db.set(dbC);
		
		MainWindowView mwv = new MainWindowView("Hauptfenster");
		MainWindowPresenter mwp = new MainWindowPresenter(mwv);
		 
		mwp.go(this);
	}

}
