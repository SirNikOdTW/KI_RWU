package machine_learning.nearest_neighbour;

import machine_learning.DataClass;
import machine_learning.MachineLearning;
import machine_learning.Vector;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KNearestNeighbour implements MachineLearning
{
    private List<Vector> positives;
    private List<Vector> negatives;

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

    public void learn(List<Vector> positives, List<Vector> negatives)
    {
        this.positives = positives;
        this.negatives = negatives;
    }

    public DataClass classify(Vector toClassify)
    {
        var nearestNeighbours = this.nearestNeighbours(
                Stream.concat(this.positives.stream(), this.negatives.stream())
                        .collect(Collectors.toList()),
                toClassify
        );

        var positivesWithNearestNeighboursAmount = nearestNeighbours.stream()
                .filter(this.positives::contains)
                .count();

        var negativesWithNearestNeighboursAmount = nearestNeighbours.stream()
                .filter(this.negatives::contains)
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
        return vectors.parallelStream()
                .map(v -> Map.entry(this.distance.distance(v, vector), v))
                .sorted((e1, e2) -> e1.getKey() >= e2.getKey() ? (e1.getKey().equals(e2.getKey()) ? 0 : 1) : -1)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList())
                .subList(0, this.k);
    }
}

