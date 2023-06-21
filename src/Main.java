import java.util.stream.*;


public class Main {
    public static void main(String[] args) {
        IntStream
                .range(1,10)

                .forEach(System.out::println);
        System.out.println();
    }
}