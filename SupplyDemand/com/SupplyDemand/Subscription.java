package com.SupplyDemand;

public class Subscription {

    private String prodCat;

    public Subscription(SubEvent e) {
        this.prodCat = e.getProdCat();
    }

    public String getProdCat(){
        return prodCat;
    }
}

