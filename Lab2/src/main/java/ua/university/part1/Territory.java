package ua.university.part1;

import java.util.Random;

public class Territory {
    private final int numberOfParts;

    private final int bearPosition;

    public Territory(int numberOfParts) {
        this.numberOfParts = numberOfParts;
        this.bearPosition = new Random().nextInt(numberOfParts);
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    boolean isBearHere(int partNumber) {
        return partNumber == bearPosition;
    }
}
