package org.example.rules;

import org.example.entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeaturedRestaurantsOfPrimaryCuisineAndPrimaryCostBracketTest {

    private FeaturedRestaurantsOfPrimaryCuisineAndPrimaryCostBracket rule = new FeaturedRestaurantsOfPrimaryCuisineAndPrimaryCostBracket();

    @Test
    void testRunWhenBothRestaurantHaveSameCuisineAndCostItShouldReturn0() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRecommended(true);
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRecommended(true);
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());

        assertEquals(0, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenFirstRestaurantIsRecommendedAndPrimaryCostAndCuisineMatches() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRecommended(true);
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(-1, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenSecondRestaurantIsRecommendedAndPrimaryCostAndCuisineMatches() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRecommended(true);
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(1, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenFirstRestaurantIsRecommendedAndSecondaryCostAndPrimaryCuisineMatches() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRecommended(true);
        restaurant1.setCuisine(user.getSecondaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(-1, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenSecondRestaurantIsRecommendedAndSecondaryCostAndPrimaryCuisineMatches() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRecommended(true);
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(1, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenFirstRestaurantIsRecommendedAndPrimaryCostAndSecondaryCuisineMatches() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRecommended(true);
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getSecondaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(-1, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenSecondRestaurantIsRecommendedAndPrimaryCostAndSecondaryCuisineMatches() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getSecondaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRecommended(true);
        restaurant2.setCuisine(user.getSecondaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(1, rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenNoRestaurantIsRecommendedItShouldReturn0() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getSecondaryCost().getType());

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getSecondaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(0, rule.run(user, restaurant1, restaurant2));
    }
}