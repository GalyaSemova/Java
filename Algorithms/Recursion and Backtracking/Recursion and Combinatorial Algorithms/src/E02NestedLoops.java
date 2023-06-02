import java.util.Scanner;

public class E02NestedLoops {
    public static int[] numbers;
    public static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = Integer.parseInt(sc.nextLine());

        numbers = new int[n];

        permute(0);


    }

    private static void permute(int index) {
        if (index == numbers.length)  {
            printArr();
        } else {
            for (int i = 1; i <= n; i++) {
                numbers[index] = i;
                permute(index + 1);
            }
        }
    }

    private static void printArr() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
}
