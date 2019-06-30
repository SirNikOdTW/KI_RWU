package machine_learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest
{

    @Test
    void shouldInitializeZeroVector()
    {
        var v = new Vector(3);

        var expected = new Vector(0d, 0d, 0d);

        assertEquals(3, v.dimension());
        assertEquals(expected, v);
    }

    @Test
    void shouldReturnCorrectVectorWhenAdding()
    {
        var v1 = new Vector(1d, 2d);
        var v2 = new Vector(3d, 4d);

        var actual = v1.add(v2);
        var expected = new Vector(4d, 6d);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectVectorWhenSubtracting()
    {
        var v1 = new Vector(1d, 2d);
        var v2 = new Vector(3d, 4d);

        var actual = v1.subtract(v2);
        var expected = new Vector(-2d, -2d);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectVectorWhenScalarMultiplying()
    {
        var v1 = new Vector(1d, 2d);
        var v2 = new Vector(3d, 4d);

        var actual = v1.scalar(v2);
        var expected = 11d;

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectVectorWhenEuclid()
    {
        var v1 = new Vector(1d, 2d);

        var actual = v1.euclid();
        var expected = Math.sqrt(5);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectDistance()
    {
        var v1 = new Vector(1d, 2d);
        var v2 = new Vector(3d, 4d);

        var actual = v1.distance(v2);
        var expected = Math.sqrt(8);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectVectorWhenDividing()
    {
        var v1 = new Vector(1d, 2d);
        var div = 2d;

        var actual = v1.divide(div);
        var expected = new Vector(0.5d, 1d);

        assertEquals(expected, actual);
    }

    @Test
    void shouldDecreaseDimensionCorrect()
    {
        var v = new Vector(1d, 2d, 3d, 4d);
        
        var decreasedDimensionVector = v.decreasedDimension();
        
        var actual = decreasedDimensionVector.dimension();
        var expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    void shouldNormalizeCorrect()
    {
        var v = new Vector(4d, 4d, 4d, 4d);

        var actual = v.normalized();

        var expected = new Vector(0.5d, 0.5d, 0.5d, 0.5d);

        assertEquals(expected, actual);
    }
}