import java.util.Arrays;
import java.util.Scanner;

public class L01RecursiveArraySum {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = sumNumbers(arr,0);

        System.out.println(sum);
    }

    private static int sumNumbers(int[] arr, int index) {
        if(index >= arr.length ) {
            return 0;
        }

        return arr[index] + sumNumbers(arr, index + 1);

    }
}
