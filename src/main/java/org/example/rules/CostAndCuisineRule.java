package org.example.rules;

import org.example.entities.Restaurant;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CostAndCuisineRule implements Rule {

    private final FilterRule preRequisiteFilter;
    private final CuisineSelector cuisineSelector;
    private final CostSelector costSelector;

    public CostAndCuisineRule(FilterRule filterRule, CuisineSelector getCuisine, CostSelector costSelector) {
        this.preRequisiteFilter = filterRule;
        this.cuisineSelector = getCuisine;
        this.costSelector = costSelector;
    }

    @Override
    public int comparator(User user, Restaurant restaurant1, Restaurant restaurant2) {
        if (preRequisiteFilter.check(restaurant1) && preRequisiteFilter.check(restaurant2)) {
            if (matchCostAndCuisine(restaurant1, user) && matchCostAndCuisine(restaurant2, user)) {
                return (int) (100 * (restaurant2.getRating() - restaurant1.getRating()));
            } else if (matchCostAndCuisine(restaurant1, user)) {
                return -1;
            } else if (matchCostAndCuisine(restaurant2, user)) {
                return 1;
            } else {
                return 0;
            }
        } else if (preRequisiteFilter.check(restaurant1) && matchCostAndCuisine(restaurant1, user)) {
            return -1;
        } else if (preRequisiteFilter.check(restaurant2) && matchCostAndCuisine(restaurant2, user)) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Restaurant> filter(User user, List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .filter(r -> matchCostAndCuisine(r, user) && preRequisiteFilter.check(r))
                .collect(Collectors.toList());
    }

    private boolean matchCostAndCuisine(Restaurant restaurant, User user) {
        return restaurant.getCuisine() == cuisineSelector.getCuisine(user) &&
                restaurant.getCostBracket() == costSelector.getCost(user);
    }
}
