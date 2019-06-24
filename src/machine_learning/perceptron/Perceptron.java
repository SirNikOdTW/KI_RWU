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
                    weight = weight.add(x);
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
        while (!elementsAreCorrectClassified(positives, negatives, weight));

        System.out.println("----------------------------------------------");
        System.out.println("-- All datapoints are classified correctly. --");
        System.out.println("----------------------------------------------");
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

    private boolean elementsAreCorrectClassified(List<Vector> positives, List<Vector> negatives, Vector weight)
    {
        for (var x : positives)
        {
            if (weight.scalar(x) <= 0) return false;
        }

        for (var x : negatives)
        {
            if (weight.scalar(x) > 0) return false;
        }

        return true;
    }
}
