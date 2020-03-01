package service.impl;

import collection.OrderList;
import service.BeverageQuantityChecker;
import type.CoffeeType;


public class BeverageQuantityCheckerImpl implements BeverageQuantityChecker {

    private OrderList orderList;
    private String shortage="";

    private static final  int MAXIMUM_WATER_QUANTITY = 200;
    private static final  int MAXIMUM_MILK_QUANTITY = 200;

    public BeverageQuantityCheckerImpl(OrderList orderList) {
        this.orderList = orderList;
    }


    public boolean isEmpty(CoffeeType drink) {
        if(orderList==null)
            return false;
        int usedWaterQuantity = orderList.getUsedWaterQuantity();
        int usedMilkQuantity = orderList.getUsedMilkQuantity();
        boolean result=true;
        switch (drink) {
            case COFFEE:
            case HOT_COFFEE:
            case CHOCOLATE:
            case HOT_CHOCOLATE:
                result = MAXIMUM_WATER_QUANTITY > usedWaterQuantity && MAXIMUM_MILK_QUANTITY > usedMilkQuantity;
                if(MAXIMUM_WATER_QUANTITY == usedWaterQuantity)
                    shortage="WATER IS MISSING";
                 else if(MAXIMUM_MILK_QUANTITY == usedMilkQuantity){
                    shortage="MILK IS MISSING";
                }
                break;
            case TEA:
            case HOT_TEA:
            case ORANGE:
                result = MAXIMUM_WATER_QUANTITY > usedWaterQuantity;
                if(MAXIMUM_WATER_QUANTITY == usedWaterQuantity)
                    shortage="WATER IS MISSING";
                break;
            default:
        }
        return !result;

    }

    public String getShortageReason() {
        return shortage;
    }


}
