import java.util.ArrayList;
import java.util.List;

public class Retailer implements ISubscriber {

	private String name;

	private EventBroker brokerRef = EventBroker.getBroker();

	public Retailer(String name) {
		this.name = name;
	}
	/**
	 *   Subscribe to a product type to receive notifications related to it.  
	 *   
	 * 
	 */
	public void subscribe(String prodCat, int time){
		brokerRef.fireEvent(new SupplyDemand.SubEvent(name, prodCat, time));
	}

	/**
	 *   Unsubscribe from a product type to stop receiving notifications about it.  
	 *   
	 * 
	 */
	public void unsubscribe(String prodCat){
		brokerRef.fireEvent(new SupplyDemand.UnSubEvent(prodCat, name));
	}

	public String getName(){
		return this.name;
	}
}
