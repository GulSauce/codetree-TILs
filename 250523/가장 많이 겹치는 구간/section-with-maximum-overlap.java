import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Dist> dists = new ArrayList<>();
        int x1, x2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            dists.add(new Dist(x1, x2));
        }
        br.close();

        new Solver(dists).solve();
    }
}

class Solver {

    ArrayList<Dist> dists;
    int[] count = new int[200001];

    public Solver(
        ArrayList<Dist> dists
    ) {
        this.dists = dists;
    }

    public void solve() {
        for (Dist dist : dists) {
            count[dist.start]++;
            count[dist.end]--;
        }

        int maxSum = 0;
        int curSum = 0;
        for (int i = 1; i <= 200000; i++) {
            curSum += count[i];
            maxSum = Math.max(maxSum, curSum);
        }

        int answer = 0;
        boolean started = false;
        curSum = 0;
        for (int i = 1; i <= 200000; i++) {
            curSum += count[i];
            if (curSum != maxSum) {
                started = false;
                continue;
            }
            if (started) {
                continue;
            }
            started = true;
            answer++;
        }
        System.out.println(answer);
    }
}


class Dist {

    int start;
    int end;

    public Dist(
        int x1,
        int x2
    ) {
        this.start = x1;
        this.end = x2;
    }
}