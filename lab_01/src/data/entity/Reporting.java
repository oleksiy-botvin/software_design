package data.entity;

import data.entity.events.Event;
import data.entity.events.ReceiptOfGoods;
import data.entity.events.ShipmentOfGoods;
import data.entity.events.Stocktaking;
import data.entity.product.ProductInfo;
import org.jetbrains.annotations.NotNull;
import util.ConsoleUtil;
import util.TableUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Reporting {

    @NotNull private final Calendar date;
    @NotNull private final String name;
    @NotNull private final List<ProductInfo> products;
    private final boolean isPrintLastDelivery;

    public Reporting(@NotNull Event event, @NotNull List<ProductInfo> products) {
        this(Calendar.getInstance(), event, products);
    }

    public Reporting(@NotNull Calendar date, @NotNull Event event, @NotNull List<ProductInfo> products) {
        if (event instanceof ReceiptOfGoods) {
            isPrintLastDelivery = false;
            this.name = "Надходження товару";
        } else if (event instanceof ShipmentOfGoods)  {
            isPrintLastDelivery = false;
            this.name = "Відвантаження товару";
        } else if (event instanceof Stocktaking) {
            isPrintLastDelivery = true;
            this.name = "Інвентаризацію";
        } else  {
            isPrintLastDelivery = false;
            throw new IllegalArgumentException("Not registered event: " + event.getClass().getName());
        }
        this.date = date;
        this.products = new ArrayList<>(products);
    }

    public @NotNull Calendar getDate() {
        Calendar it = Calendar.getInstance();
        it.setTimeInMillis(date.getTimeInMillis());
        return it;
    }

    public @NotNull String getName() {
        SimpleDateFormat formatter = dateFormat("dd MMMM yyyy");
        return "Звіт про " + name + " за " + formatter.format(this.date.getTime());
    }

    public @NotNull List<ProductInfo> getProducts() {
        return new ArrayList<>(products);
    }

    public void print() {
        SimpleDateFormat formatter = dateFormat("dd MMMM yyyy HH:mm");
        TableUtil.Column.Builder[] builders = new TableUtil.Column.Builder[] {
                new TableUtil.Column.Builder().width(4).addValueRow("№"),
                new TableUtil.Column.Builder().width(30).addValueRow("Назва товару"),
                new TableUtil.Column.Builder().width(24).addValueRow("Дата останнього завозу"),
                new TableUtil.Column.Builder().width(7).addValueRow("Одиниця виміру"),
                new TableUtil.Column.Builder().width(9).addValueRow("Кількість"),
                new TableUtil.Column.Builder().width(15).addValueRow("Ціна за одиницю"),
        };

        for (int i = 0; i < products.size(); i++) {
            ProductInfo it = products.get(i);
            builders[0].addValueRow(String.valueOf(i + 1));
            builders[1].addValueRow(it.getName());
            builders[2].addValueRow(formatter.format(it.getDateLastDelivery().getTime()));
            builders[3].addValueRow(it.getUnit());
            builders[4].addValueRow(String.valueOf(it.getQuantity()));
            builders[5].addValueRow(it.getPrice().toString());
        }

        if (isPrintLastDelivery) {
            TableUtil.printTable(
                    getName(),
                    builders[0].build(),
                    builders[1].build(),
                    builders[2].build(),
                    builders[3].build(),
                    builders[4].build(),
                    builders[5].build()
            );
        } else {
            TableUtil.printTable(
                    getName(),
                    builders[0].build(),
                    builders[1].build(),
                    builders[3].build(),
                    builders[4].build(),
                    builders[5].build()
            );
        }
        ConsoleUtil.inputEnter();
    }

    private SimpleDateFormat dateFormat(@NotNull String pattern) {
        return new SimpleDateFormat(pattern, new Locale("uk", "UA"));
    }
}
