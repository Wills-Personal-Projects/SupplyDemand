package com.SupplyDemand;

import java.util.*;

public class SupplyDemandUtil {
    private static ArrayList<Retailer> retailers;
    private static ArrayList<Producer> producers;
    private EventBroker brokerRef;

    public SupplyDemandUtil(){
        this.retailers = new ArrayList<>();
        this.producers = new ArrayList<>();
        brokerRef = EventBroker.getBroker();
    }

    public Retailer findRet(String retName){
        for (int i = 0; i < retailers.size(); i++) {
            if (retailers.get(i).getName().equals(retName)) {
                return retailers.get(i);
            }
        }
        return new Retailer(retName);
    }

    private Boolean retExist(String retName){
        for (int i = 0; i < retailers.size(); i++){
            if (retailers.get(i).getName().equals(retName)){
                return true;
            }
        }
        return false;
    }

    private Boolean prodExist(String prodName){
        for (int i = 0; i < producers.size(); i++){
            if (producers.get(i).getName().equals(prodName)){
                return true;
            }
        }
        return false;
    }

    private Producer findProd(String prodName){
        Producer found = null;
        for (int i = 0; i < producers.size(); i++) {
            if (producers.get(i).getName().equals(prodName)) {
                found = producers.get(i);
            }
        }
        return found;
    }

    public void publish(String[] commands) {
        String prodName = commands[1].trim().toLowerCase();
        String prodCat = commands[2].trim().toLowerCase();
        String brand = commands[3].trim().toLowerCase();
        if (!prodExist(prodName))
            producers.add(new Producer(prodName));
        findProd(prodName).publish(prodCat, brand);
    }

    public void subscribe(String[] commands) {
        String retName = commands[1].trim().toLowerCase();
        String prodCat = commands[2].trim().toLowerCase();
        if (!retExist(retName))
            retailers.add(new Retailer(retName));
        findRet(retName).subscribe(prodCat);
    }

    public void unsubscribe(String[] commands){
        String retName = commands[1].trim().toLowerCase();
        String prodCat = commands[2].trim().toLowerCase();
        if (retExist(retName)) {
            findRet(retName).unsubscribe(prodCat);
        }
    }

    public ArrayList<String> aggregateNotifs(){
        return brokerRef.getNotifications();
    }

    public void utilReset(){
        retailers.clear();
        producers.clear();
        brokerRef.reset();
    }
}
