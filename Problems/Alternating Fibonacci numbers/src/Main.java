import java.util.Scanner;

public class Main {

    public static long fib(long n){
        // write your code here
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return -1;
        }

        return (long) ((Math.abs(fib(n - 1)) + Math.abs(fib(n - 2))) * Math.pow(-1, n - 1));
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fib(n));
    }
}