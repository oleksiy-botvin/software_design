package data.warehouse;

import data.entity.Reporting;
import data.entity.events.Event;
import data.entity.events.ReceiptOfGoods;
import data.entity.events.ShipmentOfGoods;
import data.entity.events.Stocktaking;
import data.entity.exeption.InsufficientQuantityOfGoodsException;
import data.entity.exeption.ProductIsNotAvailableException;
import data.entity.money.Money;
import data.entity.product.Product;
import data.entity.product.ProductInfo;
import data.entity.product.StoredProduct;
import org.jetbrains.annotations.NotNull;
import util.function.NotNullConsumer;

import java.util.*;

public class WarehouseImpl implements Warehouse {

    @NotNull private final Map<@NotNull String, @NotNull StoredProduct> products = new HashMap<>();

    public void event(
            @NotNull Event event,
            @NotNull NotNullConsumer<Reporting> onSuccess,
            @NotNull NotNullConsumer<Throwable> onError
    ) {
        try {
            onSuccess.accept(new Reporting(event, perform(event)));
        } catch (ProductIsNotAvailableException | InsufficientQuantityOfGoodsException t) {
            onError.accept(t);
        }
    }

    private @NotNull List<ProductInfo> perform(
            @NotNull Event event
    ) throws ProductIsNotAvailableException, InsufficientQuantityOfGoodsException {
        if (event instanceof ReceiptOfGoods) {
            return receiptOfGoods((ReceiptOfGoods) event);
        } else if (event instanceof ShipmentOfGoods) {
            return shipmentOfGoods((ShipmentOfGoods) event);
        } else if (event instanceof Stocktaking) {
            return products.values().stream().map(ProductInfo::new).toList();
        } else {
            throw new IllegalArgumentException("Not registered event: " + event.getClass().getName());
        }
    }

    private List<ProductInfo> receiptOfGoods(@NotNull ReceiptOfGoods event) {
        return event.getProducts().stream().map(it -> {
            StoredProduct item = products.get(it.getId());
            if (item == null) {
                item = new StoredProduct(it.getName(), it.getPrice(), it.getUnit(), it.getQuantity(), event.getDate());
                products.put(item.getId(), item);
            } else {
                item.setQuantity(item.getQuantity() + it.getQuantity());
                Money price = it.getPrice();
                if (!item.getPrice().equals(price)) item.setPrice(price);
            }
            return new ProductInfo(it.getName(), it.getPrice(), it.getUnit(), it.getQuantity(), event.getDate());
        }).toList();
    }

    private List<ProductInfo> shipmentOfGoods(
            @NotNull ShipmentOfGoods event
    ) throws ProductIsNotAvailableException, InsufficientQuantityOfGoodsException {
        Map<@NotNull String, @NotNull StoredProduct> map = new HashMap<>();
        for (Product it : event.getProducts()) {
            StoredProduct product = products.get(it.getId());
            if (product == null) throw new ProductIsNotAvailableException(it);
            else if (product.getQuantity() < it.getQuantity()) throw new InsufficientQuantityOfGoodsException(product);

            StoredProduct info = map.get(it.getId());
            if (info == null) {
                info = new StoredProduct(
                        product.getName(),
                        product.getPrice(),
                        product.getUnit(),
                        it.getQuantity(),
                        event.getDate()
                );
                map.put(info.getId(), info);
            } else {
                info.setQuantity(info.getQuantity() + it.getQuantity());
            }
            product.setQuantity(product.getQuantity() - it.getQuantity());
        }
        return map.values().stream().map(ProductInfo::new).toList();
    }
}
