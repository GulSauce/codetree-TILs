import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);

        grid = new int[N + 1][M + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                grid[row][col] = toInt(st);
            }
        }

        new Solver(N, M, grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    boolean[] inMST;

    int nodeCount;
    int rowLength;
    int colLength;
    final int CITY = 1;
    final int NOT_ALLOCATED = Integer.MAX_VALUE;

    int[] dist;
    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};

    int[][] grid;
    List<List<Node>> graph = new ArrayList<>();


    public Solver(int rowLength, int colLength, int[][] grid) {
        this.nodeCount = rowLength * colLength;
        this.inMST = new boolean[nodeCount + 1];
        this.dist = new int[nodeCount + 1];
        this.rowLength = rowLength;
        this.colLength = colLength;
        this.grid = grid;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int row = 1; row <= rowLength; row++) {
            for (int col = 1; col <= colLength; col++) {
                if (grid[row][col] != CITY) {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int y = row;
                    int x = col;
                    int dist = 1;
                    while (true) {
                        y = y - dy[i];
                        x = x - dx[i];
                        if (isOutOfGrid(y, x)) {
                            break;
                        }
                        if (grid[y][x] == CITY) {
                            graph.get(toNumber(row, col)).add(new Node(toNumber(y, x), dist - 1));
                        }
                        dist++;
                    }
                }
            }
        }

        int start = -1;
        for (int row = 1; row <= rowLength; row++) {
            for (int col = 1; col <= colLength; col++) {
                if (grid[row][col] == CITY) {
                    start = toNumber(row, col);
                }
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        q.add(new Node(start, 0));
        Arrays.fill(dist, NOT_ALLOCATED);
        dist[start] = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (inMST[cur.number]) {
                continue;
            }
            inMST[cur.number] = true;
            List<Node> nears = graph.get(cur.number);
            for (Node near : nears) {
                if (inMST[near.number]) {
                    continue;
                }
                int curDist = near.weight;
                if (dist[near.number] < curDist) {
                    continue;
                }
                dist[near.number] = curDist;
                q.add(new Node(near.number, curDist));
            }
        }

        int answer = 0;
        for (int row = 1; row <= rowLength; row++) {
            for (int col = 1; col <= colLength; col++) {
                if (grid[row][col] == CITY) {
                    int curDist = dist[toNumber(row, col)];
                    if (curDist == NOT_ALLOCATED) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                    answer += curDist;
                }
            }
        }

        System.out.println(answer);
    }

    private boolean isOutOfGrid(int row, int col) {
        return row < 1 || rowLength < row || col < 1 || colLength < col;
    }

    private int toNumber(int row, int col) {
        return (row - 1) * colLength + col;
    }
}

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}