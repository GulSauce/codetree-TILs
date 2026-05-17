import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int M;
        List<Integer> maxPutNumber = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            maxPutNumber.add(toInt(st));
        }

        new Solver(N, maxPutNumber).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<Integer> maxPutNumbers;

    int[] rootOf;

    public Solver(int endNodeNumber, List<Integer> maxPutNumbers) {
        this.endNodeNumber = endNodeNumber;
        this.maxPutNumbers = maxPutNumbers;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        for (int v = 0; v <= endNodeNumber; v++) {
            rootOf[v] = v;
        }

        int answer = 0;
        for (int maxPutNumber : maxPutNumbers) {
            int realMaxPutNumber = findWithCompact(maxPutNumber);
            if (realMaxPutNumber == 0) {
                break;
            }
            union(realMaxPutNumber - 1, realMaxPutNumber);
            answer++;
        }
        System.out.println(answer);
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);
        if (left <= right) {
            rootOf[right] = left;
        } else {
            rootOf[left] = right;
        }
    }

    private int findWithCompact(int cur) {
        int parent = rootOf[cur];
        if (parent == cur) {
            return cur;
        }

        rootOf[cur] = findWithCompact(parent);
        return rootOf[cur];
    }
}