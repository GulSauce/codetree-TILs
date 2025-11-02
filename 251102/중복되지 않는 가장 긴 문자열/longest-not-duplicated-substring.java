import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String string;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        string = st.nextToken();

        new Solver(string).solve();
    }
}

class Solver {

    String string;
    HashMap<Character, Integer> charCountHashMap = new HashMap<>();

    public Solver(String string) {
        this.string = string;
    }

    public void solve() {
        int i = 0;
        int j = 0;
        int answer = j - i + 1;
        charCountHashMap.put(string.charAt(j), 1);
        for (; i < string.length(); i++) {
            while (true) {
                // 유지 조건 1
                if (j < i) {
                    break;
                }
                // 유지 조건 2
                if (2 <= charCountHashMap.get(string.charAt(j))) {
                    break;
                }
                // 정답 조건 1
                answer = Math.max(answer, j - i + 1);
                // 유지 조건 3
                if (j == string.length() - 1) {
                    break;
                }
                // 갱신 조건 1
                j++;
                Character curChar = string.charAt(j);
                charCountHashMap.put(curChar, charCountHashMap.getOrDefault(curChar, 0) + 1);
            }
            Character curChar = string.charAt(i);
            charCountHashMap.put(curChar, charCountHashMap.get(curChar) - 1);
        }
        System.out.println(answer);
    }
}