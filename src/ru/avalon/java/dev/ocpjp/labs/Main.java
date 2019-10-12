package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

public class Main {

    public static void main(String[] args) throws IOException {
        final Collection<Commodity> commodities = Commodity.random(100);

        /*
         * TODO(Студент): С использованием Java Stream API выполнить задачи из списка:
         * 1. Выполнить поиск самого дорого товара
         * 2. Найти товары с минимальным остатком
         * 3. Найти товары с остатком меньшим 5 и вывести в консоль их названия
         * 4. Подсчитать общую стоимость товаров с учётом их остатка
         * 5. Найти товар, с самым длинным названием и вывести его на экран
         * 6. Выполнить сортировку коллекции commodities
         * 7. Найти среднюю стоимость товаров
         * 8. Найти моду (медиану) стоимости товаров
         */
        
        //Самый дорогой товар
        OptionalDouble maxPrice = commodities.stream()
                                            .mapToDouble(Commodity::getPrice)
                                            .max();
        System.out.println("Most expensive product:");
        commodities.stream()
                   .filter(x -> x.getPrice() == maxPrice.getAsDouble())
                   .map(Commodity::getName)
                   .forEach(System.out::println);        
        
        System.out.println();
        
        //Товары с минимальным остатком
         Optional<Integer> minResidue = commodities.stream()
                                                    .map(Commodity::getResidue)
                                                    .sorted()
                                                    .findFirst();
         System.out.println("minimum balance:");
         commodities.stream()
                    .filter(x -> x.getResidue() == (int)minResidue.get())
                    .map(Commodity::getName)
                    .forEach(System.out::println);
    }
}
