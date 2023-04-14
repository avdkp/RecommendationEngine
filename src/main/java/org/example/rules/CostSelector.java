package org.example.rules;

import org.example.entities.User;

public interface CostSelector {
    int getCost(User user);
}
