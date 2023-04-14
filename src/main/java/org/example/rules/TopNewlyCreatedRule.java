package org.example.rules;

import org.example.entities.Restaurant;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TopNewlyCreatedRule implements Rule {
    private final int maxLimit;

    public TopNewlyCreatedRule(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public int comparator(User user, Restaurant restaurant1, Restaurant restaurant2) {
        if (restaurant1.getOnboardedTime().getTime() == restaurant2.getOnboardedTime().getTime()) {
            if (restaurant1.getRating() == restaurant2.getRating())
                return 0;
            return restaurant1.getRating() > restaurant2.getRating() ? -1 : 1;
        }
        return restaurant2.getOnboardedTime().getTime() > restaurant1.getOnboardedTime().getTime() ? 1 : -1;
    }

    @Override
    public List<Restaurant> filter(User user, List<Restaurant> restaurants) {
        return restaurants.stream().limit(maxLimit).collect(Collectors.toList());
    }
}
