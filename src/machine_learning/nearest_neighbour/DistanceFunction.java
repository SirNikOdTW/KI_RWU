package machine_learning.nearest_neighbour;

import machine_learning.Vector;

public interface DistanceFunction
{
    double distance(Vector a, Vector b);
}
