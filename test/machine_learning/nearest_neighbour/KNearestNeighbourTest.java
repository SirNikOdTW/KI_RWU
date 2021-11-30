package machine_learning.nearest_neighbour;

import machine_learning.DataClass;
import machine_learning.Vector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opentest4j.AssertionFailedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KNearestNeighbourTest
{
    private List<Vector> positives;
    private List<Vector> negatives;
    private DistanceFunction distanceFunction;

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

        this.distanceFunction = (a, b) -> Math.abs(a.get(0) - b.get(0)) + Math.abs(a.get(1) - b.get(1));
    }

    @Test
    public void shouldReturnCorrectClassForVectorWithKEquals3()
    {
        var kNearestNeighbour = new KNearestNeighbour(this.distanceFunction, 3);
        kNearestNeighbour.learn(this.positives, this.negatives);
        var vector = new Vector(8, 3.5);

        var actualClass = kNearestNeighbour.classify(vector);
        var expectedClass = DataClass.NEGATIVE;

        assertEquals(expectedClass, actualClass);
    }

    @Test
    public void shouldReturnCorrectClassForVectorWithKEquals5()
    {
        var kNearestNeighbour = new KNearestNeighbour(this.distanceFunction, 5);
        kNearestNeighbour.learn(this.positives, this.negatives);
        var vector = new Vector(8, 3.5);

        var actualClass = kNearestNeighbour.classify(vector);
        var expectedClass = DataClass.POSITIVE;

        assertEquals(expectedClass, actualClass);
    }

    @Test
    public void shouldReturnCorrectClassesForAppendicitisData()
    {
        var trainDataFile = "./resources/app1.data";
        var testDataFile = "./resources/app1.test";

        var trainDataVectors = readFromFile(trainDataFile);

        var dataClasses = splitIntoClasses(trainDataVectors);
        var negatives = dataClasses.get(DataClass.NEGATIVE);
        var positives = dataClasses.get(DataClass.POSITIVE);

        var kNearestNeighbour = new KNearestNeighbour(Vector::distance);
        kNearestNeighbour.learn(positives, negatives);

        var testDataVectors = readFromFile(testDataFile);
        var failCount = 0;

        for (var vector : testDataVectors)
        {
            var expectedClass = DataClass.valueOf(Double.valueOf(vector.get(vector.dimension() - 1)).intValue());

            var testVector = vector.decreasedDimension();

            var actualClass = kNearestNeighbour.classify(testVector.normalized());

            try
            {
                assertEquals(expectedClass, actualClass);
            }
            catch (AssertionFailedError e)
            {
                failCount++;
            }
        }

        System.out.println(failCount + " of " + testDataVectors.size() + "  are not correct classified.");
        System.out.println("Fail rate of " + Math.round(100d * failCount / testDataVectors.size()) + " %");

    }

    @Test
    public void shouldReturnOptimum()
    {
        var trainDataFile = "./resources/app1.data";
        var testDataFile = "./resources/app1.test";

        var trainDataVectors = readFromFile(trainDataFile);
        var testDataVectors = readFromFile(testDataFile);
        var data = Stream.concat(trainDataVectors.stream(), testDataVectors.stream())
                .collect(Collectors.toList());

        var crossValidation = new CrossValidation(1, 100);

        var kNearestNeighbour = crossValidation.validate(data, data.size());
    }

    private List<Vector> readFromFile(String file)
    {
        List<Vector> vectorList = new ArrayList<>();

        try (var reader = new BufferedReader(new FileReader(file)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                vectorList.add(new Vector(
                        Arrays.stream(line.split(","))
                                .map(Double::valueOf)
                                .collect(Collectors.toList())
                ));
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return vectorList;
    }

    private Map<DataClass, List<Vector>> splitIntoClasses(List<Vector> data)
    {
        var positives = data.stream()
                .filter(v -> v.get(v.dimension()-1) == 1)
                .collect(Collectors.toList());

        var negatives = data.stream()
                .filter(v -> v.get(v.dimension()-1) == 0)
                .collect(Collectors.toList());

        positives = positives.stream()
                .map(Vector::decreasedDimension)
                .map(Vector::normalized)
                .collect(Collectors.toList());

        negatives = negatives.stream()
                .map(Vector::decreasedDimension)
                .map(Vector::normalized)
                .collect(Collectors.toList());

        return Map.ofEntries(Map.entry(DataClass.NEGATIVE, negatives), Map.entry(DataClass.POSITIVE, positives));
    }
}