package machine_learning.nearest_neighbour;

import machine_learning.Vector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KNearestNeighbourTest
{
    List<Vector> positives;
    List<Vector> negatives;

    @BeforeAll
    void initLearnData()
    {
        this.positives = new ArrayList<>(List.of(
                new Vector(8d, 4d),
                new Vector(8d, 6d),
                new Vector(9d, 2d),
                new Vector(9d, 5d))
        );

        this.negatives = new ArrayList<>(List.of(
                new Vector(6d, 1d),
                new Vector(7d, 3d),
                new Vector(8d, 2d),
                new Vector(9d, 0d))
        );
    }

    @Test
    public void shouldReturnCorrectClassForVectorWithKEquals3()
    {
        var kNearestNeighbour = new KNearestNeighbour((a ,b) -> Math.abs(a.get(0) - b.get(0)) + Math.abs(a.get(1) - b.get(1)), 3);
        var vector = new Vector(8, 3.5);

        var actualClass = kNearestNeighbour.kNearestNeighbour(this.positives, this.negatives, vector);
        var expectedClass = DataClass.NEGATIVE;

        assertEquals(expectedClass, actualClass);
    }

    @Test
    public void shouldReturnCorrectClassForVectorWithKEquals5()
    {
        var kNearestNeighbour = new KNearestNeighbour((a ,b) -> Math.abs(a.get(0) - b.get(0)) + Math.abs(a.get(1) - b.get(1)), 5);
        var vector = new Vector(8, 3.5);

        var actualClass = kNearestNeighbour.kNearestNeighbour(this.positives, this.negatives, vector);
        var expectedClass = DataClass.POSITIVE;

        assertEquals(expectedClass, actualClass);
    }
}