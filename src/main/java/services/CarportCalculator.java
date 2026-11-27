package services;

import exceptions.CarportException;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {
    public static final int MIN_WIDTH = 240;
    public static final int MAX_WIDTH = 600;
    public static final int MIN_LENGTH = 240;
    public static final int MAX_LENGTH = 780;
    public static final int STEP = 30;

    // Checks that width and length are within Fog's limits.
    // Throws CarportException with a Danish message if a measurement is not valid.
    public static void validate(int width, int length) throws CarportException {
        if (width < MIN_WIDTH || width > MAX_WIDTH) {
            throw new CarportException("Bredden skal være mellem " + MIN_WIDTH + " og " + MAX_WIDTH + " cm.");
        }
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new CarportException("Længden skal være mellem " + MIN_LENGTH + " og " + MAX_LENGTH + " cm.");
        }
    }

    // Returns all widths the customer can choose, from 240 to 600 in steps of 30 cm
    public static List<Integer> getWidthOptions() {
        List<Integer> widths = new ArrayList<>();
        for (int width = MIN_WIDTH; width <= MAX_WIDTH; width += STEP) {
            widths.add(width);
        }
        return widths;
    }

    // Returns all lengths the customer can choose, from 240 to 780 in steps of 30 cm
    public static List<Integer> getLengthOptions() {
        List<Integer> lengths = new ArrayList<>();
        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += STEP) {
            lengths.add(length);
        }
        return lengths;
    }
}