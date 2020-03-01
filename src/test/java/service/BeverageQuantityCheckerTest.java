package service;

import collection.OrderList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.impl.BeverageQuantityCheckerImpl;
import type.CoffeeType;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class BeverageQuantityCheckerTest {

    @Mock
    OrderList orderList;


    private BeverageQuantityChecker beverageQuantityChecker;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        beverageQuantityChecker = new BeverageQuantityCheckerImpl(orderList);
    }

    @Test
    public void testIsEmptyWithNullOrderList() {
        //given
        beverageQuantityChecker = new BeverageQuantityCheckerImpl(null);
        //when
        final boolean result=beverageQuantityChecker.isEmpty(CoffeeType.COFFEE);
        //then
        verify(orderList,times(0)).getUsedMilkQuantity();
        verify(orderList,times(0)).getUsedWaterQuantity();
        assertEquals(result,false);
    }

    @Test
    public void testIsEmptyShouldReturnTrueBecauseOfMissingWater(){
        //given
        //when
        when(orderList.getUsedMilkQuantity()).thenReturn(50);
        when(orderList.getUsedWaterQuantity()).thenReturn(200);
        final boolean result=beverageQuantityChecker.isEmpty(CoffeeType.COFFEE);
        //then
        verify(orderList,times(1)).getUsedMilkQuantity();
        verify(orderList,times(1)).getUsedWaterQuantity();
        assertEquals(result,true);
        assertEquals(beverageQuantityChecker.getShortageReason(),"WATER IS MISSING");

    }
    @Test
    public void testIsEmptyShouldReturnFalse(){
        //given
        //when
        when(orderList.getUsedMilkQuantity()).thenReturn(50);
        when(orderList.getUsedWaterQuantity()).thenReturn(150);
        final boolean result=beverageQuantityChecker.isEmpty(CoffeeType.ORANGE);
        //then
        verify(orderList,times(1)).getUsedMilkQuantity();
        verify(orderList,times(1)).getUsedWaterQuantity();
        assertEquals(result, false);

    }
}
