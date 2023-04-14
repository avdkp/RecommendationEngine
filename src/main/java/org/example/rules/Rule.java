package org.example.rules;

import org.example.entities.Restaurant;
import org.example.entities.User;

import java.util.List;

public interface Rule {
    int comparator(User user, Restaurant restaurant1, Restaurant restaurant2);
    List<Restaurant> filter(User user, List<Restaurant> restaurants);
}
