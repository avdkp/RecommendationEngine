package org.example.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuisineTrackingTest {

    @Test
    void testWhenBothAreEqual() {
        CuisineTracking c1 = new CuisineTracking(Cuisine.NorthIndian, 12);
        CuisineTracking c2 = new CuisineTracking(Cuisine.NorthIndian, 12);
        assertEquals(c1,c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testWhenBothHaveDifferentCuisine() {
        CuisineTracking c1 = new CuisineTracking(Cuisine.NorthIndian, 12);
        CuisineTracking c2 = new CuisineTracking(Cuisine.SouthIndian, 12);
        assertNotEquals(c1,c2);
        assertNotEquals(c1.hashCode(), c2.hashCode());
    }
}