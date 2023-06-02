import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class E01ReverseArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = reader.readLine().split("\\s+");

        printReverse(elements, elements.length - 1);
    }

    private static void printReverse(String[] elements, int index) {
        if(index < 0) {
            return;
        }
        System.out.print(elements[index] + " ");

        printReverse(elements, index - 1);
    }
}
