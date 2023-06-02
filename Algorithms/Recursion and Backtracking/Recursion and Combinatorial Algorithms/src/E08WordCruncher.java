import java.util.*;
import java.util.stream.Collectors;

public class E08WordCruncher {
    public static List<String> words;
    public static List<String> combined = new ArrayList<>();
    public static String target;
    public static Map<Integer, List<String>> table = new HashMap<>();
    public static Map<String, Integer> occurrences = new HashMap<>();
    public static Set<String> out = new TreeSet<>();
    public static void main(String[] args) {

//        permutation without repetition
        Scanner scanner = new Scanner(System.in);

        words = Arrays.stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());


        target = scanner.nextLine();



//        for (Iterator<String> iter = words.iterator(); iter.hasNext();) {
//            String next = iter.next();
//            if(!target.contains(next)) {
//                iter.remove();
//            }
//        }
        words.removeIf(next ->!target.contains(next));

        for (String subst : words) {
            occurrences.putIfAbsent(subst, 0);
            occurrences.put(subst, occurrences.get(subst) + 1);
            int index = target.indexOf(subst);

            while(index != -1) {
                table.putIfAbsent(index, new ArrayList<>());
                table.get(index).add(subst);
               index =  target.indexOf(subst, index + 1);
            }

        }

        permuteString(0);

        for (String str : out) {
            System.out.println(str);
        }


    }

    private static void permuteString(int index) {
        if(index == target.length()) {
            printRes();
        } else if(table.containsKey(index)){
            List<String> strings = table.get(index);
            for (String str : strings) {
                if (occurrences.get(str) > 0) {
                    occurrences.put(str, occurrences.get(str) - 1);
                    combined.add(str);
                    permuteString(index + str.length());
                    combined.remove(combined.size() - 1);
                    occurrences.put(str, occurrences.get(str) + 1);
                }

            }


            }
        }


    private static void printRes() {
        String actual = String.join("", combined);
        if(actual.contains(target)) {
            out.add(String.join(" ", combined));
        }
    }
}
