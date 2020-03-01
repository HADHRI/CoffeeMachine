package service;

import type.CoffeeType;

public interface EmailNotifier {
    void notifyMissingDrink(BeverageQuantityChecker beverageQuantityChecker);
}