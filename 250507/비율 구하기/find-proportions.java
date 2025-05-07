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
    int wordCount;
    ArrayList<String> words;

    public Solver(
            ArrayList<String> words
    ) {
        this.wordCount = words.size();
        this.words = words;
    }

    public void solve() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (String word : words) {
            treeMap.put(word, treeMap.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            double ratio = (double) entry.getValue() / wordCount * 100;
            String ratioString = String.format("%.4f", ratio);
            System.out.println(entry.getKey() + " " + ratioString);
        }
    }
}