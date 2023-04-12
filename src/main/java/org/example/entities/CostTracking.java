package org.example.entities;

import java.util.Objects;

public class CostTracking {
    private int type;
    private int noOfOrders;

    public CostTracking(int type, int noOfOrders) {
        this.type = type;
        this.noOfOrders = noOfOrders;
    }

    public int getType() {
        return type;
    }

    public int getNoOfOrders() {
        return noOfOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostTracking)) return false;
        CostTracking that = (CostTracking) o;
        return type == that.type && noOfOrders == that.noOfOrders;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, noOfOrders);
    }
}
