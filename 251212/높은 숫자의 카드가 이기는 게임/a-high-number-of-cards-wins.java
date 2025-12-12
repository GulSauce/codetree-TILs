import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

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

    int MAX_NUMBER;
    List<Integer> aNumbers = new ArrayList<>();
    List<Integer> bNumbers;
    HashSet<Integer> bNumberHashSet;

    public Solver(int MAX_NUMBER, List<Integer> bNumbers) {
        this.MAX_NUMBER = MAX_NUMBER;
        this.bNumbers = bNumbers;
    }

    public void solve() {
        bNumberHashSet = new HashSet<>(bNumbers);
        for (int i = 1; i <= 2 * MAX_NUMBER; i++) {
            if (bNumberHashSet.contains(i)) {
                continue;
            }
            aNumbers.add(i);
        }

        Collections.sort(aNumbers);
        Collections.sort(bNumbers);

        int aPoint = 0;
        int aIndex = 0;
        for (Integer bNumber : bNumbers) {
            while (true) {
                if (aIndex >= aNumbers.size()) {
                    break;
                }
                if (bNumber >= aNumbers.get(aIndex)) {
                    aIndex++;
                    continue;
                }
                break;
            }
            if (aIndex >= aNumbers.size()) {
                break;
            }
            if (aNumbers.get(aIndex) > bNumber) {
                aIndex++;
                aPoint++;
            }
        }
        System.out.println(aPoint);
    }
}