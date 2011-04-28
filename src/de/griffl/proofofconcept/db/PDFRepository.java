package de.griffl.proofofconcept.db;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

import de.griffl.proofofconcept.pdf.PDFDocument;

public class PDFRepository extends CouchDbRepositorySupport<PDFDocument> {

	public PDFRepository(Class<PDFDocument> type, CouchDbConnector db) {
		super(type, db);
	}

}
