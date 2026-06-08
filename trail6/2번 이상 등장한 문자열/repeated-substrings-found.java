import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        String S;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = st.nextToken();
        new Solver(S).solve();
    }
}

class Solver {

    final int P1 = 31;
    final int P2 = 51;

    long[] p1PowerOf;
    long[] p2PowerOf;

    ModCal modCal1 = new ModCal(1_000_000_007);
    ModCal modCal2 = new ModCal(1_000_000_009);

    String target;

    public Solver(String target) {
        this.target = target;
        this.p1PowerOf = new long[target.length()];
        this.p2PowerOf = new long[target.length()];
    }

    public void solve() {
        p1PowerOf[0] = 1;
        p2PowerOf[0] = 1;
        for (int i = 1; i < target.length(); i++) {
            p1PowerOf[i] = modCal1.of(p1PowerOf[i - 1]).mul(P1).get();
            p2PowerOf[i] = modCal2.of(p2PowerOf[i - 1]).mul(P2).get();
        }

        int left = 1;
        int right = target.length();
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isSubstringHasLength(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private boolean isSubstringHasLength(int length) {
        HashMap<Long, Integer> subStringCount = new HashMap<>();
        long hash1 = 0;
        long hash2 = 0;

        for (int i = 0; i < length; i++) {
            hash1 = modCal1.of(target.charAt(i) - 'a' + 1).mul(p1PowerOf[length - 1 - i])
                .add(hash1).get();
            hash2 = modCal2.of(target.charAt(i) - 'a' + 1).mul(p2PowerOf[length - 1 - i])
                .add(hash2).get();
        }

        long hashValue = hash1 << 32 | hash2;
        subStringCount.put(hashValue, 1);

        int left = 0;
        int right = length - 1;
        while (true) {

            long localHash1 = hashValue >>> 32;
            localHash1 = modCal1.of(localHash1)
                .sub(modCal1.of(target.charAt(left) - 'a' + 1).mul(p1PowerOf[length - 1]).get())
                .mul(P1)
                .get();

            long localHash2 = hashValue << 32 >>> 32;
            localHash2 = modCal2.of(localHash2)
                .sub(modCal2.of(target.charAt(left) - 'a' + 1).mul(p2PowerOf[length - 1]).get())
                .mul(P2)
                .get();

            left++;
            right++;

            if (right >= target.length()) {
                break;
            }

            localHash1 = modCal1.of(localHash1).add(target.charAt(right) - 'a' + 1).get();
            localHash2 = modCal2.of(localHash2).add(target.charAt(right) - 'a' + 1).get();

            hashValue = localHash1 << 32 | localHash2;
            subStringCount.compute(hashValue, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }

        List<Integer> sorted = new ArrayList<>(subStringCount.values());
        sorted.sort((a, b) -> Integer.compare(b, a));
        return sorted.get(0) >= 2 ? true : false;
    }
}

class ModCal {

    private final int R;

    public ModCal(int r) {
        R = r;
    }

    public Val of(long v) {
        return new Val(v % R);
    }

    class Val {

        private long v;

        public Val(long v) {
            this.v = v;
        }

        public Val add(long b) {
            v = (v + b) % R;
            return this;
        }

        public Val sub(long b) {
            v = (v - b) % R;
            v = v < 0 ? v + R : v;
            return this;
        }

        public Val mul(long b) {
            v = v * b % R;
            return this;
        }

        public long get() {
            return v;
        }
    }
}