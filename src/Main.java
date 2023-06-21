import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;



public class Main {
    public static void main(String[] args) throws IOException {
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
//  Stream rows from text file, sort, filter, and print
        Stream<String> bands = Files.lines(Paths.get("bands.txt"));
        bands
                .sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        bands.close();

        // Stream rows from text file and save to List
        List<String> bands2 = Files.lines(Paths.get("bands.txt"))
                .filter(x -> x.contains("jit"))
                .collect(Collectors.toList());
        bands2.forEach(x -> System.out.println(x));

        //  Stream rows from CSV file and count
        Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        // . Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
        rows2.close();

        //  Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }

        //Reduction-sum

        double total = Stream.of(7.3, 8.2, 10.0)
                .reduce(0.0, (Double a, Double b)-> a+b);
        System.out.println(total);

        IntSummaryStatistics summary = IntStream.of(7, 4,3,5,7,8,9,0,3)
                .summaryStatistics();
        System.out.println(summary);
    }
}