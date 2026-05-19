import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        int[][] grid;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        grid = new int[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                grid[row][col] = toInt(st);
            }
        }
        new Solver(N, grid).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int ACTIVATE = 1;
    final int DEACTIVATE = 2;
    int gridLength;

    List<Edge> edgeCoordinates = new ArrayList<>();


    int[] rootOf;
    int[][] grid;
    boolean[][] visited;

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public Solver(int N, int[][] grid) {
        this.rootOf = new int[2501];
        this.gridLength = N;
        this.grid = grid;
        this.visited = new boolean[N + 1][N + 1];
    }

    public void solve() {

        int shelterCount = 0;
        for (int row = 1; row <= gridLength; row++) {
            for (int col = 1; col <= gridLength; col++) {
                if (isShelter(row, col)) {
                    shelterCount++;
                    starBFS(row, col);
                }
            }
        }
        for (int v = 1; v <= gridLength * gridLength; v++) {
            rootOf[v] = v;
        }
        edgeCoordinates.sort((a, b) -> Integer.compare(a.w, b.w));

        int answer = 0;
        Set<Integer> connectedShelterSet = new HashSet<>();
        for (Edge edge : edgeCoordinates) {
            if (findWithCompact(edge.v1) == findWithCompact(edge.v2)) {
                continue;
            }
            union(edge.v1, edge.v2);
            connectedShelterSet.add(edge.v1);
            connectedShelterSet.add(edge.v2);
            answer += edge.w;
        }

        if (connectedShelterSet.size() != shelterCount) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }


    private void starBFS(int sY, int sX) {
        for (boolean[] array : visited) {
            Arrays.fill(array, false);
        }
        Queue<BFSInfo> bfsInfos = new LinkedList<>();
        visited[sY][sX] = true;
        bfsInfos.add(new BFSInfo(sX, sY, 0));

        while (!bfsInfos.isEmpty()) {
            BFSInfo bfsInfo = bfsInfos.poll();
            for (int i = 0; i < 4; i++) {
                int nX = bfsInfo.x + dx[i];
                int nY = bfsInfo.y + dy[i];
                if (nX < 1 || nY < 1 || nX > gridLength || nY > gridLength) {
                    continue;
                }
                if (visited[nY][nX] || grid[nY][nX] == -1) {
                    continue;
                }

                visited[nY][nX] = true;
                bfsInfos.add(new BFSInfo(nX, nY, bfsInfo.curLength + 1));

                if (isShelter(nY, nX)) {
                    edgeCoordinates.add(new Edge(
                        rowColToNumber(sY, sX), rowColToNumber(nY, nX), bfsInfo.curLength + 1));
                }
            }
        }
    }

    private void union(int v1, int v2) {
        int left = findWithCompact(v1);
        int right = findWithCompact(v2);

        rootOf[left] = right;
    }

    private int findWithCompact(int cur) {
        int parent = rootOf[cur];
        if (parent == cur) {
            return cur;
        }
        rootOf[cur] = findWithCompact(parent);
        return rootOf[cur];
    }

    private boolean isShelter(int row, int col) {
        return grid[row][col] == ACTIVATE || grid[row][col] == DEACTIVATE;
    }

    private int rowColToNumber(int row, int col) {
        return (row - 1) * gridLength + col;
    }
}

class BFSInfo {

    int x;
    int y;
    int curLength;

    public BFSInfo(int x, int y, int curLength) {
        this.x = x;
        this.y = y;
        this.curLength = curLength;
    }

    public void increaseLength() {
        this.curLength += 1;
    }
}

class Edge {

    int v1;
    int v2;
    int w;

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}