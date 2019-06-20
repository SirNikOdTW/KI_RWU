package machine_learning.perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector
{
    private List<Double> values;

    public Vector(int dim)
    {
        this.values = new ArrayList<>();

        for (int i = 0; i < dim; i++)
        {
            this.values.add(0d);
        }
    }

    public Vector(List<Double> values)
    {
        this.values = values;
    }

    public int dimension()
    {
        return this.values.size();
    }

    public Vector add(Vector b)
    {
        return new Vector(IntStream.range(0,
                this.values.size())
                .mapToObj(i -> this.values.get(i) + b.values.get(i))
                .collect(Collectors.toCollection(ArrayList::new))
        );

    }

    public Vector subtract(Vector b)
    {
        return new Vector(IntStream.range(0,
                this.values.size())
                .mapToObj(i -> this.values.get(i) - b.values.get(i))
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    public double scalar(Vector b)
    {
        return IntStream.range(0,
                this.values.size())
                .mapToDouble(i -> this.values.get(i) * b.values.get(i))
                .sum();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Vector vector = (Vector) o;

        return Objects.equals(values, vector.values);
    }

    @Override
    public int hashCode()
    {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return values.toString()
                .replace("[", "(")
                .replace("]", ")");
    }
}
