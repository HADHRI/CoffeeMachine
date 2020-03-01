package service.impl;

import collection.OrderList;
import exceptions.UnableToCreateReportException;
import order.Order;
import service.DrinksReportService;
import type.CoffeeType;

import java.util.List;
import java.util.Map;

public class DrinksReportServiceImpl implements DrinksReportService {


    private double getMoneyEarned(long coffeeSoldNumber, long hotCoffeeSoldNumber, long orangeSoldNumber, long teaSoldNumber, long hotTeaSoldNumber, long chocolateSoldNumber, long hotChocolateSoldNumber) {
        return coffeeSoldNumber * Order.getCoffeePrice() + hotCoffeeSoldNumber * Order.getHotCoffeePrice() + orangeSoldNumber * Order.getOrangePrice()
                + teaSoldNumber * Order.getTeaPrice() + hotTeaSoldNumber * Order.getHotTeaPrice() + chocolateSoldNumber * Order.getCoffeePrice()+ hotChocolateSoldNumber * Order.getHotChocolatePrice();
    }

    private String handleNoEmptyOrderList(OrderList orderList){
        StringBuilder report = new StringBuilder();
        final Map<CoffeeType, List<Order>> coffeeTypeOrderMap = orderList.getCoffeeTypeOrderMap();
        final int coffeeSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.COFFEE) ? coffeeTypeOrderMap.get(CoffeeType.COFFEE).size() : 0;
        final int hotCoffeeSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.HOT_COFFEE) ? coffeeTypeOrderMap.get(CoffeeType.HOT_COFFEE).size() : 0;
        final int orangeSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.ORANGE) ? coffeeTypeOrderMap.get(CoffeeType.ORANGE).size() : 0;
        final int teaSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.TEA) ? coffeeTypeOrderMap.get(CoffeeType.TEA).size() : 0;
        final int hotTeaSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.HOT_TEA) ? coffeeTypeOrderMap.get(CoffeeType.HOT_TEA).size() : 0;
        final int chocolateSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.CHOCOLATE) ? coffeeTypeOrderMap.get(CoffeeType.CHOCOLATE).size() : 0;
        final int HotChocolateSoldNumber = coffeeTypeOrderMap.containsKey(CoffeeType.HOT_CHOCOLATE) ? coffeeTypeOrderMap.get(CoffeeType.HOT_CHOCOLATE).size() : 0;
        final double totalMoneyEarned = getMoneyEarned(coffeeSoldNumber, hotCoffeeSoldNumber, orangeSoldNumber, teaSoldNumber, hotTeaSoldNumber, chocolateSoldNumber, HotChocolateSoldNumber);

        System.out.println(totalMoneyEarned);
        final  String linkExpression=" and ";
        report.append("There is ").append(coffeeSoldNumber).append(" Of COFFEE That was sold ").append("and ").append(hotCoffeeSoldNumber)
                .append(" Of Hot Coffee was sold").append(linkExpression).append(orangeSoldNumber)
                .append(" Of Orange was sold").append(linkExpression).append(teaSoldNumber).append(" Of Tea was sold").append(linkExpression).append(hotTeaSoldNumber).append(" Of Hot tea was sold")
                .append(linkExpression).append(chocolateSoldNumber).append(" Of Chocolate was sold").append(linkExpression).append(HotChocolateSoldNumber).append(" Of Hot Chocolate was sold ")
                .append("and total money earned is ").append(totalMoneyEarned);
        System.out.println(report);
        return report.toString();
    }

    public String doReport(OrderList orderList) throws UnableToCreateReportException {
        if (orderList != null && !orderList.getCoffeeTypeOrderMap().isEmpty()) {
            try {
              return handleNoEmptyOrderList(orderList);
            } catch (Exception e) {
                throw new UnableToCreateReportException(e.toString());
            }
        }
        if (orderList == null) {
            throw new UnableToCreateReportException("Order list is null");
        }
        return "Order List is empty , Nothing to report for now";

    }

}