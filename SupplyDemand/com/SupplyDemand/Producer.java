package com.SupplyDemand;

public class Producer implements IPublisher {

    private String name;

    private EventBroker brokerRef = EventBroker.getBroker();

    public Producer(String name) {
        this.name = name;
    }
    /**
     *   Publish a message about products available.
     *
     *
     */
    public void publish(String prodCat, String brand){
        brokerRef.fireEvent(new PubEvent(this.name, prodCat, brand));
    }

    public String getName(){return this.name;}
}
