package com.SupplyDemand;

public class SubEvent implements Event{

    private String prodCat;

    private int sourceId;

    private String sourceName;

    public SubEvent(String name, String prodCat) {
        this.prodCat = prodCat;
        this.sourceId = 1;
        this.sourceName = name;
    }

    public int getSourceId() { return this.sourceId; }

    public String getProdCat() { return this.prodCat; }

    public String getSourceName(){return this.sourceName; }
}
