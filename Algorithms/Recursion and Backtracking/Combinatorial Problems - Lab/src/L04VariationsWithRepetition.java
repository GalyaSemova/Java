import java.util.Scanner;

public class L04VariationsWithRepetition {
    public static String[] kSlots;
    public static String[] variations;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        kSlots = sc.nextLine().split("\\s+");
        int k = Integer.parseInt(sc.nextLine());

        variations = new String[k];

        variation(0);

    }
    private static void variation(int index) {
        if(index == variations.length) {
            printArr(variations);
            return;
        }
        for (int i = 0; i < kSlots.length; i++) {
            variations[index] = kSlots[i];
            variation(index + 1);

        }

    }

    private static void printArr(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
