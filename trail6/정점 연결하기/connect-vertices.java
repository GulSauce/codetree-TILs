import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<LineEnd> lineEnds = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(br.readLine());
            lineEnds.add(new LineEnd(toInt(st), toInt(st)));
        }

        new Solver(N, lineEnds).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<LineEnd> lineEnds;
    int[] rootOf;

    public Solver(int endNodeNumber, List<LineEnd> lineEnds) {
        this.endNodeNumber = endNodeNumber;
        this.lineEnds = lineEnds;
        this.rootOf = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            rootOf[v] = v;
        }

        for (LineEnd lineEnd : lineEnds) {
            int left = lineEnd.left;
            int right = lineEnd.right;
            unionRoot(left, right);
        }

        for (int v = 1; v <= endNodeNumber; v++) {
            findRootWithCompacting(v);
        }

        int left = 0;
        int right = 0;

        for (int i = 1; i <= endNodeNumber; i++) {
            if (left == 0) {
                left = rootOf[i];
                continue;
            }
            if (left != rootOf[i]) {
                right = rootOf[i];
                break;
            }
        }

        System.out.println(left + " " + right);
    }

    private void unionRoot(int left, int right) {
        int leftRoot = findRootWithCompacting(left);
        int rightRoot = findRootWithCompacting(right);
        if (leftRoot < rightRoot) {
            rootOf[rightRoot] = leftRoot;
        } else {
            rootOf[leftRoot] = rightRoot;
        }
    }

    private int findRootWithCompacting(int cur) {
        int root = rootOf[cur];
        if (root == cur) {
            return cur;
        }

        rootOf[cur] = findRootWithCompacting(root);
        return rootOf[cur];
    }
}

class LineEnd {

    int left;
    int right;


    public LineEnd(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
