package machine_learning;

import java.util.List;

public interface MachineLearning
{
    void learn(List<Vector> positives, List<Vector> negatives);
    DataClass classify(Vector toClassify);
}
