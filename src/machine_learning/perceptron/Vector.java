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
                this.dimension())
                .mapToObj(i -> this.get(i) + b.get(i))
                .collect(Collectors.toList())
        );

    }

    public Vector subtract(Vector b)
    {
        return new Vector(IntStream.range(0,
                this.dimension())
                .mapToObj(i -> this.get(i) - b.get(i))
                .collect(Collectors.toList())
        );
    }

    public double scalar(Vector b)
    {
        return IntStream.range(0,
                this.dimension())
                .mapToDouble(i -> this.get(i) * b.get(i))
                .sum();
    }

    public double euclid()
    {
        return Math.sqrt(IntStream.range(0,
                this.dimension())
                .mapToDouble(i -> this.get(i) * this.get(i))
                .sum());
    }

    public Vector divide(double div)
    {
        var divided = new ArrayList<Double>();

        for (int i = 0; i < this.dimension(); i++)
        {
            divided.add(this.values.get(i) / div);
        }

        return new Vector(divided);
    }

    public double get(int index)
    {
        return this.values.get(index);
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
        return this.values.toString();
    }
}
