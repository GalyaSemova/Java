import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class E06ConnectedAreasInMatrix {
    public static char[][] matrix;
    public static List<int[]> areas;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());

        matrix = new char[r][c];

        for (int i = 0; i < r; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        areas = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == '-') {
                    areas.add(new int[] {row, col, 0});
                    findAreas(row, col);
                }
            }
        }

//        Print Result
        System.out.println("Total areas found: " + areas.size());

        AtomicInteger counter = new AtomicInteger(1); // in lambda
        areas.stream()
                .sorted((f,s) -> Integer.compare(s[2], f[2]))
                .forEach(e -> {
                    System.out.printf("Area #%d at (%d, %d), size: %d%n", counter.getAndIncrement(), e[0], e[1], e[2] );

                });
    }

    private static void findAreas(int row, int col) {
        if(isOutOfBounds(row, col) || isNotPassable(row, col)) {
            return;
        }
//        marc cell as Visited
        matrix[row][col] = 'V';

//        increase the counter in the array in areas

        areas.get(areas.size() - 1)[2]++;


        findAreas(row + 1, col);
        findAreas(row , col + 1);
        findAreas(row - 1, col);
        findAreas(row, col - 1);

    }

    private static boolean isNotPassable(int row, int col) {
        return matrix[row][col] == '*' || matrix[row][col] == 'V' ;
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
