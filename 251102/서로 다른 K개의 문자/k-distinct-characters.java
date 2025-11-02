import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String string;
        int K;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        string = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        new Solver(string, K).solve();
    }
}

class Solver {

    String string;
    int maxDiffCount;
    HashMap<Character, Integer> charCountHashMap = new HashMap<>();
    HashSet<Character> uniqueFinderHashSet = new HashSet<>();

    public Solver(String string, int K) {
        this.string = string;
        this.maxDiffCount = K;
    }

    public void solve() {
        int i = 0;
        int j = 0;
        int answer = j - i + 1;
        charCountHashMap.put(string.charAt(j), 1);
        uniqueFinderHashSet.add(string.charAt(j));
        for (; i < string.length(); i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (maxDiffCount < uniqueFinderHashSet.size()) {
                    break;
                }
                answer = Math.max(answer, j - i + 1);
                if (j == string.length() - 1) {
                    break;
                }
                j++;
                Character cur = string.charAt(j);
                charCountHashMap.put(cur, charCountHashMap.getOrDefault(cur, 0) + 1);
                uniqueFinderHashSet.add(cur);
            }
            Character cur = string.charAt(i);
            charCountHashMap.put(cur, charCountHashMap.get(cur) - 1);
            if (charCountHashMap.get(cur) == 0) {
                uniqueFinderHashSet.remove(cur);
            }
        }
        System.out.println(answer);
    }
}