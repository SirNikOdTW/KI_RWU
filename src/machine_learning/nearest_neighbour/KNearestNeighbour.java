package machine_learning.nearest_neighbour;

import machine_learning.Vector;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KNearestNeighbour
{
    private Distance distance;

    private int k;

    public KNearestNeighbour(Distance distance)
    {
        this(distance, 1);
    }

    public KNearestNeighbour(Distance distance, int k)
    {
        this.distance = distance;
        this.k = k;
    }

    public DataClass kNearestNeighbour(List<Vector> positives, List<Vector> negatives, Vector toClassify)
    {
        var nearestNeighbours = this.nearestNeighbours(
                Stream.concat(positives.stream(), negatives.stream())
                        .collect(Collectors.toList()),
                toClassify
        );

        var positivesWithNearestNeighboursAmount = nearestNeighbours.stream()
                .filter(positives::contains)
                .count();

        var negativesWithNearestNeighboursAmount = nearestNeighbours.stream()
                .filter(negatives::contains)
                .count();

        if (positivesWithNearestNeighboursAmount > negativesWithNearestNeighboursAmount)
        {
            return DataClass.POSITIVE;
        }
        else if (positivesWithNearestNeighboursAmount < negativesWithNearestNeighboursAmount)
        {
            return DataClass.NEGATIVE;
        }

        return new Random().nextBoolean() ? DataClass.POSITIVE : DataClass.NEGATIVE;
    }

    private List<Vector> nearestNeighbours(List<Vector> vectors, Vector vector)
    {
        var nearestNeighbours = vectors.stream()
                .map(v -> Map.entry(this.distance.distance(v, vector), v))
                .sorted((e1, e2) -> e1.getKey() >= e2.getKey() ? (e1.getKey().equals(e2.getKey()) ? 0 : 1) : -1)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return nearestNeighbours.subList(0, this.k);
    }
}

