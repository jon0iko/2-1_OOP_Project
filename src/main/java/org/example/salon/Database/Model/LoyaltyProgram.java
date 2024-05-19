package org.example.salon.Database.Model;

public class LoyaltyProgram {

    public static int calculatePoints(double amountSpent) {
        return (int) (amountSpent / 100);
    }
}
