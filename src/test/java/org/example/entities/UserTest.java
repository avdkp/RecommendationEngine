package org.example.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getPrimaryCostWhenCostTrackingIsNull() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, null);
        assertNull(user.getPrimaryCost());
    }

    @Test
    void getPrimaryCostWhenCostTrackingIsValid() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 12);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});
        assertEquals(user.getPrimaryCost(), ct2);
    }

    @Test
    void getPrimaryCuisineWhenCuisineTrackingIsNull() {
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 12);
        User user = new User(null, new CostTracking[]{ct1, ct2});
        assertNull(user.getPrimaryCuisine());
    }

    @Test
    void getPrimaryCuisineWhenCuisineTrackingIsValid() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 2);
        CuisineTracking cuT3 = new CuisineTracking(Cuisine.SouthIndian, 11);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 12);
        User user = new User(new CuisineTracking[]{cuT1, cuT2, cuT3}, new CostTracking[]{ct1, ct2});
        assertEquals(user.getPrimaryCuisine(), cuT3);
    }

    @Test
    void getSecondaryCostWhenCostTrackingIsNull() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, null);
        assertNull(user.getSecondaryCost());
    }

    @Test
    void getSecondaryCostWhenCostTrackingHas1Cost() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1});
        assertNull(user.getSecondaryCost());
    }

    @Test
    void getSecondaryCostWhenCostTrackingHasMoreThan1CostItems() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(4, 13);
        CostTracking ct3 = new CostTracking(5, 34);
        CostTracking ct4 = new CostTracking(2, 11);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2, ct3, ct4});
        assertEquals(user.getSecondaryCost(), ct2);
    }

    @Test
    void getSecondaryCuisineWhenCuisineTrackingIsNull() {
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 12);
        User user = new User(null, new CostTracking[]{ct1, ct2});
        assertNull(user.getSecondaryCuisine());
    }

    @Test
    void getSecondaryCuisineWhenCuisineTrackingHas1Element() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 12);
        User user = new User(new CuisineTracking[]{cuT1}, new CostTracking[]{ct1, ct2});
        assertNull(user.getSecondaryCuisine());
    }

    @Test
    void getSecondaryCuisineWhenCuisineTrackingHasMoreThan1Element() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 2);
        CuisineTracking cuT3 = new CuisineTracking(Cuisine.SouthIndian, 11);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 12);
        User user = new User(new CuisineTracking[]{cuT1, cuT2, cuT3}, new CostTracking[]{ct1, ct2});
        assertEquals(user.getSecondaryCuisine(), cuT1);
    }
}