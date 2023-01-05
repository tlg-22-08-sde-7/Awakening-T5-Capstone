package com.awakening.ui.framework.utils;

import java.util.Random;

public class MathHelper {
    private static final Random rand = new Random(); // Random number generator

    // Returns a random integer between 0 and the given max
    public static int randomInt(int upperBound) {
        return rand.nextInt(upperBound);
    }

    // Returns a random integer between the given min and max
    public static int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    // Returns a random double between 0 and the given max
    public static Direction randomDirection() {
        return Direction.values()[rand.nextInt(Direction.values().length)];
    }

    // enum for directions
    // used for room generation
    public enum Direction {
        NORTH(0, -1),
        SOUTH(0, 1),
        WEST(-1, 0),
        EAST(1, 0);

        public int dirX;
        public int dirY;
        public Direction opposite;

        static {
            NORTH.opposite = SOUTH;
            SOUTH.opposite = NORTH;
            WEST.opposite = EAST;
            EAST.opposite = WEST;
        }

        private Direction(int dirX, int dirY) {
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }
}
