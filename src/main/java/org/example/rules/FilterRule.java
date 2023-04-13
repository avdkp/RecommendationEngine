package org.example.rules;

import org.example.entities.Restaurant;

public interface FilterRule {
    boolean check(Restaurant restaurant);
}
