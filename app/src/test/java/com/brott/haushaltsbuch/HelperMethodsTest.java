package com.brott.haushaltsbuch;

import com.brott.haushaltsbuch.utilities.HelperMethods;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelperMethodsTest {

    @Test
    public void formatAmountTest() {
        assertEquals("10,00 €", HelperMethods.formatAmount(10));
    }
}
