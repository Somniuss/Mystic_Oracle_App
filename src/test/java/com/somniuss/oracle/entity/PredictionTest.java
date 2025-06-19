package com.somniuss.oracle.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PredictionTest {

    @Test
    void testEqualsSameId() {
        Prediction p1 = new Prediction("Yes");
        p1.setId(1L);

        Prediction p2 = new Prediction("No");
        p2.setId(1L);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testNotEqualsDifferentId() {
        Prediction p1 = new Prediction("Yes");
        p1.setId(1L);

        Prediction p2 = new Prediction("Yes");
        p2.setId(2L);

        assertNotEquals(p1, p2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testEqualsNullId() {
        Prediction p1 = new Prediction("Maybe");
        Prediction p2 = new Prediction("Maybe");

        assertNotEquals(p1, p2);
        assertEquals(p1, p1);
    }

    @Test
    void testToString() {
        Prediction p = new Prediction("It will rain");
        p.setId(42L);

        String expected = "Prediction{id=42, text='It will rain'}";
        assertEquals(expected, p.toString());
    }
}
