package collection;

import order.Order;
import type.CoffeeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderList {


    private final Map<CoffeeType, List<Order>> coffeeTypeOrderMap = new HashMap<CoffeeType, List<Order>>();

    public Map<CoffeeType, List<Order>> getCoffeeTypeOrderMap() {
        return coffeeTypeOrderMap;
    }

}
