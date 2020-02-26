package order;

import exceptions.CoffeTypeNotMentionnedException;
import exceptions.NotEnoughMoneyException;
import type.CoffeeType;

import java.math.BigDecimal;

public class Order {

    private CoffeeType coffeeType;
    private int suggarNumber;
    private double money;
    private static final double TEA_PRICE = 0.4;
    private static final double COFFEE_PRICE = 0.6;
    private static final double CHOCOLATE_PRICE = 0.5;
    private static final int SUGGAR_LIMIT = 2;

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getSuggarNumber() {
        return suggarNumber;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private String createInstructionFromOrder() throws CoffeTypeNotMentionnedException, NotEnoughMoneyException {
        if (coffeeType == null) {
            throw new CoffeTypeNotMentionnedException("Coffee type required before sending an order");
        }
        switch (coffeeType) {
            case COFFEE:
                if (money < COFFEE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(COFFEE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Coffee");
                break;
            case TEA:
                if (money < TEA_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(TEA_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Tea");
                break;
            case CHOCOLATE:
                if (money < CHOCOLATE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(CHOCOLATE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Chocolate");
                break;
            default:
        }
        StringBuilder orderTransleted = new StringBuilder();
        final boolean suggarExist = suggarNumber > 0 ;
        orderTransleted.append(coffeeType.getCoffeeType()).append(":").append(suggarExist ? suggarNumber : "").append(":").append(suggarExist ? 0 : "");
        return orderTransleted.toString();
    }

    public void setSuggarNumber(int suggarNumber) {
        if(suggarNumber<=SUGGAR_LIMIT)
            this.suggarNumber = suggarNumber;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public String sendOrderToDrinkMaker()  {
        try {
            String orderToSend=this.createInstructionFromOrder();
            System.out.println("Order To send "+orderToSend);
            resetOrder();
            return "M:Order has been sent-"+orderToSend;
        } catch (CoffeTypeNotMentionnedException e) {
            return "M:Cannot process your order-Coffee type is required";
        } catch (NotEnoughMoneyException e) {
            return e.getMessage();
        }

    }


    private void resetOrder() {
        this.coffeeType = null;
        this.suggarNumber = 0;
    }

    public void addingSuggar(int suggarToAdd) {
        if (suggarNumber + suggarToAdd <= SUGGAR_LIMIT) {
            this.suggarNumber = suggarToAdd + suggarNumber;
        }
    }


}
