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
    private static final double HOT_TEA_PRICE=0.4;
    private static final double COFFEE_PRICE = 0.6;
    private static final double HOT_COFFEE_PRICE=0.6;
    private static final double CHOCOLATE_PRICE = 0.5;
    private static final double HOT_CHOCOLATE_PRICE=0.5;
    private static final double ORANGE_PRICE = 0.6;
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
        handleInsufficientMoney(coffeeType);
        StringBuilder orderTransleted = new StringBuilder();
        final boolean suggarExist = suggarNumber > 0 ;
        orderTransleted.append(coffeeType.getCoffeeType()).append(":").append(suggarExist ? suggarNumber : "").append(":").append(suggarExist ? 0 : "");
        return orderTransleted.toString();
    }

    private void handleInsufficientMoney(CoffeeType coffeeType) throws NotEnoughMoneyException {
        switch (coffeeType) {
            case COFFEE:
                if (money < COFFEE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(COFFEE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Coffee");
                break;
            case HOT_COFFEE:
                if(money <HOT_COFFEE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(HOT_COFFEE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Hot Coffee");
                break;
            case TEA:
                if (money < TEA_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(TEA_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Tea");
                break;
            case HOT_TEA:
                if(money <HOT_TEA_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(HOT_TEA_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Hot Tea");
                break;

            case CHOCOLATE:
                if (money < CHOCOLATE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(CHOCOLATE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Chocolate");
                break;
            case HOT_CHOCOLATE:
                if(money < HOT_CHOCOLATE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY + (BigDecimal.valueOf(HOT_CHOCOLATE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy a Hot Chocolate");
                break;
            case ORANGE:
                if(money < ORANGE_PRICE)
                    throw new NotEnoughMoneyException(NotEnoughMoneyException.MISSING_MONEY +(BigDecimal.valueOf(ORANGE_PRICE).subtract(BigDecimal.valueOf(money))) + " to buy an Orange");
            default:
        }
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
