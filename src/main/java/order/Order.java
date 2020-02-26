package order;

import exceptions.CoffeTypeNotMentionnedException;
import type.CoffeeType;

public class Order {

    private CoffeeType coffeeType;
    private int suggarNumber;
    private static final int SUGGAR_LIMIT = 2;


    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getSuggarNumber() {
        return suggarNumber;
    }

    private String createInstructionFromOrder() throws CoffeTypeNotMentionnedException {
        StringBuilder orderTransleted = new StringBuilder();
        final boolean suggarExist = suggarNumber > 0 ;
        if (coffeeType == null) {
            throw new CoffeTypeNotMentionnedException("Coffe type required before sending an order");
        }
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
            resetOrder();
            return "M:order.Order has been sent-"+orderToSend;
        } catch (CoffeTypeNotMentionnedException e) {
            return "M:Cannot process your order-Coffee type is required";
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
