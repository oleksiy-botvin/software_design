package data.entity.events;

import data.entity.product.IncomingProduct;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.List;

public class ReceiptOfGoods extends Event {

    @NotNull private final List<IncomingProduct> products;

    public ReceiptOfGoods(@NotNull List<IncomingProduct> products) {
        super();
        this.products = products;
    }

    public ReceiptOfGoods(@NotNull Calendar date, @NotNull List<IncomingProduct> products) {
        super(date);
        this.products = products;
    }

    public @NotNull List<IncomingProduct> getProducts() {
        return products;
    }
}
