public class Subscription {

	private String prodCat;

	private long timeStamp;

	public Subscription(SubEvent e) {
		this.prodCat = e.getProdCat();
		timeStamp = e.getTimeStamp();
	}

	public String getProdCat(){
		return prodCat;
	}

	public long getTimeStamp(){return timeStamp;}
	public void setTimeStamp(long t){timeStamp = t;}
}
