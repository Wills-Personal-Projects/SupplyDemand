public class SubEvent implements Event{

	private String prodCat;

	private int sourceId;

	private String sourceName;

	private long timeStamp;

	public SubEvent(String name, String prodCat, long ts) {
		this.prodCat = prodCat;
		this.sourceId = 1;
		this.sourceName = name;
		this.timeStamp = ts;
	}

	public int getSourceId() {
		return this.sourceId;
	}

	public String getProdCat() {
		return this.prodCat;
	}

	public String getSourceName(){return this.sourceName; }

	public long getTimeStamp(){return this.timeStamp; }

}
