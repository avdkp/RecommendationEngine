package org.example;

import org.example.entities.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecommendationEngineTest {

    private Date getDate(String date_string) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(date_string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    private Restaurant[] getRestaurants1() {
        Restaurant[] restaurants = new Restaurant[3];
        restaurants[0] = new Restaurant();
        restaurants[1] = new Restaurant();
        restaurants[2] = new Restaurant();

        restaurants[0].setRestaurantId("0");
        restaurants[1].setRestaurantId("1");
        restaurants[2].setRestaurantId("2");

        restaurants[0].setRecommended(true);
        restaurants[1].setRecommended(false);
        restaurants[2].setRecommended(true);

        restaurants[0].setCuisine(Cuisine.NorthIndian);
        restaurants[1].setCuisine(Cuisine.SouthIndian);
        restaurants[2].setCuisine(Cuisine.SouthIndian);

        restaurants[0].setCostBracket(1);
        restaurants[1].setCostBracket(2);
        restaurants[2].setCostBracket(2);

        restaurants[0].setOnboardedTime(getDate("11-11-2013"));
        restaurants[1].setOnboardedTime(getDate("11-11-2014"));
        restaurants[2].setOnboardedTime(getDate("11-11-2015"));

        return restaurants;
    }

    private Restaurant[] getRestaurants2() {
        Restaurant[] restaurants = new Restaurant[5];
        restaurants[0] = new Restaurant();
        restaurants[1] = new Restaurant();
        restaurants[2] = new Restaurant();
        restaurants[3] = new Restaurant();
        restaurants[4] = new Restaurant();

        restaurants[0].setRestaurantId("0");
        restaurants[1].setRestaurantId("1");
        restaurants[2].setRestaurantId("2");
        restaurants[3].setRestaurantId("3");
        restaurants[4].setRestaurantId("4");

        restaurants[0].setRecommended(false);
        restaurants[1].setRecommended(false);
        restaurants[2].setRecommended(true);
        restaurants[3].setRecommended(true);
        restaurants[4].setRecommended(false);

        restaurants[0].setCuisine(Cuisine.NorthIndian);
        restaurants[1].setCuisine(Cuisine.SouthIndian);
        restaurants[2].setCuisine(Cuisine.SouthIndian);
        restaurants[3].setCuisine(Cuisine.Chinese);
        restaurants[4].setCuisine(Cuisine.SouthIndian);

        restaurants[0].setCostBracket(1);
        restaurants[1].setCostBracket(2);
        restaurants[2].setCostBracket(2);
        restaurants[3].setCostBracket(4);
        restaurants[4].setCostBracket(5);

        restaurants[0].setOnboardedTime(getDate("11-11-2013"));
        restaurants[1].setOnboardedTime(getDate("11-11-2014"));
        restaurants[2].setOnboardedTime(getDate("11-11-2015"));
        restaurants[3].setOnboardedTime(getDate("11-11-2016"));
        restaurants[4].setOnboardedTime(getDate("11-11-2017"));

        restaurants[0].setRating(3.3F);
        restaurants[1].setRating(4.4F);
        restaurants[2].setRating(3.5F);
        restaurants[3].setRating(3.9F);
        restaurants[4].setRating(4.3F);

        return restaurants;
    }


    private User getUser1() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 2);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        return new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});
    }

    private User getUser2() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 2);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 20);
        return new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});
    }

    @Test
    void getRestaurantRecommendations() {
        RecommendationEngine recommendationEngine = new RecommendationEngine(5, 5);
        Restaurant[] restaurants = getRestaurants1();
        String[] result = recommendationEngine.getRestaurantRecommendations(getUser1(), restaurants);
        assertEquals(3, result.length);
        assertEquals("2", result[0]);
        assertEquals("1", result[1]);
        assertEquals("0", result[2]);
    }

    @Test
    void getRestaurantRecommendationsWhenMaxSizeIsLesserThanTotalCount() {
        RecommendationEngine recommendationEngine = new RecommendationEngine(2, 3);
        Restaurant[] restaurants = getRestaurants1();
        String[] result = recommendationEngine.getRestaurantRecommendations(getUser1(), restaurants);
        assertEquals(2, result.length);
        assertEquals("2", result[0]);
        assertEquals("1", result[1]);
    }

    @Test
    void getRestaurantRecommendationsWhenThereAreCuisinesApartFromPrimaryAndSecondary() {
        int maxSize = 10;
        RecommendationEngine recommendationEngine = new RecommendationEngine(maxSize, 5);
        Restaurant[] restaurants = getRestaurants2();
        User user = getUser2();
        String[] result = recommendationEngine.getRestaurantRecommendations(user, restaurants);
        assertEquals(Math.min(restaurants.length, 10), result.length);
        assertEquals("2", result[0]);
        assertEquals("1", result[1]);
        assertEquals("4", result[2]);
        assertEquals("3", result[3]);
        assertEquals("0", result[4]);
    }

    @Test
    void getRestaurantRecommendationsWhenThereAreLowerRatedRestaurants() {
        int maxSize = 10;
        RecommendationEngine recommendationEngine = new RecommendationEngine(maxSize, 1);
        Restaurant[] restaurants = getRestaurants2();
        User user = getUser2();
        String[] result = recommendationEngine.getRestaurantRecommendations(user, restaurants);
        assertEquals(Math.min(restaurants.length, 10), result.length);
        assertEquals("2", result[0]);
        assertEquals("1", result[1]);
        assertEquals("4", result[2]);
        assertEquals("3", result[3]);
        assertEquals("0", result[4]);
    }
}