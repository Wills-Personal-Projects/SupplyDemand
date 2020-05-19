package com.SupplyDemand;

public class PubEvent implements Event{

    private String prodCat;

    private String brand;

    private int sourceId;

    private String prodName;


    public PubEvent(String name, String prodCat, String brand) {
        this.prodCat = prodCat;
        this.brand = brand;
        this.prodName = name;
        this.sourceId = 0;
    }

    public String getBrand() {
        return brand;
    }

    public String getProdName() { return prodName;}

    public int getSourceId() {
        return this.sourceId;
    }

    public String getProdCat() { return this.prodCat; }
}
