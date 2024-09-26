public class Main {
    public static void main(String[] args) {
        System.out.println("Fibonacci sequence from 0 to 9: ");
        Sequence iterator = new FibonacciIterator();
        for (int i = 0; i < 10; i++) {
            System.out.println(iterator.next());
        }
        System.out.println("\nFibonacci sequence from 15 to 19: ");
        iterator = new FibonacciIterator(15);
        for (int i = 0; i < 5; i++) {
            System.out.println(iterator.next());
        }
    }
}
