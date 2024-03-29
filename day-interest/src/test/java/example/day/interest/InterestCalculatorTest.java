/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package example.day.interest;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.data.Offset.strictOffset;
import static org.junit.jupiter.api.Assertions.*;

class InterestCalculatorTest {
    private static final Offset<Double> EPSILON = strictOffset(0.00000000000001);

    private InterestCalculator interestCalculator;

    @BeforeEach
    void setUp() {
        interestCalculator = new InterestCalculator();
    }

    @Test
    void dayInterestForCasualAmount() {
        assertThat(interestCalculator.dayInterest(10000.0)).isEqualTo(1.36986301369863, EPSILON);
    }

    @Test
    void dayInterestForBigAmount() {
        assertThat(interestCalculator.dayInterest(250000.0)).isEqualTo(41.0958904109589, EPSILON);
    }

    @Test
    void dayInterestForNegativeAmount() {
        assertThat(interestCalculator.dayInterest(-5000.0)).isEqualTo(-1.36986301369863, EPSILON);
    }
}
