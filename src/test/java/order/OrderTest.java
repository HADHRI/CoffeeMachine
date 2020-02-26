package order;

import org.junit.Test;
import type.CoffeeType;

import static org.junit.Assert.assertEquals;

public class OrderTest {


    @Test
    public void testSendOrderToDrinkMakerShouldFailBecauseNoCoffeeType() {
        //given
        final Order strangeOrder=initOrder(null,2);
        //when
        final String result =strangeOrder.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:Cannot process your order-Coffee type is required");
    }

    @Test

    public void testSendOrderShouldSendCoffeWithSugar(){
        //given
        final Order order=initOrder(CoffeeType.COFFEE,2);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"M:order.Order has been sent-C:2:0");
        assertEquals(order.getSuggarNumber(),0);
        assertEquals(order.getCoffeeType(),null);
    }

    @Test
    public void testSendOrderShouldSendTeaWithoutSuggar(){
        final Order order=initOrder(CoffeeType.TEA,0);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"M:order.Order has been sent-T::");
        assertEquals(order.getSuggarNumber(),0);
        assertEquals(order.getCoffeeType(),null);
    }


    @Test
    public void testAddingSuggarShouldNotModifySuggarNumberBecauseOfSuggarLimit() {
        //given
        final Order order=initOrder(CoffeeType.COFFEE,2);
        //when
        order.addingSuggar(1);
        //then
        assertEquals(order.getSuggarNumber(),2);
    }

    @Test
    public void  testAddingSuggarShouldAddOneSuggar(){
        //given
        final Order order=initOrder(CoffeeType.COFFEE,1);
        //when
        order.addingSuggar(1);
        //then
        assertEquals(order.getSuggarNumber(),2);
    }




    private Order initOrder(CoffeeType coffeeType, int suggarNumber){
        Order order=new Order();
        order.setCoffeeType(coffeeType);
        order.setSuggarNumber(suggarNumber);
        return order;
    }
}