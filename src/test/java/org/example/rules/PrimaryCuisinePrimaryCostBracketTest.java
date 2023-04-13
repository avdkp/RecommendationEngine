package org.example.rules;

import org.example.entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryCuisinePrimaryCostBracketTest {
    SortingRule rule = new PrimaryCuisinePrimaryCostBracket(new FilterRule() {
        @Override
        public boolean check(Restaurant restaurant) {
            return restaurant.getRating()>4;
        }
    });

    @Test
    void testRunWhenBothArePrimaryCostAndPrimaryCuisineWithDifferentRating() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());
        restaurant1.setRating(4.5F);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        restaurant2.setRating(4.1F);
        assertEquals(-40,rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenOneRestaurantHasRequiredRating() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());
        restaurant1.setRating(3.5F);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        restaurant2.setRating(4.1F);
        assertEquals(1,rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunWhenOneRestaurantCuisineDoesNotMatchPrimaryCuisine() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(Cuisine.Chinese);
        restaurant1.setCostBracket(user.getPrimaryCost().getType());
        restaurant1.setRating(4.5F);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(Cuisine.Chinese);
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        restaurant2.setRating(4.1F);
        assertEquals(0,rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunRatingOfFirstRestaurantIsLesserThan4() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());
        restaurant1.setRating(3.3F);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        restaurant2.setRating(4.1F);
        assertEquals(1,rule.run(user, restaurant1, restaurant2));
    }

    @Test
    void testRunRatingOfBothRestaurantsIsLesserThan4() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCuisine(user.getPrimaryCuisine().getType());
        restaurant1.setCostBracket(user.getPrimaryCost().getType());
        restaurant1.setRating(3.3F);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        restaurant2.setRating(2.1F);
        assertEquals(0,rule.run(user, restaurant1, restaurant2));
    }
}