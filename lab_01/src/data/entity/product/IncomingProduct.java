package data.entity.product;

import data.entity.money.Money;
import org.jetbrains.annotations.NotNull;

public class IncomingProduct extends Product {

    @NotNull private Money price;

    public IncomingProduct(@NotNull IncomingProduct it) {
        super(it);
        this.price = it.getPrice();
    }

    public IncomingProduct(@NotNull String name, @NotNull Money price, @NotNull String unit) {
        super(name, unit);
        this.price = price;
    }

    public IncomingProduct(
            @NotNull String name,
            @NotNull Money price,
            @NotNull String unit,
            int quantity
    ) {
        super(name, unit, quantity);
        this.price = price.copy();
    }

    public @NotNull Money getPrice() {
        return price.copy();
    }

    protected void setPrice(@NotNull Money price) {
        this.price = price.copy();
    }

    protected final @NotNull Money editPrice() {
        return price;
    }
}
