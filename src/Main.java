import java.util.*;
import java.util.stream.*;


public class Main {
    public static void main(String[] args) {
        System.out.println(
                IntStream
                        .range(1,11 )
                        .sum()
        );

        //stream.of, sorted(), findfirst()
        System.out.println();

        Stream.of(
                "Alberto", "Daniel", "Antonio"
        )
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println();

        //Array.stream, filter
        String[] names = {"Alberto", "Danteryder", "Antonio", "TonyStark", "DanielEdmundRyder"};
        Arrays.stream(names)
                .filter(x-> x.startsWith("D"))
                .sorted()
                .forEach(System.out::println);


        //map and average
        int[] num = {1,3,4,6,3,6,8,9};
        Arrays.stream(num)
                .map(x-> x*x)
                .average()
                .ifPresent(System.out::println);

        //filter, map, stream from list
        List<String> people = Arrays.asList("Daniel", "ALBERTO", "DANTERYDER", "TONYSTARK", "Flynnryder");
        people.stream()
                .map(String::toLowerCase)
                .filter(x->x.startsWith("d"))
                .forEach(System.out::println);
        //Reduction- sum

        double total = Stream.of(7.3, 8.2, 10.0)
                .reduce(0.0, (Double a, Double b)-> a+b);
        System.out.println(total);

    }
}