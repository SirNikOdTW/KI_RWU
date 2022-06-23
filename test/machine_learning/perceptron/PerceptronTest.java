package machine_learning.perceptron;

import machine_learning.DataClass;
import machine_learning.Vector;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PerceptronTest
{
    List<Vector> positives;
    List<Vector> negatives;
    Perceptron perceptron;

    @BeforeAll
    void initLearnData()
    {
        double biasUnit = 1d;
        this.positives = new ArrayList<>(List.of(
                new Vector(8d, 4d, biasUnit),
                new Vector(8d, 6d, biasUnit),
                new Vector(9d, 2d, biasUnit),
                new Vector(9d, 5d, biasUnit))
        );

        this.negatives = new ArrayList<>(List.of(
                new Vector(6d, 1d, biasUnit),
                new Vector(7d, 3d, biasUnit),
                new Vector(8d, 2d, biasUnit),
                new Vector(9d, 0d, biasUnit))
        );

        this.perceptron = new Perceptron();
        this.perceptron.learn(this.positives, this.negatives);
    }

    @Test
    void shouldClassifyVectorCorrectAsNegative()
    {
        var vector = new Vector(0d, 0d, 1d);

        var actualClass = this.perceptron.classify(vector);
        var expectedClass = DataClass.NEGATIVE;

        Assertions.assertEquals(expectedClass, actualClass);
    }

    @Test
    void shouldClassifyVectorCorrectAsPositive()
    {
        var vector = new Vector(9d, 3d, 1d);

        var actualClass = this.perceptron.classify(vector);
        var expectedClass = DataClass.POSITIVE;

        Assertions.assertEquals(expectedClass, actualClass);
    }
}