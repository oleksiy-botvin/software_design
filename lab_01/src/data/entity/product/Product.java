package data.entity.product;

import org.jetbrains.annotations.NotNull;

public class Product {

    @NotNull private final String id;
    @NotNull private final String name;
    @NotNull private final String unit;
    private int quantity;

    public Product(@NotNull Product it) {
        this(it.getName(), it.getUnit(), it.getQuantity());
    }

    public Product(@NotNull String name,@NotNull String unit) {
        this(name, unit, 0);
    }

    public Product(
            @NotNull String name,
            @NotNull String unit,
            int quantity
    ) {
        id = (name + unit).replaceAll(" ", "_");
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public final @NotNull String getId() {
        return id;
    }

    public final @NotNull String getName() {
        return name;
    }

    public final @NotNull String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
