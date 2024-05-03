package screen;

import data.entity.Reporting;
import data.entity.events.ReceiptOfGoods;
import data.entity.money.*;
import data.entity.product.IncomingProduct;
import data.warehouse.Warehouse;
import org.jetbrains.annotations.NotNull;
import util.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ReceiptOfGoodsScreen extends Screen {

    private final @NotNull Warehouse warehouse;

    public ReceiptOfGoodsScreen(@NotNull Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    protected void onCreate() {
        showMenu(new ArrayList<>());
    }

    private void showMenu(@NotNull List<IncomingProduct> products) {
        List<String> items = new ArrayList<>();
        items.add("Повернутися до попереднього меню");
        items.add("Переглянути список");
        items.add("Додати товар");
        if (!products.isEmpty()) {
            items.add("Видалити товар");
            items.add("Відправити на склад");
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
                        new ReceiptOfGoods(products),
                        Reporting::print,
                        it -> System.out.println("Під час отримання товару сталася помилка!!!")
                );
                return;
        }

        showMenu(products);
    }

    private void showAddProduct(@NotNull List<IncomingProduct> products) {
        System.out.println("Додати товар");
        String name = ConsoleUtil.inputLine("Введіть назву");
        String unit = ConsoleUtil.inputLine("Введіть одиницю виміру (наприклад: \"шт\", \"кг\", \"л\")");
        int quantity = ConsoleUtil.inputInt("Введіть кількість");
        float price = ConsoleUtil.inputFloat("Введіть вартість");

        System.out.println("Оберіть валюту");
        int index = ConsoleUtil.selectItem(
                "Оберіть валюту",
                "фунт стерлінгів Великобританії",
                "гривні",
                "доллар",
                "євро",
                "злотий"
        );

        @NotNull Money money = switch (index) {
            case 0 -> new GBP(price);
            case 2 -> new USD(price);
            case 3 -> new EUR(price);
            case 4 -> new PLN(price);
            default -> new UAH(price);
        };

        products.add(new IncomingProduct(name, money, unit, quantity));
    }

    private void showRemoveProduct(@NotNull List<IncomingProduct> products) {
        int index = ConsoleUtil.selectItem(
                "Введіть номер продукту, який потрібно видалити",
                false,
                products.stream().map(this::productInfo).toArray(String[]::new)
        );

        products.remove(index);
    }

    private @NotNull String productInfo(@NotNull IncomingProduct it) {
        return it.getName() + ": " + it.getQuantity() + " " + it.getUnit() + ", " + it.getPrice();
    }
}
