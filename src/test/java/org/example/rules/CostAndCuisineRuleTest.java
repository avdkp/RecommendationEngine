package org.example.rules;

import org.example.entities.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CostAndCuisineRuleTest {

    @Test
    void testCompareWhenBothArePrimaryCostAndPrimaryCuisineWithDifferentRating() {
        Rule rule = new CostAndCuisineRule(restaurant -> restaurant.getRating() > 4,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());

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
        assertEquals(-40,rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenOneRestaurantHasRequiredRating() {
        Rule rule = new CostAndCuisineRule(restaurant -> restaurant.getRating() > 4,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());

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
        assertEquals(1,rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenOneRestaurantCuisineDoesNotMatchPrimaryCuisine() {
        Rule rule = new CostAndCuisineRule(restaurant -> restaurant.getRating() > 4,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());

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
        assertEquals(0,rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareRatingOfFirstRestaurantIsLesserThan4() {
        Rule rule = new CostAndCuisineRule(restaurant -> restaurant.getRating() > 4,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(1,rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareRatingOfBothRestaurantsIsLesserThan4() {
        Rule rule = new CostAndCuisineRule(restaurant -> restaurant.getRating() > 4,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(0,rule.comparator(user, restaurant1, restaurant2));
    }


    @Test
    void testCompareWhenBothRestaurantHaveSameCuisineAndCostItShouldReturn0() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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

        assertEquals(0, rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenFirstRestaurantIsRecommendedAndPrimaryCostAndCuisineMatches() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(-1, rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenSecondRestaurantIsRecommendedAndPrimaryCostAndCuisineMatches() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(1, rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenFirstRestaurantIsRecommendedAndPrimaryCostAndPrimaryCuisineMatchesWithSecond() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(0, rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenSecondRestaurantIsRecommendedAndSecondaryCostAndPrimaryCuisineMatches() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(1, rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testCompareWhenNoRestaurantIsRecommendedItShouldReturn0() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        assertEquals(0, rule.comparator(user, restaurant1, restaurant2));
    }

    @Test
    void testFilterForRecommendedCase() {
        Rule rule = new CostAndCuisineRule(Restaurant::isRecommended,
                user -> user.getPrimaryCuisine().getType(),
                user -> user.getPrimaryCost().getType());
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
        restaurant2.setRecommended(true);
        restaurant2.setCuisine(user.getPrimaryCuisine().getType());
        restaurant2.setCostBracket(user.getPrimaryCost().getType());
        assertEquals(1, rule.filter(user, Arrays.asList(restaurant1, restaurant2)).size());
    }
}