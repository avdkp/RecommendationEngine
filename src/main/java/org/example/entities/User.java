package org.example.entities;

import java.util.Arrays;
import java.util.Comparator;

public class User {
    private final CuisineTracking[] cuisines;
    private final CostTracking[] costBracket;

    public User(CuisineTracking[] cuisines, CostTracking[] costBracket) {
        if (cuisines != null)
            Arrays.sort(cuisines, (o1, o2) -> o2.getNoOfOrders() - o1.getNoOfOrders());
        this.cuisines = cuisines;
        if (costBracket != null)
            Arrays.sort(costBracket, (o1, o2) -> o2.getNoOfOrders() - o1.getNoOfOrders());
        this.costBracket = costBracket;
    }

    public CuisineTracking[] getCuisines() {
        return cuisines;
    }

    public CostTracking[] getCostBracket() {
        return costBracket;
    }

    public CostTracking getPrimaryCost() {
        if (costBracket == null || costBracket.length == 0) {
            return null;
        }
        return costBracket[0];
    }

    public CuisineTracking getPrimaryCuisine() {
        if (cuisines == null || cuisines.length == 0) {
            return null;
        }
        return cuisines[0];
    }

    public CostTracking getSecondaryCost() {
        if (costBracket == null || costBracket.length <= 1) {
            return null;
        }
        return costBracket[1];
    }

    public CuisineTracking getSecondaryCuisine() {
        if (cuisines == null || cuisines.length <= 1) {
            return null;
        }
        return cuisines[1];
    }
}
