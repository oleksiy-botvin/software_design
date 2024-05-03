package data.entity.product;

import data.entity.money.Money;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class ProductInfo extends IncomingProduct {

    @NotNull private Calendar dateLastDelivery;

    public ProductInfo(@NotNull ProductInfo it) {
        super(it);
        dateLastDelivery = it.getDateLastDelivery();
    }

    public ProductInfo(
            @NotNull String name,
            @NotNull Money price,
            @NotNull String unit,
            @NotNull Calendar dateLastDelivery
    ) {
        super(name, price, unit);
        this.dateLastDelivery = dateLastDelivery;
    }

    public ProductInfo(
            @NotNull String name,
            @NotNull Money price,
            @NotNull String unit,
            int quantity,
            @NotNull Calendar dateLastDelivery
    ) {
        super(name, price, unit, quantity);
        this.dateLastDelivery = dateLastDelivery;
    }

    public @NotNull Calendar getDateLastDelivery() {
        return dateLastDelivery;
    }

    protected void setDateLastDelivery(@NotNull Calendar dateLastDelivery) {
        this.dateLastDelivery = dateLastDelivery;
    }
}
