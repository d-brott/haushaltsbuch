package com.brott.haushaltsbuch;

import com.brott.haushaltsbuch.utilities.Utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class HelperMethodsTest {

    @Test
    public void formatAmountTest() {
        assertEquals("10,00 €", Utilities.formatAmount(10));
    }
}
