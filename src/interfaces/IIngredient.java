package interfaces;

public interface IIngredient {
    int getId();

    float getAmount();
    void setAmount(float amount);

    String getMeasurement();
    void setMeasurement(String measurement);

    String getName();
    void setName(String name);

    String toString();
}
