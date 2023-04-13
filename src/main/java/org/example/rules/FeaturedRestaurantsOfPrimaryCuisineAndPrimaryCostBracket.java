package org.example.rules;

import org.example.entities.Restaurant;
import org.example.entities.User;

public class FeaturedRestaurantsOfPrimaryCuisineAndPrimaryCostBracket implements Rule {
    @Override
    public int run(User user, Restaurant restaurant1, Restaurant restaurant2) {
        if (restaurant1.isRecommended() == restaurant2.isRecommended() &&
                restaurant1.getCuisine() == restaurant2.getCuisine() &&
                restaurant1.getCostBracket() == restaurant2.getCostBracket()) {
            return 0;
        } else if (restaurant1.isRecommended() &&
                restaurant1.getCostBracket() == user.getPrimaryCost().getType() &&
                restaurant1.getCuisine() == user.getPrimaryCuisine().getType()) {
            return -1;
        } else if (restaurant2.isRecommended() &&
                restaurant2.getCostBracket() == user.getPrimaryCost().getType() &&
                restaurant2.getCuisine() == user.getPrimaryCuisine().getType()) {
            return 1;
        } else if (restaurant1.isRecommended() &&
                restaurant1.getCostBracket() == user.getSecondaryCost().getType() &&
                restaurant1.getCuisine() == user.getPrimaryCuisine().getType()) {
            return -1;
        } else if (restaurant2.isRecommended() &&
                restaurant2.getCostBracket() == user.getSecondaryCost().getType() &&
                restaurant2.getCuisine() == user.getPrimaryCuisine().getType()) {
            return 1;
        } else if (restaurant1.isRecommended() &&
                restaurant1.getCostBracket() == user.getPrimaryCost().getType() &&
                restaurant1.getCuisine() == user.getSecondaryCuisine().getType()) {
            return -1;
        } else if (restaurant2.isRecommended() &&
                restaurant2.getCostBracket() == user.getPrimaryCost().getType() &&
                restaurant2.getCuisine() == user.getSecondaryCuisine().getType()) {
            return 1;
        }
        return 0;
    }
}
