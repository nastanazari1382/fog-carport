package services;

import exceptions.CarportException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    // 300 x 480 is a normal size, so it should work
    @Test
    void validMeasurementsDoNotThrow() {
        int width = 300;
        int length = 480;

        assertDoesNotThrow(() -> CarportCalculator.validate(width, length));
    }

    // The smallest and biggest width and length should also be allowed
    @Test
    void boundaryValuesAreAllowed() {
        int minWidth = 240;
        int minLength = 240;
        int maxWidth = 600;
        int maxLength = 780;

        assertDoesNotThrow(() -> CarportCalculator.validate(minWidth, minLength));
        assertDoesNotThrow(() -> CarportCalculator.validate(maxWidth, maxLength));
    }

    // A width under 240 should give an error
    @Test
    void widthTooSmallThrows() {
        int width = 239;
        int length = 480;

        CarportException e = assertThrows(CarportException.class,
                () -> CarportCalculator.validate(width, length));

        assertEquals("Bredden skal være mellem 240 og 600 cm.", e.getMessage());
    }

    // A width over 600 should give an error
    @Test
    void widthTooBigThrows() {
        int width = 601;
        int length = 480;

        assertThrows(CarportException.class, () -> CarportCalculator.validate(width, length));
    }

    // A length under 240 should give an error
    @Test
    void lengthTooSmallThrows() {
        int width = 300;
        int length = 239;

        assertThrows(CarportException.class, () -> CarportCalculator.validate(width, length));
    }

    // A length over 780 should give an error + error message
    @Test
    void lengthTooBigThrows() {
        int width = 300;
        int length = 781;

        CarportException e = assertThrows(CarportException.class,
                () -> CarportCalculator.validate(width, length));

        assertEquals("Længden skal være mellem 240 og 780 cm.", e.getMessage());
    }

    // The width list should start at 240, end at 600 and have 13 options
    @Test
    void widthOptionsAreCorrect() {
        List<Integer> widths = CarportCalculator.getWidthOptions();

        assertEquals(240, widths.get(0));
        assertEquals(600, widths.get(widths.size() - 1));
        assertEquals(13, widths.size());
    }


    // The length list should start at 240, end at 780 and have 19 options
    @Test
    void lengthOptionsAreCorrect() {
        List<Integer> lengths = CarportCalculator.getLengthOptions();

        assertEquals(240, lengths.get(0));
        assertEquals(780, lengths.get(lengths.size() - 1));
        assertEquals(19, lengths.size());
    }
}