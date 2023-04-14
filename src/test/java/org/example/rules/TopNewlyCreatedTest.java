package org.example.rules;

import org.example.entities.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TopNewlyCreatedTest {

    Rule rule = new TopNewlyCreatedRule(4);

    @Test
    void testRunWhenOnboardedTimeIsSame() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        String date_string = "26-09-1989";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(date_string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        restaurant1.setOnboardedTime(date);
        restaurant1.setRating(2.3F);

        Restaurant restaurant2 = new Restaurant();
        String date_string2 = "26-09-1989";
        Date date2;
        try {
            date2 = formatter.parse(date_string2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        restaurant2.setRating(3.3F);
        restaurant2.setOnboardedTime(date2);
        assert (rule.comparator(user, restaurant1, restaurant2) > 0);
    }


    @Test
    void testRunWhenOnboardedTimeIsDifferent() {
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        Restaurant restaurant1 = new Restaurant();
        String date_string = "26-09-1989";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(date_string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        restaurant1.setOnboardedTime(date);
        restaurant1.setRating(2.3F);

        Restaurant restaurant2 = new Restaurant();
        String date_string2 = "26-09-1990";
        Date date2;
        try {
            date2 = formatter.parse(date_string2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        restaurant2.setRating(3.3F);
        restaurant2.setOnboardedTime(date2);
        assert (rule.comparator(user, restaurant1, restaurant2) > 0);
    }

    @Test
    void testFilterForTopOnBoarded() {
        Rule rule = new TopNewlyCreatedRule(5);
        CuisineTracking cuT1 = new CuisineTracking(Cuisine.NorthIndian, 5);
        CuisineTracking cuT2 = new CuisineTracking(Cuisine.SouthIndian, 5);
        CostTracking ct1 = new CostTracking(1, 3);
        CostTracking ct2 = new CostTracking(2, 2);
        User user = new User(new CuisineTracking[]{cuT1, cuT2}, new CostTracking[]{ct1, ct2});

        String date_string = "26-09-1989";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(date_string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setOnboardedTime(date);
        date_string = "26-09-1990";
        Date date2;
        try {
            date2 = formatter.parse(date_string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setOnboardedTime(date2);

        date_string = "26-09-1991";
        Date date3;
        try {
            date3 = formatter.parse(date_string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setOnboardedTime(date3);

        assertEquals(3, rule.filter(user, Arrays.asList(restaurant1, restaurant2, restaurant3)).size());
    }
}