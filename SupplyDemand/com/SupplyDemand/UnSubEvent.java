package com.SupplyDemand;

public class UnSubEvent implements Event{

    private String prodCat;

    private int sourceId;

    private String sourceName;

    public UnSubEvent(String prodCat, String name) {
        this.prodCat = prodCat;
        this.sourceId  = 2;
        this.sourceName = name;
    }

    public int getSourceId() {
        return this.sourceId;
    }

    public String getProdCat() {
        return this.prodCat;
    }

    public String getSourceName(){return this.sourceName; }
}
