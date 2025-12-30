import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, Q;
        List<Integer> queries = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        Q = toInt(st);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            queries.add(toInt(st));
        }

        new Solver(N, queries).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    boolean[] visited;
    int nodeCount;
    List<Integer> queries;

    public Solver(int nodeCount, List<Integer> queries) {
        this.nodeCount = nodeCount;
        this.queries = queries;
        this.visited = new boolean[nodeCount + 1];
    }


    int blockNumber;

    public void solve() {
        final int NO_BLOCK = 0;
        for (Integer query : queries) {
            blockNumber = NO_BLOCK;
            toRootSetBlockNumberDFS(query);

            System.out.println(blockNumber);
            if (blockNumber == NO_BLOCK) {
                visited[query] = true;
            }
        }
    }

    private void toRootSetBlockNumberDFS(int cur) {
        if (cur == 1) {
            return;
        }
        if (visited[cur]) {
            blockNumber = cur;
        }
        toRootSetBlockNumberDFS(cur / 2);
    }
}