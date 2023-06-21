import java.util.stream.*;


public class Main {
    public static void main(String[] args) {
        IntStream
                .range(1,10)
                .skip(5)
                .forEach(x-> System.out.println(x));
        System.out.println();
    }
}