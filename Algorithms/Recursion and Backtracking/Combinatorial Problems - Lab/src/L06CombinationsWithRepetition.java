import java.util.Scanner;

public class L06CombinationsWithRepetition {
    public static String[] elements;
    public static String[] combinations;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        elements = sc.nextLine().split("\\s+");
        int k = Integer.parseInt(sc.nextLine());
        combinations = new String[k];

        combination(0, 0);

    }

    private static void combination(int index, int start) {
        if(index == combinations.length) {
            printArr(combinations);
            return;
        }

        for (int i = start; i < elements.length; i++) {
            combinations[index] = elements[i];
            combination(index + 1, i);

        }
    }

    private static void printArr(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
    }

