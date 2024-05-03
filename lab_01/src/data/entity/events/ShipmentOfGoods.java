package data.entity.events;

import data.entity.product.Product;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.List;

public class ShipmentOfGoods extends Event {

    @NotNull private final List<Product> products;

    public ShipmentOfGoods(@NotNull List<Product> products) {
        super();
        this.products = products;
    }

    public ShipmentOfGoods(@NotNull Calendar date, @NotNull List<Product> products) {
        super(date);
        this.products = products;
    }

    public @NotNull List<Product> getProducts() {
        return products;
    }
}
