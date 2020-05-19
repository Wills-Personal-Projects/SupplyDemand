package com.SupplyDemand;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class EventBroker {
    private static EventBroker broker = new EventBroker();
    private ArrayList<String> notifs;
    private LinkedHashMap<String, ArrayList<Subscription>> subs;

    private EventBroker(){
        notifs = new ArrayList<>();
        subs = new LinkedHashMap<>();
    }

    public static EventBroker getBroker(){
        return broker;
    }

    public void fireEvent(Event e){
        //call filter event here
        if(e.getSourceId() == 0){
            handlePubEvent((PubEvent) e);
        }else if(e.getSourceId() ==  1){
            handleSubEvent((SubEvent) e);
        }else if(e.getSourceId() == 2){
            handleUnSubEvent((UnSubEvent) e);
        }else{
            //error handling here
        }
    }

    private void handlePubEvent(PubEvent p){
        boolean notified = false;
        //iterate through retailers that have subscribed to a product category
        for (String rName: subs.keySet()){
            //iterate through each subscription belonging to a retailer
            for (int i = 0; i < subs.get(rName).size() && !notified; i++){
                Subscription sub = subs.get(rName).get(i);
                //if this retailer is subscribed to the newly published product category, then notify them
                if (p.getProdCat().equals(sub.getProdCat())){
                    //add notification
                    notified = true;
                    notifs.add(rName + " notified of " + p.getBrand() + " brand " + p.getProdCat() + " from " + p.getProdName());
                }
            }
            notified = false;
        }
    }

    private void handleSubEvent(SubEvent s){
        //add a subscripton for this retailer
        if (subs.containsKey(s.getSourceName())){
            Subscription ns = new Subscription(s);
            subs.get(s.getSourceName()).add(ns);
        }else{
            ArrayList<Subscription> newSubs = new ArrayList<>();
            newSubs.add(new Subscription(s));
            subs.put(s.getSourceName(), newSubs);
        }
    }

    private void handleUnSubEvent(UnSubEvent u){
        //remove a subscription for this retailer
        boolean removed = false;
        if (subs.containsKey(u.getSourceName()) && !subs.get(u.getSourceName()).isEmpty()){
            for (int i = 0; i < subs.get(u.getSourceName()).size() && !removed; i++){
                Subscription sub = subs.get(u.getSourceName()).get(i);
                if (sub.getProdCat().equals(u.getProdCat())){
                    removed = true;
                    subs.get(u.getSourceName()).remove(sub);
                }
            }
        }
        if(subs.get(u.getSourceName()).isEmpty()){
            subs.remove(u.getSourceName());
        }
    }

    public ArrayList<String> getNotifications(){
        return notifs;
    }

    public void reset(){
        notifs.clear();
        subs.clear();
    }
}