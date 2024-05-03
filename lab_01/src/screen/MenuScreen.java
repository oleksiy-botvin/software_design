package screen;

import data.entity.Reporting;
import data.entity.events.ReceiptOfGoods;
import data.entity.events.Stocktaking;
import data.entity.money.UAH;
import data.entity.product.IncomingProduct;
import data.entity.product.ProductInfo;
import data.warehouse.Warehouse;
import data.warehouse.WarehouseImpl;
import org.jetbrains.annotations.NotNull;
import util.ConsoleUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MenuScreen extends Screen {


    @Override
    protected void onCreate() {
        Warehouse warehouse = new WarehouseImpl();
        List<IncomingProduct> items = new ArrayList<>();
        items.add(new IncomingProduct("Молоток", new UAH(12, 15), "шт", 10));
        items.add(new IncomingProduct("М'яч", new UAH(5, 0), "шт", 15));
        items.add(new IncomingProduct("Стіл", new UAH(1500, 0), "шт", 3));
        items.add(new IncomingProduct("Комод", new UAH(10000, 0), "шт", 5));
        warehouse.event(new ReceiptOfGoods(Calendar.getInstance(), items), it -> showMenu(warehouse));
    }

    private void showMenu(@NotNull Warehouse warehouse) {
        int index = ConsoleUtil.selectItem(
                "Вкажіть номер пункту",
                "Завершити роботу",
                "Надходження товару",
                "Відвантаження товару",
                "Інвентаризації"
        );

        switch (index) {
            case 0: return;
            case 1:
                new ReceiptOfGoodsScreen(warehouse).dispatchCreate();
                break;
            case 2:
                new ShipmentOfGoodsScreen(warehouse).dispatchCreate();
                break;
            case 3:
                warehouse.event(
                        new Stocktaking(),
                        Reporting::print,
                        it -> System.out.println("Під час запиту інвентаризації сталася помилка!!!")
                );
                break;
        }
        showMenu(warehouse);
    }
}
