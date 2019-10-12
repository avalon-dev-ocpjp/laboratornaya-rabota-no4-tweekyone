package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
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
        System.out.println("Minimum balance:");
        commodities.stream()
                   .filter(x -> x.getResidue() == (int)minResidue.get())
                   .map(Commodity::getName)
                   .forEach(System.out::println);
        
        System.out.println();
        
        //Товары с остатком меньше 5
        System.out.println("Goods with a balance of less than 5");
        commodities.stream()
                    .filter(x -> x.getResidue() < 5)
                    .map(Commodity::getName)
                    .forEach(System.out::println);
        
        System.out.println();
        
        //Подсчитать общую стоимость товаров с учетом их остатка
        double totalPrice = commodities.stream()
                                       .mapToDouble(x -> x.getPrice() * x.getResidue())
                                       .sum();
        System.out.println("Total price = " + (float) totalPrice);
        
        System.out.println();
        
        //Товар, с самым длинным названием
        OptionalInt maxLength = commodities.stream()
                                           .map(Commodity::getName)
                                           .mapToInt(x -> x.length())
                                           .max();
        System.out.println("Longest name is:");
        commodities.stream()
                   .filter(x -> x.getName().length() == maxLength.getAsInt())
                   .map(Commodity::getName)
                   .forEach(System.out::println);
        
        System.out.println();
        
        //Сортировка коллекции commodities
        commodities.stream().sorted();
        
        //Средняя стоимость товаров
        OptionalDouble average = commodities.stream()
                                            .mapToDouble(x -> x.getPrice())
                                            .average();
        
        System.out.printf("Average price of goods is %.2f", average.getAsDouble());
        
        System.out.println();
        
        //Мода (медиана) стоимости товара
        List<Double> list = commodities.stream()
                                       .map(Commodity::getPrice)
                                       .collect(Collectors.toList());
        
        Double median = list.size() % 2 == 0 ? EventSetMedian.call(list) : OddSetMedian.call(list);
        System.out.println("Median = " + median);
    }
    
    
    private static class EventSetMedian{

        public static Double call(List<Double> list) throws Exception {
            return Arrays.stream(list.toArray())
                        .mapToDouble(x -> (double)x)
                        .sorted()
                        .skip(list.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .orElseThrow(IllegalStateException::new);
        }
        
    }
    
    private static class OddSetMedian{

        public static Double call(List<Double> list) throws Exception {
            return Arrays.stream(list.toArray())
                        .mapToDouble(x -> (double)x)
                        .sorted()
                        .skip(list.size() / 2)
                        .findFirst()
                        .orElseThrow(IllegalStateException::new);
        }
    }
}
