package order;

import org.junit.Test;
import type.CoffeeType;

import static org.junit.Assert.assertEquals;

public class OrderTest {


    @Test
    public void testSendOrderToDrinkMakerWhenOrderingOrangeWithSufficientPrice(){
        //given
        final Order order= initOrder(CoffeeType.ORANGE, 0, 0.8);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:Order has been sent-O::");
    }

    @Test
    public void testSendOrderToDrinkMakerWhenOrderingOrangeWithInSufficientPrice(){
        //given
        final Order order= initOrder(CoffeeType.ORANGE, 0, 0.4);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:insufficient Money-Missing 0.2 to buy an Orange");
    }
    @Test
    public void testSendOrderToDrinkMakerWhenOrderingHotDrink(){
        //given
        final Order order= initOrder(CoffeeType.HOT_CHOCOLATE, 2, 0.8);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"M:Order has been sent-Hh:2:0");
    }
    @Test
    public void testSendOrderToDrinkMakerShouldFailBecauseNoCoffeeType() {
        //given
        final Order strangeOrder = initOrder(null, 2, 0.3);
        //when
        final String result =strangeOrder.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:Cannot process your order-Coffee type is required");
    }

    @Test
    public void  testSendOrderShouldFailWhenOrderingCoffeeWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.COFFEE, 2, 0.5);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.1 to buy a Coffee");
    }

    @Test
    public void  testSendOrderShouldFailWhenOrderingHotCoffeeWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.HOT_COFFEE, 2, 0.5);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.1 to buy a Hot Coffee");
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingTeaWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.TEA, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.2 to buy a Tea");
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingHotTeaWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.HOT_TEA, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.2 to buy a Hot Tea");
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingChocolateWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.CHOCOLATE, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.3 to buy a Chocolate");
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingHotChocolateWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.HOT_CHOCOLATE, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.3 to buy a Hot Chocolate");
    }


    @Test
    public void  testSendOrderShouldSendCoffeeWithSugarWithSufficientPrice() {
        //given
        final Order order = initOrder(CoffeeType.COFFEE, 2, 0.8);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"M:Order has been sent-C:2:0");
        assertEquals(order.getSuggarNumber(),0);
        assertEquals(order.getCoffeeType(),null);
    }

    @Test
    public void testSendOrderShouldSendTeaWithoutSuggar(){
        final Order order = initOrder(CoffeeType.TEA, 0, 0.8);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"M:Order has been sent-T::");
        assertEquals(order.getSuggarNumber(),0);
        assertEquals(order.getCoffeeType(),null);
    }


    @Test

    public void  testAddingSuggarShouldNotModifySuggarNumberBecauseOfSuggarLimit() {
        //given
        final Order order = initOrder(CoffeeType.COFFEE, 2, 0);
        //when
        order.addingSuggar(1);
        //then
        assertEquals(order.getSuggarNumber(),2);
    }

    @Test
    public void testAddingSuggarShouldAddOneSuggar(){
        //given
        final Order order = initOrder(CoffeeType.COFFEE, 1, 0);
        //when
        order.addingSuggar(1);
        //then
        assertEquals(order.getSuggarNumber(),2);
    }


    private Order initOrder(CoffeeType coffeeType, int suggarNumber, double money) {
        Order order=new Order();
        order.setCoffeeType(coffeeType);
        order.setSuggarNumber(suggarNumber);
        order.setMoney(money);
        return order;
    }
}