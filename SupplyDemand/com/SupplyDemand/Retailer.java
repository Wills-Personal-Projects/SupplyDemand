package com.SupplyDemand;

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
    public void subscribe(String prodCat){
        brokerRef.fireEvent(new SubEvent(name, prodCat));
    }

    /**
     *   Unsubscribe from a product type to stop receiving notifications about it.
     *
     *
     */
    public void unsubscribe(String prodCat){
        brokerRef.fireEvent(new UnSubEvent(prodCat, name));
    }

    public String getName(){
        return this.name;
    }
}
