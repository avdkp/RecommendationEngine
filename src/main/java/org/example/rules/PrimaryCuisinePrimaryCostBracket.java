package org.example.rules;

import org.example.entities.Restaurant;
import org.example.entities.User;

public class PrimaryCuisinePrimaryCostBracket implements SortingRule {

    private final FilterRule ratingFilter;

    public PrimaryCuisinePrimaryCostBracket(FilterRule filterRule) {
        this.ratingFilter = filterRule;
    }

    @Override
    public int run(User user, Restaurant restaurant1, Restaurant restaurant2) {
        if (ratingFilter.check(restaurant1) && ratingFilter.check(restaurant2)) {
            if (matchesWithPrimaryCostAndPrimaryCuisine(restaurant1, user) && matchesWithPrimaryCostAndPrimaryCuisine(restaurant2, user)) {
                return (int) (100 * (restaurant2.getRating() - restaurant1.getRating()));
            } else if (matchesWithPrimaryCostAndPrimaryCuisine(restaurant1, user)) {
                return -1;
            } else if (matchesWithPrimaryCostAndPrimaryCuisine(restaurant2, user)) {
                return 1;
            } else {
                return 0;
            }
        } else if (ratingFilter.check(restaurant1) && matchesWithPrimaryCostAndPrimaryCuisine(restaurant1, user)) {
            return -1;
        } else if (ratingFilter.check(restaurant2) && matchesWithPrimaryCostAndPrimaryCuisine(restaurant2, user)) {
            return 1;
        }
        return 0;
    }

    private boolean matchesWithPrimaryCostAndPrimaryCuisine(Restaurant restaurant, User user) {
//        Removing this check, since its guaranteed that these values will never be null
//        if(user.getPrimaryCost() == null || user.getPrimaryCuisine() == null) {
//            return false;
//        }
        return restaurant.getCuisine() == user.getPrimaryCuisine().getType() &&
                restaurant.getCostBracket() == user.getPrimaryCost().getType();
    }
}
