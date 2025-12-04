import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Gem> gems = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gems.add(new Gem(toInt(st), toInt(st)));
        }
        Collections.sort(gems, Comparator.reverseOrder());

        new Solver(M, gems).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int targetWeight;
    List<Gem> gems;

    public Solver(int targetWeight, List<Gem> gems) {
        this.targetWeight = targetWeight;
        this.gems = gems;
    }

    public void solve() {
        int curWeight = 0;
        double value = 0;
        for (Gem gem : gems) {
            int ableWeight = Math.min(targetWeight - curWeight, gem.weight);
            if (ableWeight == 0) {
                break;
            }
            value += gem.valuePerWeight * ableWeight;
            curWeight += ableWeight;
        }
        System.out.printf("%.3f%n", value);
    }
}

class Gem implements Comparable<Gem> {

    int weight;
    int value;
    double valuePerWeight;

    public Gem(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valuePerWeight = (double) value / weight;
    }

    @Override
    public int compareTo(Gem other) {
        return Double.compare(this.valuePerWeight, other.valuePerWeight);
    }
}