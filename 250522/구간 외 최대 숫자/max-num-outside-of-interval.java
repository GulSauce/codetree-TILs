import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, Q;
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(-1);
        ArrayList<Dist> dists = new ArrayList<>();
        int a, b;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            dists.add(new Dist(a, b));
        }
        br.close();

        new Solver(numbers, dists).solve();
    }
}

class Solver {

    int[] leftMax;
    int[] rightMax;

    ArrayList<Integer> numbers;
    ArrayList<Dist> dists;

    public Solver(
        ArrayList<Integer> numbers,
        ArrayList<Dist> dists
    ) {
        this.numbers = numbers;
        this.dists = dists;
        this.leftMax = new int[numbers.size()];
        this.rightMax = new int[numbers.size() + 1];
    }

    public void solve() {
        for (int i = 1; i < numbers.size(); i++) {
            leftMax[i] = Math.max(leftMax[i - 1], numbers.get(i));
        }
        for (int i = numbers.size() - 1; i >= 1; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], numbers.get(i));
        }
        for (Dist dist : dists) {
            System.out.println(Integer.max(leftMax[dist.left - 1], rightMax[dist.right + 1]));
        }
    }
}

class Dist {

    int left;
    int right;

    public Dist(
        int left,
        int right
    ) {
        this.left = left;
        this.right = right;
    }
}