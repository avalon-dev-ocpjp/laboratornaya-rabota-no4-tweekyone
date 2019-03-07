package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        final Collection<Commodity> commodities = Commodity.random(11);

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
//       1
        double expware = commodities.stream()
                .map(Commodity::getPrice)
                .max(Double::compare)
                .get();
        System.out.println("Most expensive price is " + expware);
//        2
        int minpr = commodities.stream()
                .map(Commodity::getResidue)
                .min(Integer::compare)
                .get();
        System.out.println("Min residue is " + minpr);
//        3
        System.out.println("Wares witn residue<5 if exists:");
        commodities.stream()
                .filter(c -> c.getResidue() < 5)
                .map(Commodity::getName)
                .forEach(System.out::println);
//        4
        double summary = commodities.stream()
                .mapToDouble(c -> c.getPrice() * c.getResidue())
                .sum();
        System.out.println("Summary price of ware is " + summary);
//        5
        Optional<Commodity> maxname = commodities.stream()
                .max(Comparator.comparingInt(c -> c.getName().length()));
        System.out.println(maxname.get().getName());
//        6
        commodities.stream()
                .sorted((x, y) -> Integer.valueOf(x.getCode()).compareTo(Integer.valueOf(y.getCode())))
                .map(Commodity::toString)
                .forEach(System.out::println);
//        7
        double averPrice = commodities.stream()
                .mapToDouble(Commodity::getPrice)
                .average()
                .getAsDouble();
        System.out.println("Average price is " + averPrice);
//        8
        Stream<Double> streamForMedian = commodities.stream()
                .map(Commodity::getPrice)
                .sorted();
        double median = 0.0;
        int size = commodities.size();
        System.out.print("Median is ");
        if (size % 2 == 0) {
            median = streamForMedian
                    .skip(size / 2 - 1)
                    .limit(2)
                    .reduce(median, (acc, x) -> acc + x / 2);
            System.out.println(median);
        }else{
            streamForMedian
                    .skip(size / 2)
                    .limit(1)
                    .forEach(System.out::println);
        }

    }
}
