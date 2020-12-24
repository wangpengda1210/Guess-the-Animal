import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        int input = new Scanner(System.in).nextInt();
        decomposition(input, input, "");
    }

    private static void decomposition(int input, int max, String a) {
        if (input == 0) {
            System.out.println(a);
        } else if (input > 0) {
            for (int i = 1; i <= max; i++) {
                decomposition(input - i, i, a + i + " ");
            }
        }
    }
}