package order;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.BeverageQuantityChecker;
import service.EmailNotifier;

import type.CoffeeType;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderTest {

    @Mock
    EmailNotifier emailNotifier;

    @Mock
    BeverageQuantityChecker beverageQuantityChecker;

    private Order order;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        order=new Order(emailNotifier,beverageQuantityChecker);
    }
    @Test
    public void testSendOrderToDrinkMakerWhenOrderingOrangeWithSufficientPrice(){
        //given
        initOrder(CoffeeType.ORANGE, 0, 0.8);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:Order has been sent-O::");
    }

    @Test
    public void testSendOrderToDrinkMakerWhenOrderingOrangeWithInSufficientPrice(){
        //given
        initOrder(CoffeeType.ORANGE, 0, 0.4);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:insufficient Money-Missing 0.2 to buy an Orange");
    }
    @Test
    public void testSendOrderToDrinkMakerWhenOrderingHotDrink(){
        //given
        final Order order=initOrder(CoffeeType.HOT_CHOCOLATE, 2, 0.8);
        //when
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"M:Order has been sent-Hh:2:0");
        verify(beverageQuantityChecker,times(1)).isEmpty(CoffeeType.HOT_CHOCOLATE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void testSendOrderShouldSendAnNotifBecauseMissingWater(){
        //given
        final Order order=initOrder(CoffeeType.HOT_CHOCOLATE, 2, 0.8);
        //When
        when(beverageQuantityChecker.isEmpty(CoffeeType.HOT_CHOCOLATE)).thenReturn(Boolean.TRUE);
        when(beverageQuantityChecker.getShortageReason()).thenReturn("WATER IS MISSING");
        final String result =order.sendOrderToDrinkMaker();
        //then
        assertEquals(result,"Cannot deliver HOT_CHOCOLATE because WATER IS MISSING and email was sent to technician");
        verify(beverageQuantityChecker,times(1)).isEmpty(CoffeeType.HOT_CHOCOLATE);
        verify(beverageQuantityChecker,times(1)).getShortageReason();
        verify(emailNotifier,times(1)).notifyMissingDrink((BeverageQuantityChecker) any());
    }



    @Test
    public void testSendOrderToDrinkMakerShouldFailBecauseNoCoffeeType() {
        //given
        final Order strangeOrder = initOrder(null, 2, 0.3);
        //when
        final String result =strangeOrder.sendOrderToDrinkMaker();
        //Then
        assertEquals(result,"M:Cannot process your order-Coffee type is required");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.HOT_CHOCOLATE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void  testSendOrderShouldFailWhenOrderingCoffeeWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.COFFEE, 2, 0.5);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.1 to buy a Coffee");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.COFFEE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void  testSendOrderShouldFailWhenOrderingHotCoffeeWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.HOT_COFFEE, 2, 0.5);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.1 to buy a Hot Coffee");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.HOT_COFFEE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingTeaWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.TEA, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.2 to buy a Tea");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.TEA);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingHotTeaWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.HOT_TEA, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.2 to buy a Hot Tea");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.HOT_TEA);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingChocolateWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.CHOCOLATE, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.3 to buy a Chocolate");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.CHOCOLATE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
    }

    @Test
    public void testSendOrderShouldFailWhenOrderingHotChocolateWithSugarWithInsufficientPrice() {
        //given
        final Order orderWithInsufficientMoney = initOrder(CoffeeType.HOT_CHOCOLATE, 2, 0.2);
        //when
        final String result = orderWithInsufficientMoney.sendOrderToDrinkMaker();
        //Then
        assertEquals(result, "M:insufficient Money-Missing 0.3 to buy a Hot Chocolate");
        verify(beverageQuantityChecker,times(0)).isEmpty(CoffeeType.HOT_CHOCOLATE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
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
        verify(beverageQuantityChecker,times(1)).isEmpty(CoffeeType.COFFEE);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
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
        verify(beverageQuantityChecker,times(1)).isEmpty(CoffeeType.TEA);
        verify(emailNotifier,times(0)).notifyMissingDrink((BeverageQuantityChecker) any());
        verify(beverageQuantityChecker,times(0)).getShortageReason();
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
        order.setCoffeeType(coffeeType);
        order.setSuggarNumber(suggarNumber);
        order.setMoney(money);
        return order;
    }
}