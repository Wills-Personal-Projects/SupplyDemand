package com.SupplyDemand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class would be the point of interaction of your system - it accepts input and provides aggregated output.
 */
public class SupplyDemand {

    private SupplyDemandUtil util;

    /**
     * Class constructor - you may set up any needed objects and data here. Specification of constructor is optional - it is acceptable if you leave it blank.
     */
    public SupplyDemand() {
        util = new SupplyDemandUtil();
    }

    /**
     * This method accepts a single command and carry out the instruction given. You do not need to (and probably shouldn't) do everything in this method - delegate responsibilities to other classes.
     */
    public void processInput(String command) {
        String[] commands = command.split(",");
        if (commands.length == 4 && commands[0].toLowerCase().trim().equals("publish")){
            util.publish(commands);
        }else if(commands.length == 3 && commands[0].toLowerCase().trim().equals("subscribe")){
            util.subscribe(commands);
        }else if(commands.length == 3 && commands[0].toLowerCase().trim().equals("unsubscribe")){
            util.unsubscribe(commands);
        }
    }

    public ArrayList<String> getAggregatedOutput() {
        return util.aggregateNotifs();
    }

    /**
     * Finally, this method would be called to clear all saved information in the system, so that information from previous round would not be carried to next round. After calling this method the system should be effectively starting anew.
     */
    public void reset() {
        util.utilReset();
    }
}
