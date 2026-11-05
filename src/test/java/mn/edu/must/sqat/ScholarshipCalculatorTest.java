
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calculator =
            new ScholarshipCalculator();

    // Test 1: A grade
    @Test
    void letterGrade_returnsA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    // Test 2: GPA calculation
    @Test
    void gpa_calculatesAverage() {
        double[] scores = {95, 85};

        assertEquals(3.5, calculator.gpa(scores));
    }

    // Test 3: 100 percent scholarship
    @Test
    void scholarship_returns100() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    // Test 4: 70 percent scholarship
    @Test
    void scholarship_returns70() {
        assertEquals(70, calculator.scholarship(3.6, 12, false));
    }

    // Test 5: 50 percent scholarship
    @Test
    void scholarship_returns50() {
        assertEquals(50, calculator.scholarship(3.2, 10, false));
    }
}
