package org.example.rules;

import org.example.entities.Restaurant;

public interface PreRequisite {
    boolean check(Restaurant restaurant);
}
