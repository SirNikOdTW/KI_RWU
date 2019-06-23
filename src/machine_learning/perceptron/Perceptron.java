package machine_learning.perceptron;

import java.util.List;
import java.util.stream.IntStream;

public class Perceptron
{
    public void learn(List<Vector> positives, List<Vector> negatives)
    {
        var weight = this.getInitializationVector(positives, negatives);

        do
        {
            for (var x : positives)
            {
                if (weight.scalar(x) <= 0)
                {
                    weight = weight.add(x.divide(x.euclid()));
                }
            }

            for (var x : negatives)
            {
                if (weight.scalar(x) > 0)
                {
                    weight = weight.subtract(x);
                }
            }

            System.out.println(weight);
        }
        while (elementsAreCorrectClassified(positives, weight, 1) && elementsAreCorrectClassified(negatives, weight, 0));

        System.out.println("-----------------------------------");
        System.out.println("-- All are classified correctly. --");
        System.out.println("-----------------------------------");
    }

    private Vector getInitializationVector(List<Vector> positives, List<Vector> negatives)
    {
        var a = new Vector(positives.get(0).dimension());
        for (var x : positives)
        {
            a = a.add(x);
        }

        var b = new Vector(positives.get(0).dimension());
        for (var x : negatives)
        {
            b = b.add(x);
        }

        return a.subtract(b);
    }

    private boolean elementsAreCorrectClassified(List<Vector> vectors, Vector weight, int expectedClass)
    {
        int actualClass;

        for (var x : vectors)
        {
            actualClass = weight.scalar(x) > 0 ? 1 : 0;

            if (actualClass != expectedClass)
            {
                return false;
            }
        }

        return true;
    }

    private double scalarForThresholdPerceptron(Vector a, Vector b)
    {
        return IntStream.range(0,
                a.dimension()-1)
                .mapToDouble(i -> a.get(i) * b.get(i))
                .sum();
    }
}
