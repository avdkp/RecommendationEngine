package org.example.rules;

import org.example.entities.Restaurant;
import org.example.entities.User;

public interface SortingRule {
    int run(User user, Restaurant restaurant1, Restaurant restaurant2);
}
