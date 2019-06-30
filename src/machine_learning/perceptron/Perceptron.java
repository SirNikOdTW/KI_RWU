package machine_learning.perceptron;

import machine_learning.MachineLearning;
import machine_learning.Vector;
import machine_learning.DataClass;

import java.util.List;

public class Perceptron implements MachineLearning
{
    private Vector weight;

    public void learn(List<Vector> positives, List<Vector> negatives)
    {
        var iterationCounter = 0;
        this.weight = this.getInitializationVector(positives, negatives);

        do
        {
            for (var x : positives)
            {
                if (weight.scalar(x) <= 0)
                {
                    weight = weight.add(x);
                    System.out.println(weight);
                }
            }

            for (var x : negatives)
            {
                if (weight.scalar(x) > 0)
                {
                    weight = weight.subtract(x);
                    System.out.println(weight);
                }
            }

            iterationCounter++;
        }
        while (!elementsAreCorrectClassified(positives, negatives, weight));

        System.out.println("-----------------------------------------------------------------");
        System.out.println("-- All datapoints are classified correctly in " + iterationCounter + " iterations. --");
        System.out.println("-----------------------------------------------------------------");
    }

    public DataClass classify(Vector vector)
    {
        return this.weight.scalar(vector) > 0 ? DataClass.POSITIVE : DataClass.NEGATIVE;
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
