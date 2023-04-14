package org.example.rules;

import org.example.entities.Cuisine;
import org.example.entities.User;

public interface CuisineSelector {
    Cuisine getCuisine(User user);
}
