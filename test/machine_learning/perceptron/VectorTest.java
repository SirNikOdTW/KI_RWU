package machine_learning.perceptron;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest
{

    @Test
    void shouldInitializeZeroVector()
    {
        var v = new Vector(3);

        var expected = new Vector(List.of(0d, 0d, 0d));

        assertEquals(3, v.dimension());
        assertEquals(expected, v);
    }

    @Test
    void shouldReturnCorrectVectorWhenAdding()
    {
        var v1 = new Vector(List.of(1d, 2d));
        var v2 = new Vector(List.of(3d, 4d));

        var result = v1.add(v2);
        var expected = new Vector(List.of(4d, 6d));

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnCorrectVectorWhenSubtracting()
    {
        var v1 = new Vector(List.of(1d, 2d));
        var v2 = new Vector(List.of(3d, 4d));

        var result = v1.subtract(v2);
        var expected = new Vector(List.of(-2d, -2d));

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnCorrectVectorWhenScalarMultiplying()
    {
        var v1 = new Vector(List.of(1d, 2d));
        var v2 = new Vector(List.of(3d, 4d));

        var result = v1.scalar(v2);
        var expected = 11d;

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnCorrectVectorWhenEuclid()
    {
        var v1 = new Vector(List.of(1d, 2d));

        var result = v1.euclid();
        var expected = Math.sqrt(5);

        assertEquals(expected, result);
    }

    @Test
    void shouldReturnCorrectVectorWhenDividing()
    {
        var v1 = new Vector(List.of(1d, 2d));
        var div = 2d;

        var result = v1.divide(div);
        var expected = new Vector(List.of(0.5d, 1d));

        assertEquals(expected, result);
    }
}