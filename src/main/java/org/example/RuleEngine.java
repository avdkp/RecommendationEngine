package org.example;

import org.example.entities.Restaurant;
import org.example.rules.*;

import java.util.Arrays;
import java.util.List;

public class RuleEngine {

    private final List<Rule> rules;

    private PreRequisite getSmallerThanRatingFilterRule(float rating) {
        return restaurant -> restaurant.getRating() < rating;
    }

    private PreRequisite getGreaterThanOrEqualToRatingFilterRule(float rating) {
        return restaurant -> restaurant.getRating() >= rating;
    }

    private PreRequisite recommendedFilter() {
        return Restaurant::isRecommended;
    }

    private CostSelector primaryCostSelector() {
        return user -> user.getPrimaryCost().getType();
    }

    private CostSelector secondaryCostSelector() {
        return user -> user.getSecondaryCost().getType();
    }

    private CuisineSelector primaryCuisineSelector() {
        return user -> user.getPrimaryCuisine().getType();
    }

    private CuisineSelector secondaryCuisineSelector() {
        return user -> user.getSecondaryCuisine().getType();
    }

    private Rule primaryCuisineAndPrimaryCostBracketRuleWithFeaturedFlag() {
        return new CostAndCuisineRule(recommendedFilter(), primaryCuisineSelector(), primaryCostSelector());
    }

    private Rule primaryCuisineAndPrimaryCostRuleWithGreaterThanOrEqualToRating(float rating) {
        return new CostAndCuisineRule(getGreaterThanOrEqualToRatingFilterRule(rating), primaryCuisineSelector(), primaryCostSelector());
    }

    private Rule primaryCuisineAndSecondaryCostRuleWithGreaterThanOrEqualToRating(float rating) {
        return new CostAndCuisineRule(getGreaterThanOrEqualToRatingFilterRule(rating), primaryCuisineSelector(), secondaryCostSelector());
    }

    private Rule secondaryCuisineAndPrimaryCostRuleWithGreaterThanOrEqualToRating(float rating) {
        return new CostAndCuisineRule(getGreaterThanOrEqualToRatingFilterRule(rating), secondaryCuisineSelector(), primaryCostSelector());
    }

    private Rule topNewlyCreatedRestaurantRule(int maxLimit) {
        return new TopNewlyCreatedRule(maxLimit);
    }

    private Rule primaryCuisineAndPrimaryCostRuleWithRatingLessThan(float rating) {
        return new CostAndCuisineRule(getSmallerThanRatingFilterRule(rating), primaryCuisineSelector(), primaryCostSelector());
    }

    private Rule primaryCuisineAndSecondaryCostRuleWithRatingLessThan(float rating) {
        return new CostAndCuisineRule(getSmallerThanRatingFilterRule(rating), primaryCuisineSelector(), secondaryCostSelector());
    }

    private Rule secondaryCuisineAndPrimaryCostRuleWithRatingLessThan(float rating) {
        return new CostAndCuisineRule(getSmallerThanRatingFilterRule(rating), secondaryCuisineSelector(), primaryCostSelector());
    }

    public RuleEngine(int topOnboardedRestaurantCount) {
        rules = Arrays.asList(
                primaryCuisineAndPrimaryCostBracketRuleWithFeaturedFlag(),
                primaryCuisineAndPrimaryCostRuleWithGreaterThanOrEqualToRating(4),
                primaryCuisineAndSecondaryCostRuleWithGreaterThanOrEqualToRating(4.5F),
                secondaryCuisineAndPrimaryCostRuleWithGreaterThanOrEqualToRating(4.5F),
                topNewlyCreatedRestaurantRule(topOnboardedRestaurantCount),
                primaryCuisineAndPrimaryCostRuleWithRatingLessThan(4),
                primaryCuisineAndSecondaryCostRuleWithRatingLessThan(4.5F),
                secondaryCuisineAndPrimaryCostRuleWithRatingLessThan(4.5F));
    }

    public List<Rule> getRules() {
        return rules;
    }
}
