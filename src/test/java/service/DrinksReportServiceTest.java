package service;

import collection.OrderList;
import exceptions.UnableToCreateReportException;
import order.Order;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.impl.DrinksReportServiceImpl;
import type.CoffeeType;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;


public class DrinksReportServiceTest {


    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    OrderList orderList;

    private  DrinksReportServiceImpl drinksReportService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
        drinksReportService=new DrinksReportServiceImpl();
    }



    @Test
    public void doReportShouldNotCreateReportBecauseOfNullOrderList() throws UnableToCreateReportException {
        //given
        expectedEx.expect(UnableToCreateReportException.class);
        expectedEx.expectMessage("Order list is null");
        //when
        drinksReportService.doReport(null);
        //then
    }
    @Test
    public void  doReportShouldNotCreateReportBecauseOfNullPointerException() throws UnableToCreateReportException {
        //given
        expectedEx.expect(UnableToCreateReportException.class);
        expectedEx.expectMessage("java.lang.NullPointerException");
        //when
        when(orderList.getCoffeeTypeOrderMap()).thenReturn(getMapWithNullList());
        drinksReportService.doReport(orderList);
        //then
    }

    @Test
    public void doReportWithEmptyOrderList() throws UnableToCreateReportException {
        //given
        //when
        when(orderList.getCoffeeTypeOrderMap()).thenReturn(new HashMap<CoffeeType, List<Order>>());
       final String result= drinksReportService.doReport(orderList);
        //then
        assertEquals(result,"Order List is empty , Nothing to report for now");

    }
    @Test
    public void doReportWithFilledOrderList() throws UnableToCreateReportException {
        //given
        final Map<CoffeeType,List<Order>> filledMap= getFilledOrderList();
        //when
        when(orderList.getCoffeeTypeOrderMap()).thenReturn(filledMap);
        final String result=drinksReportService.doReport(orderList);
        //then
        assertEquals(result,"There is 1 Of COFFEE That was sold and 0 Of Hot Coffee was sold and 1 Of Orange was sold and 0 Of Tea was sold and 1 Of Hot tea was sold and 1 Of Chocolate was sold and 0 Of Hot Chocolate was sold and total money earned is 2.2");

    }


    private ArrayList<Order>getOrderArrayList(CoffeeType coffeeType){
        ArrayList<Order>arrayList=new ArrayList<Order>();
        Order firstOrder=new Order(null,null);
        firstOrder.setCoffeeType(coffeeType);
        firstOrder.setMoney(0.8);
        arrayList.add(firstOrder);
        return arrayList;
    }


    private Map<CoffeeType,List<Order>> getFilledOrderList(){
        Map<CoffeeType, List<Order>> map=new HashMap<CoffeeType, List<Order>>();
        map.put(CoffeeType.HOT_TEA,getOrderArrayList(CoffeeType.HOT_TEA));
        map.put(CoffeeType.CHOCOLATE,getOrderArrayList(CoffeeType.CHOCOLATE));
        map.put(CoffeeType.ORANGE,getOrderArrayList(CoffeeType.ORANGE));
        map.put(CoffeeType.COFFEE,getOrderArrayList(CoffeeType.COFFEE));
        return map;
    }


    private Map<CoffeeType, List<Order>> getMapWithNullList(){
        Map<CoffeeType, List<Order>> map=new HashMap<CoffeeType, List<Order>>();
        map.put(CoffeeType.COFFEE,null);
        return map;
    }
}