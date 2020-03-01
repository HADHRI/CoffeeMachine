package service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.impl.EmailNotifierImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmailNotifierImplTest {

    @Mock
    BeverageQuantityChecker beverageQuantityChecker;

    private EmailNotifier emailNotifier;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        emailNotifier = new EmailNotifierImpl(beverageQuantityChecker);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testNotifyWhenReasonIsUnkown() {
        //given
        //when
        when(beverageQuantityChecker.getShortageReason()).thenReturn("");
        emailNotifier.notifyMissingDrink(beverageQuantityChecker);
        //then
        verify(beverageQuantityChecker, times(1)).getShortageReason();
        assertEquals(outContent.toString(), "unable to deliver a drink but unkown reason");
    }

    @Test
    public void testNotifyWhenReasonisMissingWater() {
        //given
        //when
        when(beverageQuantityChecker.getShortageReason()).thenReturn("WATER IS MISSING , PLEASE REFILL WATER");
        emailNotifier.notifyMissingDrink(beverageQuantityChecker);
        //then
        verify(beverageQuantityChecker, times(2)).getShortageReason();
        assertEquals(outContent.toString(), "unable to deliver a drink because of WATER IS MISSING , PLEASE REFILL WATERplease Refill it");
    }

}
