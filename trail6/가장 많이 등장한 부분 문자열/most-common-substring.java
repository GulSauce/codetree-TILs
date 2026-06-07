import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int L;
        String S;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        S = st.nextToken();

        new Solver(L, S).solve();
    }
}

class Solver {

    int length;
    final int P1 = 31;
    final int R1 = 1_000_000_007;
    final int P2 = 51;
    final int R2 = 1_000_000_009;

    long[] p1PowerOf;
    long[] p2PowerOf;

    String input;

    HashMap<Long, Integer> stringCount = new HashMap<>();

    public Solver(int length, String input) {
        this.p1PowerOf = new long[length];
        this.p2PowerOf = new long[length];
        this.length = length;
        this.input = input;
    }

    public void solve() {

        p1PowerOf[0] = 1;
        for (int i = 1; i < length; i++) {
            p1PowerOf[i] = P1 * p1PowerOf[i - 1];
            p1PowerOf[i] %= R1;
            p2PowerOf[i] = P2 * p2PowerOf[i - 1];
            p2PowerOf[i] %= R2;
        }

        long hashValue1 = 0;
        long hashValue2 = 0;
        for (int i = 0; i < length; i++) {
            hashValue1 += (long) p1PowerOf[length - 1 - i] * (input.charAt(i) - 'a' + 1) % R1;
            hashValue1 %= R1;
            hashValue2 += (long) p2PowerOf[length - 1 - i] * (input.charAt(i) - 'a' + 1) % R2;
            hashValue2 %= R2;
        }
        stringCount.put(hashValue1 << 32 | hashValue2, 1);

        int curStart = 0;
        int curEnd = length - 1;
        while (true) {
            hashValue1 -= (long) p1PowerOf[length - 1] * (input.charAt(curStart) - 'a' + 1);
            hashValue1 %= R1;
            if (hashValue1 < 0) {
                hashValue1 += R1;
            }
            hashValue1 *= P1;
            hashValue1 %= R1;

            hashValue2 -= (long) p2PowerOf[length - 1] * (input.charAt(curStart) - 'a' + 1);
            hashValue2 %= R2;
            if (hashValue2 < 0) {
                hashValue2 += R2;
            }
            hashValue2 *= P2;
            hashValue2 %= R2;
            curStart++;

            curEnd++;
            if (curEnd >= input.length()) {
                break;
            }
            hashValue1 += (long) p1PowerOf[0] * (input.charAt(curEnd) - 'a' + 1);
            hashValue1 %= R1;
            hashValue2 += (long) p2PowerOf[0] * (input.charAt(curEnd) - 'a' + 1);
            hashValue2 %= R2;

            stringCount.compute(hashValue1 << 32 | hashValue2, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }

        List<Integer> sorted = new ArrayList<>(stringCount.values());
        sorted.sort((a, b) ->
            Integer.compare(b, a)
        );

        System.out.println(sorted.get(0));
    }
}