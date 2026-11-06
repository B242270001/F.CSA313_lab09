package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    // ==========================================
    // 1. letterGrade() тестийн хэсэг
    // ==========================================
    @Test
    void letterGrade_returnsA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void letterGrade_returnsB() {
        assertEquals("B", calculator.letterGrade(85));
    }

    @Test
    void letterGrade_returnsC() {
        assertEquals("C", calculator.letterGrade(75));
    }

    @Test
    void letterGrade_returnsD() {
        assertEquals("D", calculator.letterGrade(65));
        // Лабораторийн зориудын алдааг шалгах (60 оноо F буцааж байгаа эсэх)
        assertEquals("D", calculator.letterGrade(60));
    }

    @Test
    void letterGrade_returnsF() {
        assertEquals("F", calculator.letterGrade(50));
    }

    @Test
    void letterGrade_throwsException_whenInvalidScore() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101.0));
    }

    // ==========================================
    // 2. gpa() тестийн хэсэг
    // ==========================================
    @Test
    void gpa_calculatesAverage() {
        double[] scores = { 95, 85, 75, 65 }; // A(4), B(3), C(2), D(1) -> 10 / 4 = 2.5
        assertEquals(2.5, calculator.gpa(scores), 0.001);
    }

    @Test
    void gpa_calculatesAverage_withFGrade() {
        // switch-ийн default (F) салааг шалгах
        double[] scores = { 95, 40 }; // A(4) + F(0) -> 4 / 2 = 2.0
        assertEquals(2.0, calculator.gpa(scores), 0.001);
    }

    @Test
    void gpa_throwsException_whenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[] {}));
    }

    // ==========================================
    // 3. scholarship() тестийн хэсэг
    // ==========================================
    @Test
    void scholarship_returns100() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship_returns70() {
        assertEquals(70, calculator.scholarship(3.5, 12, false));
    }

    @Test
    void scholarship_returns50() {
        assertEquals(50, calculator.scholarship(3.2, 10, false));
    }

    @Test
    void scholarship_returns70_whenGpaHighButCreditsLow() {
        // gpa >= 3.8 (true) боловч credits < 15 (false) үед дараагийн 70-ын шалгуурт
        // орох
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void scholarship_returns50_whenGpaMediumButCreditsLow() {
        // gpa >= 3.4 (true) боловч credits < 12 (false) үед дараагийн 50-ийн шалгуурт
        // орох
        assertEquals(50, calculator.scholarship(3.5, 10, false));
    }

    @Test
    void scholarship_returns0_whenViolation() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void scholarship_returns0_whenLowGpa() {
        assertEquals(0, calculator.scholarship(2.5, 15, false));
    }

    @Test
    void scholarship_throwsException_whenInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 15, false));
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 15, false));
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.5, -1, false));
    }

    @Test
    void gpa_calculatesAverage_withOneElement() {
        double[] scores = { 95 };

        assertEquals(4.0, calculator.gpa(scores), 0.001);
    }

    @Test
    void gpa_calculatesAverage_withMultipleElements() {
        double[] scores = { 95, 85, 75, 65, 40 };

        // A(4) + B(3) + C(2) + D(1) + F(0) = 10
        // 10 / 5 = 2.0
        assertEquals(2.0, calculator.gpa(scores), 0.001);
    }
}