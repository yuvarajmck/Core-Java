import java.util.Arrays;
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

    }
}