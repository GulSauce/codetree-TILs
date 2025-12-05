import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> bNumbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            bNumbers.add(toInt(st));
        }
        new Solver(N, bNumbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int numberCount;
    HashSet<Integer> bNumberHashSet;
    List<Integer> bNumbers;
    TreeSet<Integer> aNumberTreeSet = new TreeSet<>();

    public Solver(int numberCount,
        List<Integer> bNumbers) {
        this.numberCount = 2 * numberCount;
        this.bNumbers = bNumbers;
    }

    public void solve() {
        bNumberHashSet = new HashSet<>(bNumbers);
        for (int i = 1; i <= numberCount; i++) {
            if (bNumberHashSet.contains(i)) {
                continue;
            }
            aNumberTreeSet.add(i);
        }
        int answer = 0;
        for (Integer bNumber : bNumbers) {
            Integer found = aNumberTreeSet.higher(bNumber);
            if (found == null) {
                continue;
            }
            answer++;
            aNumberTreeSet.remove(found);
        }
        System.out.println(answer);
    }
}