package machine_learning;

public enum DataClass
{
    NEGATIVE,
    POSITIVE;

    public static DataClass valueOf(int i)
    {
        return i == 0 ? NEGATIVE : POSITIVE;
    }
}
