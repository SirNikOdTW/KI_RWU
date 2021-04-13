package machine_learning.nearest_neighbour;

import machine_learning.DataClass;
import machine_learning.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CrossValidation
{
    private final int paramMin;
    private final int paramMax;

    public CrossValidation(int paramMin, int paramMax)
    {
        this.paramMin = paramMin;
        this.paramMax = paramMax;
    }

    public KNearestNeighbour validate(List<Vector> data, int chunkSize)
    {
        Collections.shuffle(data);
        var counter = new AtomicInteger(0);
        var chunks = data.stream()
                .collect(Collectors.groupingBy(v -> counter.getAndIncrement() / (data.size() / chunkSize)))
                .values();

        var averageFailRates = new HashMap<Double, Integer>();

        IntStream.range(paramMin, paramMax).forEach(i -> {

            var failRate = new AtomicReference<>(0d);

            chunks.forEach(chunk -> {
                var dataWithoutChunk = data.parallelStream()
                        .filter(v -> !chunk.contains(v))
                        .collect(Collectors.toList());

                var mapOfClasses = splitIntoClasses(dataWithoutChunk);
                var negatives = mapOfClasses.get(DataClass.NEGATIVE);
                var positives = mapOfClasses.get(DataClass.POSITIVE);

                var kNearestNeighbour = new KNearestNeighbour(Vector::distance, i);
                kNearestNeighbour.learn(positives, negatives);

                var failCount = 0;

                for (var vector : chunk)
                {
                    var expectedClass = DataClass.valueOf(Double.valueOf(vector.get(vector.dimension() - 1)).intValue());

                    var testVector = vector.decreasedDimension().normalized();
                    var actualClass = kNearestNeighbour.classify(testVector);

                    if (expectedClass != actualClass)
                    {
                        failCount++;
                    }
                }

                failRate.set(failRate.get() + failCount * 1d / chunk.size());
            });

            averageFailRates.put(failRate.get() / chunkSize, i);
        });

        var optimalParam = averageFailRates.get(averageFailRates.keySet().stream().min(Double::compareTo).get());
        var finalKNearestNeighbour = new KNearestNeighbour(Vector::distance, optimalParam);

        System.out.println("Optimaler Parameter k = " + optimalParam + " mit Fehlerrate " + averageFailRates.keySet().stream().min(Double::compareTo).get()*100 + " %");

        var classes = splitIntoClasses(data);
        var negatives = classes.get(DataClass.NEGATIVE);
        var positives = classes.get(DataClass.POSITIVE);
        finalKNearestNeighbour.learn(positives, negatives);

        return finalKNearestNeighbour;
    }

    private Map<DataClass, List<Vector>> splitIntoClasses(List<Vector> data)
    {
        var positives = data.parallelStream()
                .filter(v -> v.get(v.dimension()-1) == 1)
                .collect(Collectors.toList());

        var negatives = data.parallelStream()
                .filter(v -> v.get(v.dimension()-1) == 0)
                .collect(Collectors.toList());

        positives = positives.parallelStream()
                .map(Vector::decreasedDimension)
                .map(Vector::normalized)
                .collect(Collectors.toList());

        negatives = negatives.parallelStream()
                .map(Vector::decreasedDimension)
                .map(Vector::normalized)
                .collect(Collectors.toList());

        return Map.ofEntries(Map.entry(DataClass.NEGATIVE, negatives), Map.entry(DataClass.POSITIVE, positives));
    }
}
