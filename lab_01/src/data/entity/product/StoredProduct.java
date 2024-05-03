package data.entity.product;

import data.entity.money.Money;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class StoredProduct extends ProductInfo {

    public StoredProduct(@NotNull ProductInfo it) {
        super(it);
    }

    public StoredProduct(
            @NotNull String name,
            @NotNull Money price,
            @NotNull String unit,
            @NotNull Calendar dateLastDelivery
    ) {
        super(name, price, unit, dateLastDelivery);
    }

    public StoredProduct(
            @NotNull String name,
            @NotNull Money price,
            @NotNull String unit,
            int quantity,
            @NotNull Calendar dateLastDelivery
    ) {
        super(name, price, unit, quantity, dateLastDelivery);
    }

    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public void setDateLastDelivery(@NotNull Calendar dateLastDelivery) {
        super.setDateLastDelivery(dateLastDelivery);
    }

    public void setPrice(@NotNull Money price) {
        super.setPrice(price);
    }

    public void reducePrice(float amount) {
        float it = getPrice().getValue() - amount;
        editPrice().setValue(it > 0 ? it: 0);
    }

    public void increasePrice(float amount) {
        float it = getPrice().getValue() + amount;
        editPrice().setValue(it > 0 ? it: 0);
    }
}
