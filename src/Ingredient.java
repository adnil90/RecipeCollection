import interfaces.IIngredient;

import java.io.Serializable;

public class Ingredient implements IIngredient, Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private float amount;
    private String measurement;
    private String name;

    public Ingredient(int id, float amount, String measurement, String name) {
        this.id = id;
        this.amount = amount;
        this.measurement = measurement;
        this.name = name;
    }

    public Ingredient(float amount, String measurement, String name) {
        this(-1, amount, measurement, name);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public float getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String getMeasurement() {
        return this.measurement;
    }

    @Override
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (this.amount == Math.floor(this.amount)) {
            return String.format("%.0f %s %s", this.amount, this.measurement, this.name);
        } else {
            return String.format("%.1f %s %s", this.amount, this.measurement, this.name);
        }
    }
}
