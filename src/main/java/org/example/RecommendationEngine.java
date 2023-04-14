package org.example;

import org.example.entities.Restaurant;
import org.example.entities.User;
import org.example.rules.Rule;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationEngine {

    private List<Rule> rules;
    private int maxSize;

    public RecommendationEngine(int maxSize, int topOnboardedRestaurantCount) {
        this.maxSize = maxSize;
        RuleEngine ruleEngine = new RuleEngine(topOnboardedRestaurantCount);
        this.rules = ruleEngine.getRules();
    }

    public String[] getRestaurantRecommendations(User user, Restaurant[] restaurants) {
        List<String> result = new ArrayList<>();
        List<Restaurant> restaurantsList = Arrays.asList(restaurants);
        Set<Restaurant> addedInResult = new HashSet<>();

        for (Rule rule : rules) {
            restaurantsList.sort((o1, o2) -> rule.comparator(user, o1, o2));
            List<Restaurant> filteredRestaurant = rule.filter(user, restaurantsList);
            addedInResult.addAll(filteredRestaurant);
            List<String> filteredIds = filteredRestaurant.stream().map(Restaurant::getRestaurantId).collect(Collectors.toList());
            result.addAll(filteredIds);
            restaurantsList = restaurantsList.stream().filter(r -> !addedInResult.contains(r)).collect(Collectors.toList());
        }
        result.addAll(restaurantsList.stream().map(Restaurant::getRestaurantId).collect(Collectors.toList()));
        return result.stream().limit(this.maxSize).toArray(String[]::new);
    }
}
