import java.util.Scanner;

public class L04RecursiveFactorial {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        long result = factorial(n);
        System.out.println(result);

    }

    private static long factorial(int n) {
        if(n <= 0) {
            return 1;
        }
        return n * factorial(n-1);
    }
}
