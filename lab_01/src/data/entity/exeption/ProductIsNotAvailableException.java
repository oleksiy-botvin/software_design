package data.entity.exeption;

import data.entity.product.Product;
import org.jetbrains.annotations.NotNull;

public class ProductIsNotAvailableException extends Exception {

    private final @NotNull Product product;

    public ProductIsNotAvailableException(@NotNull Product product) {
        super("Товар «" + product.getName() + "» відсутній.");
        this.product = new Product(product);
    }

    public @NotNull Product getProduct() {
        return product;
    }
}
