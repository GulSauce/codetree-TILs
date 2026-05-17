import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<MatchLog> matchLogs = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            matchLogs.add(new MatchLog(toInt(st), toInt(st)));
        }

        new Solver(N, matchLogs).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNodeNumber;
    List<MatchLog> matchLogs;

    final int NOT_MATCHED = 0;
    final int TEAM_WHITE = 1;
    final int TEAM_RED = 2;

    int[] rootOf;
    int[] opponent;

    public Solver(int endNodeNumber, List<MatchLog> matchLogs) {
        this.endNodeNumber = endNodeNumber;
        this.matchLogs = matchLogs;
        this.rootOf = new int[100_001];
        this.opponent = new int[100_001];
    }

    public void solve() {
        for (int v = 1; v <= endNodeNumber; v++) {
            rootOf[v] = v;
        }

        for (MatchLog matchLog : matchLogs) {
            int p1 = matchLog.p1;
            int p2 = matchLog.p2;

            int p1Team = findWithCompact(p1);
            int p2Team = findWithCompact(p2);

            if (p1Team == p2Team) {
                System.out.println("0");
                System.exit(0);
            }

            if (opponent[p1] != 0) {
                int p1Opponent = opponent[p1];
                union(p1Opponent, p2);
            }
            if (opponent[p2] != 0) {
                int p2Opponent = opponent[p2];
                union(p2Opponent, p1);
            }

            opponent[p1] = findWithCompact(p2);
            opponent[p2] = findWithCompact(p1);
        }

        System.out.println("1");
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

class MatchLog {

    int p1;
    int p2;

    public MatchLog(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}