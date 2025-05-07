import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<String> words = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            words.add(sc.next());
        }
        sc.close();

        new Solver(words).solve();
    }
}

class Solver {
    ArrayList<String> words;

    public Solver(
            ArrayList<String> words
    ) {
        this.words = words;
    }

    public void solve() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        for (String word : words) {
            treeMap.put(word, treeMap.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}