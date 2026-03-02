import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m, k;
        List<Integer> movePoints = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);
        m = toInt(st);
        k = toInt(st);

        List<LineInfo> lineInfos = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            lineInfos.add(new LineInfo(toInt(st), toInt(st)));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            movePoints.add(toInt(st));
        }

        new Solver(n, lineInfos, movePoints).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNumber;
    List<LineInfo> lineInfos;
    List<Integer> movePoints;
    int[] parents;

    public Solver(int endNumber, List<LineInfo> lineInfos, List<Integer> movePoints) {
        this.endNumber = endNumber;
        this.lineInfos = lineInfos;
        this.movePoints = movePoints;
        this.parents = new int[endNumber + 1];
    }

    public void solve() {
        for (int i = 1; i <= endNumber; i++) {
            parents[i] = i;
        }

        for (LineInfo lineInfo : lineInfos) {
            unionParent(lineInfo.x, lineInfo.y);
        }

        int parent = findParent(movePoints.get(0));
        boolean movable = true;
        for (int movePoint : movePoints) {
            if (parent != findParent(movePoint)) {
                movable = false;
                break;
            }
        }
        System.out.println(movable ? 1 : 0);
    }

    private void unionParent(int x, int y) {
        int xParent = findParent(x);
        int yParent = findParent(y);

        if (xParent != yParent) {
            parents[yParent] = xParent;
        }
    }

    private int findParent(int number) {
        if (parents[number] == number) {
            return number;
        }

        parents[number] = findParent(parents[number]);
        return parents[number];
    }
}

class LineInfo {

    int x;
    int y;


    public LineInfo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}