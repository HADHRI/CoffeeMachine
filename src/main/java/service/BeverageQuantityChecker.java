package service;

import type.CoffeeType;

public interface BeverageQuantityChecker {
    boolean isEmpty(CoffeeType drink);
    String getShortageReason();
}
