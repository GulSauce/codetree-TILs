import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
    ArrayList<String> sortedWords = new ArrayList<>();

    public Solver(
            ArrayList<String> words
    ) {
        this.words = words;
    }

    public void solve() {
        for (String word : words) {
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            sortedWords.add(new String(wordChars));
        }
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        int answer = 0;
        for (String sortedWord : sortedWords) {
            frequencyMap.put(sortedWord, frequencyMap.getOrDefault(sortedWord, 0) + 1);
            answer = Math.max(answer, frequencyMap.get(sortedWord));
        }
        System.out.println(answer);
    }
}