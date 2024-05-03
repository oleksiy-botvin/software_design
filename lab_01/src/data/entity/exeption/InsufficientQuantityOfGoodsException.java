package data.entity.exeption;

import data.entity.product.Product;
import org.jetbrains.annotations.NotNull;

public class InsufficientQuantityOfGoodsException extends Exception {

    private final @NotNull Product product;

    public InsufficientQuantityOfGoodsException(@NotNull Product product) {
        super("Продукту «" + product.getName() + "» немає в достатній кількості");
        this.product = new Product(product);
    }

    public @NotNull Product getProduct() {
        return product;
    }
}
