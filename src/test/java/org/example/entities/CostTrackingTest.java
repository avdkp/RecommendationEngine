package org.example.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostTrackingTest {

    @Test
    void testWhenBothAreEqual() {
        CostTracking c1 = new CostTracking(2, 12);
        CostTracking c2 = new CostTracking(2, 12);
        assertEquals(c1,c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testWhenBothHaveDifferentCuisine() {
        CostTracking c1 = new CostTracking(3, 12);
        CostTracking c2 = new CostTracking(4, 12);
        assertNotEquals(c1,c2);
        assertNotEquals(c1.hashCode(), c2.hashCode());
    }
}