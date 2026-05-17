import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m;
        List<eraseNumber> eraseNumbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = toInt(st);
        m = toInt(st);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            eraseNumbers.add(new eraseNumber(toInt(st), toInt(st)));
        }

        new Solver(n, eraseNumbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<eraseNumber> eraseNumbers;

    int[] rootOf;
    int[] endOfGroup;

    public Solver(int endNodeNumber, List<eraseNumber> eraseNumbers) {
        this.endNodeNumber = endNodeNumber;
        this.eraseNumbers = eraseNumbers;
        this.rootOf = new int[100_001];
        this.endOfGroup = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            rootOf[v] = v;
            endOfGroup[v] = v;
        }

        int remains = endNodeNumber - 1;
        for (eraseNumber eraseNumber : eraseNumbers) {
            int a = eraseNumber.a;
            int b = eraseNumber.b;

            int firstGroup = findWithCompact(a);
            int endNumber = a;
            while (true) {
                int curGroup = findWithCompact(endNumber);
                endNumber = endOfGroup[curGroup];
                if (endNumber + 1 > b) {
                    break;
                }
                union(firstGroup, endNumber + 1);
                remains--;
            }
            System.out.println(remains + 1);
        }
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);
        if (left <= right) {
            rootOf[right] = left;
            endOfGroup[left] = endOfGroup[right];
        } else {
            rootOf[left] = right;
            endOfGroup[right] = endOfGroup[left];
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

class eraseNumber {

    int a;
    int b;

    public eraseNumber(int a, int b) {
        this.a = a;
        this.b = b;
    }
}