import java.util.Scanner;

public class L07NChooseKCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine());
//        n!/(n-k)!/k! permutation
//        pascal pyramid
//        3,2 -> 3 ; 49, 6 -> 13983816
        int binom = binom(n,k);
        System.out.println(binom);
    }

    private static int binom(int n, int k) {
        if(k > n) {
            return 0;
        }
        if(k == 0 || k == n) {
            return 1;
        }

        return binom(n - 1, k - 1) + binom(n - 1, k );
    }
}
