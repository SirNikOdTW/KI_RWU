package machine_learning;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector
{
    private final List<Double> values;

    public Vector(int dim)
    {
        this(IntStream.range(0, dim)
                .mapToDouble(i -> 0d)
                .toArray());
    }

    public Vector(double... value)
    {
        this(Arrays.stream(value)
                .boxed()
                .collect(Collectors.toList()));
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
        if (this.dimension() != b.dimension()) throw new IllegalArgumentException("Dimensions must be equals.");
        return new Vector(IntStream.range(0,
                this.dimension())
                .mapToObj(i -> this.get(i) + b.get(i))
                .collect(Collectors.toList())
        );

    }

    public Vector subtract(Vector b)
    {
        if (this.dimension() != b.dimension()) throw new IllegalArgumentException("Dimensions must be equals.");
        return new Vector(IntStream.range(0,
                this.dimension())
                .mapToObj(i -> this.get(i) - b.get(i))
                .collect(Collectors.toList())
        );
    }

    public double scalar(Vector b)
    {
        if (this.dimension() != b.dimension()) throw new IllegalArgumentException("Dimensions must be equals.");
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

    public double distance(Vector b)
    {
        return Math.sqrt(IntStream.range(0,
                this.dimension())
                .mapToDouble(i -> (this.get(i) - b.get(i)) * (this.get(i) - b.get(i)))
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
