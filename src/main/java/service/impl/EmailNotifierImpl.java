package service.impl;

import service.BeverageQuantityChecker;
import service.EmailNotifier;


public class EmailNotifierImpl implements EmailNotifier {

    private BeverageQuantityChecker beverageQuantityChecker;


    public EmailNotifierImpl(BeverageQuantityChecker beverageQuantityChecker) {
        this.beverageQuantityChecker = beverageQuantityChecker;
    }

    public void notifyMissingDrink(BeverageQuantityChecker beverageQuantityChecker) {
        // send a mail with  information
        if (beverageQuantityChecker.getShortageReason().equals("")) {
            System.out.print("unable to deliver a drink but unkown reason");
        } else
            System.out.print("unable to deliver a drink because of " + beverageQuantityChecker.getShortageReason() + "please Refill it");
    }
}
