import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<GraphMakeInfo> graphMakeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graphMakeInfos.add(new GraphMakeInfo(toInt(st), toInt(st)));
        }

        new Solver(N, graphMakeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int nodeCount;
    List<GraphMakeInfo> graphMakeInfos;

    public Solver(int nodeCount, List<GraphMakeInfo> graphMakeInfos) {
        this.nodeCount = nodeCount;
        this.graphMakeInfos = graphMakeInfos;
    }

    public void solve() {
        boolean[][] edgeExist = new boolean[nodeCount + 1][nodeCount + 1];
        for (int v = 1; v <= nodeCount; v++) {
            edgeExist[v][v] = true;
        }
        for (GraphMakeInfo graphMakeInfo : graphMakeInfos) {
            edgeExist[graphMakeInfo.start][graphMakeInfo.end] = true;
        }
        for (int k = 1; k <= nodeCount; k++) {
            for (int i = 1; i <= nodeCount; i++) {
                for (int j = 1; j <= nodeCount; j++) {
                    if (edgeExist[i][k] == false || edgeExist[k][j] == false) {
                        continue;
                    }
                    edgeExist[i][j] = true;
                }
            }
        }
        for (int v = 1; v <= nodeCount; v++) {
            int notMatch = 0;
            for (int other = 1; other <= nodeCount; other++) {
                if (!edgeExist[v][other] && !edgeExist[other][v]) {
                    notMatch++;
                }
            }
            System.out.println(notMatch);
        }
    }
}

class GraphMakeInfo {

    int start;
    int end;

    public GraphMakeInfo(int start, int end) {
        this.start = start;
        this.end = end;
    }
}