package de.griffl.proofofconcept.pdf;


import org.ektorp.support.CouchDbDocument;
import org.codehaus.jackson.annotate.JsonProperty;

public class PDFDocument extends CouchDbDocument {
	private String id;
	private String revision;
	private byte[] document;
	
	@JsonProperty("_id")
	public String getId() {
		return id;
	}
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("_rev")
	public String getRevision() {
		return revision;
	}
	@JsonProperty("_rev")
	public void setRevision(String revision) {
		this.revision = revision;
	}
	
	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	
	
}
