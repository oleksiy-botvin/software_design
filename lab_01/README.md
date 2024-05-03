# Варіант 1
## Запуск:
Запуск виконується стандартним методом [`main(String[] args)`](/lab_01/src/Main.java#L6)

## Programming Principles
- ### DRY (Don`t repeat yourself)
  повторення коду в програмі зведено до мінімуму
- ### KISS (Keep It Simple, Stupid)
  Код є простим і зрозумілим, використовуються базові структури даних, зрозумілі назви класів та класи мають просту та не велику за обсягом реалізацію коду.
- ### SOLID
  - #### Single responsibility principle
    ксласи [`ProductInfo`](/lab_01/src/data/entity/product/ProductInfo.java) та [`Product`](/lab_01/src/data/entity/product/Product.java) дотримується данного принципу. Вони мають єдине завдання - надати лише інформацію про продук без можливості її редагування
  - #### Open/closed principle
    Прогама дотримується данног принципу в такіх класах:
    - [`ProductInfo`](/lab_01/src/data/entity/product/ProductInfo.java)
    - [`Product`](/lab_01/src/data/entity/product/Product.java)
    - [`Screen`](/lab_01/src/screen/Screen.java)
  - #### Liskov substitution principle
    Клас [`Money`](/lab_01/src/data/entity/money/Money.java) та його дочірні класии дотримуються данного принципу
  - #### Dependency inversion principle
    У програмі доступ до складу реалізовано через інтерфейси [`Warehouse`](/lab_01/src/data/warehouse/Warehouse.java). Завдяки чому, звертаючись до нього, програма не прив’язується до конкретної реалізації, а лише до абстрактної сутності, яка описує його функціональність

- ### YAGNI (You Ain't Gonna Need It)
  Програма не містить функціоналу, який не використовується
- ### Program to Interfaces not Implementations
  Для роботи зі складом програма використовує інтерфейс [`Warehouse`](/lab_01/src/data/warehouse/Warehouse.java), який описує функціонал роботи з ним, а не конкретну реалізацію.
- ### Fail Fast
  Під час створення класу [`Reporting`](/lab_01/src/data/entity/Reporting.java) або виклику методу [`event(...)`](/lab_01/src/data/warehouse/WarehouseImpl.java#L23) дотримуються цей принцип, щоб не забути описати дії для нових дочірніх класів, успадкованих від [`Event`](/lab_01/src/data/entity/events/Event.java)
  
