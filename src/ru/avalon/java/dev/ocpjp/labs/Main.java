package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        final Collection<Commodity> commodities = Commodity.random(5);
        /*
         * TODO(Студент): С использованием Java Stream API выполнить задачи из списка:
         */

        //1. Выполнить поиск самого дорого товара
        double mostExpensive = commodities.stream()
                .map(Commodity::getPrice)
                .max(Double::compare)
                .get();
        System.out.println("most expensive: " + mostExpensive);
        
        //2. Найти товары с минимальным остатком
        int minResidue = commodities.stream()
                .map(Commodity::getResidue)
                .min(Integer::compare)
                .get();
        System.out.println("min residue: " + minResidue);
        
        //3. Найти товары с остатком меньшим 5 и вывести в консоль их названия
        System.out.println("residue < 5:");
        commodities.stream()
                .filter(c -> c.getResidue() < 5)
                .map(Commodity::getName)
                .forEach(System.out::println);
        
        //4. Подсчитать общую стоимость товаров с учётом их остатка
        double totalPrice = commodities.stream()
                .mapToDouble(c -> c.getResidue() * c.getPrice())
                .sum();
        System.out.println("total price: " + totalPrice);
        
        //5. Найти товар, с самым длинным названием и вывести его на экран
        Optional<String> longestName = commodities.stream()
                .map(Commodity::getName)
                .collect(Collectors
                        .maxBy((c1, c2) -> Integer.compare(c1.length(), c2.length())));
        System.out.println("longest name: " + longestName.get());
        
        //6. Выполнить сортировку коллекции commodities
        System.out.println("sorted by code:");
        commodities.stream()                
                .sorted((c1, c2) -> Integer.valueOf(c1.getCode())
                                    .compareTo(Integer.valueOf(c2.getCode())))
                .forEach(System.out::println);

        //7. Найти среднюю стоимость товаров
        double averagePrice = commodities.stream()
                .mapToDouble(Commodity::getPrice)
                .average()
                .getAsDouble();
        System.out.println("average price: " + averagePrice);
        
        //8. Найти моду (медиану) стоимости товаров
        commodities.stream()
                .map(Commodity::getPrice)
                .sorted()
                .skip(commodities.size() / 2)
                .limit(1).forEach(c -> System.out.format("median: %s\n", c));
    }
}
