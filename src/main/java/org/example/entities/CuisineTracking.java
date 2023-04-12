package org.example.entities;

import java.util.Objects;

public class CuisineTracking {
    private Cuisine type;
    private int noOfOrders;

    public CuisineTracking(Cuisine type, int noOfOrders) {
        this.type = type;
        this.noOfOrders = noOfOrders;
    }

    public Cuisine getType() {
        return type;
    }

    public int getNoOfOrders() {
        return noOfOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuisineTracking)) return false;
        CuisineTracking that = (CuisineTracking) o;
        return noOfOrders == that.noOfOrders && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, noOfOrders);
    }
}
