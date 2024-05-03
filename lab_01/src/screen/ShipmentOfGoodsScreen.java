package screen;

import data.entity.Reporting;
import data.entity.events.ShipmentOfGoods;
import data.entity.events.Stocktaking;
import data.entity.exeption.InsufficientQuantityOfGoodsException;
import data.entity.exeption.ProductIsNotAvailableException;
import data.entity.money.*;
import data.entity.product.IncomingProduct;
import data.entity.product.Product;
import data.entity.product.ProductInfo;
import data.warehouse.Warehouse;
import org.jetbrains.annotations.NotNull;
import util.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ShipmentOfGoodsScreen extends Screen {

    private final @NotNull Warehouse warehouse;

    public ShipmentOfGoodsScreen(@NotNull Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    protected void onCreate() {
        showMenu(new ArrayList<>());
    }

    private void showMenu(@NotNull List<Product> products) {
        List<String> items = new ArrayList<>();
        items.add("Повернутися до попереднього меню");
        items.add("Переглянути список");
        items.add("Додати товар");
        if (!products.isEmpty()) {
            items.add("Видалити товар");
            items.add("Оформити товар");
        }

        int index = ConsoleUtil.selectItem("Вкажіть номер пункту", items.toArray(new String[0]));

        switch (index) {
            case 0: return;
            case 1:
                System.out.println("\nТовари:");
                for (int i = 0; i < products.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + productInfo(products.get(i)));
                }
                break;
            case 2:
                showAddProduct(products);
                break;
            case 3:
                showRemoveProduct(products);
                break;
            case 4:
                warehouse.event(
                        new ShipmentOfGoods(products),
                        Reporting::print,
                        this::onShipmentOfGoodsError
                );
                return;
        }

        showMenu(products);
    }

    private void showAddProduct(@NotNull List<Product> products) {
        warehouse.event(
                new Stocktaking(),
                reporting -> {
                    int index = ConsoleUtil.selectItem(
                            "Введіть номер продукту, який бажаєте придбати",
                            false,
                            reporting.getProducts().stream().map(this::productInfo).toArray(String[]::new)
                    );

                    ProductInfo it = reporting.getProducts().get(index);
                    int quantity = ConsoleUtil.inputInt(
                            "Введіть кількість",
                            1,
                            it.getQuantity()
                    );

                    products.add(new Product(it.getName(), it.getUnit(), quantity));
                },
                it -> System.out.println("Не вдалося отримати список продуктів!!!")
        );
    }

    private void showRemoveProduct(@NotNull List<Product> products) {
        int index = ConsoleUtil.selectItem(
                "Введіть номер продукту, який потрібно видалити",
                false,
                products.stream().map(this::productInfo).toArray(String[]::new)
        );

        products.remove(index);
    }

    private @NotNull String productInfo(@NotNull Product it) {
        return it.getName() + ": " + it.getQuantity() + " " + it.getUnit();
    }

    private void onShipmentOfGoodsError(@NotNull Throwable t) {
        if (t instanceof ProductIsNotAvailableException || t instanceof InsufficientQuantityOfGoodsException) {
            System.out.println(t.getMessage());
        } else {
            System.out.println("Неможливо відправити продукт, сталася неочікувана помилка");
        }
    }
}
