import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("");
        ArrayList<String> queryStrings = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            strings.add(sc.next());
        }
        for (int i = 0; i < M; i++) {
            queryStrings.add(sc.next());
        }
        sc.close();

        new Solver(strings, queryStrings).solve();
    }
}

class Solver {
    ArrayList<String> strings;
    ArrayList<String> queryStrings;


    public Solver(
            ArrayList<String> strings,
            ArrayList<String> queryStrings
    ) {
        this.strings = strings;
        this.queryStrings = queryStrings;
    }

    public void solve() {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        HashMap<Integer, String> integerStringHashMap = new HashMap<>();

        for (int i = 1; i < strings.size(); i++) {
            stringIntegerHashMap.put(strings.get(i), i);
            integerStringHashMap.put(i, strings.get(i));
        }
        for (String queryString : queryStrings) {
            try {
                int value = Integer.valueOf(queryString);
                System.out.println(integerStringHashMap.get(value));
            } catch (NumberFormatException e) {
                System.out.println(stringIntegerHashMap.get(queryString));
            }
        }
    }
}