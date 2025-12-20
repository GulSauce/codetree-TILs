import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int A, B, N;
        int cost, count;
        List<Integer> nodes;
        List<BusInfo> busInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = toInt(st);
        B = toInt(st);
        N = toInt(st);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost = toInt(st);
            count = toInt(st);
            st = new StringTokenizer(br.readLine());
            List<Integer> curNodes = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                curNodes.add(toInt(st));
            }
            nodes = curNodes;
            busInfos.add(new BusInfo(cost, nodes));
        }

        new Solver(A, B, busInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NODE_COUNT = 1000;
    int start;
    int end;
    List<BusInfo> busInfos;

    public Solver(int start, int end, List<BusInfo> busInfos) {
        this.start = start;
        this.end = end;
        this.busInfos = busInfos;
    }

    public void solve() {
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        Weight[][] graph = new Weight[NODE_COUNT + 1][NODE_COUNT + 1];
        for (Weight[] array : graph) {
            Arrays.fill(array, new Weight());
        }
        for (BusInfo busInfo : busInfos) {
            List<Integer> sequentiallyVisitNodes = busInfo.sequentiallyVisitNodes;
            for (int i = 0; i < sequentiallyVisitNodes.size(); i++) {
                for (int j = i + 1; j < sequentiallyVisitNodes.size(); j++) {
                    Weight candidateWeight = new Weight(busInfo.cost, j - i);
                    int start = sequentiallyVisitNodes.get(i);
                    int end = sequentiallyVisitNodes.get(j);
                    if (graph[start][end].isCheaperOrSameThan(candidateWeight)) {
                        continue;
                    }
                    graph[start][end] = candidateWeight;
                }
            }
        }

        Weight[] dist = new Weight[NODE_COUNT + 1];
        Arrays.fill(dist, new Weight());
        boolean[] visited = new boolean[NODE_COUNT + 1];
        Arrays.fill(dist, new Weight());

        dist[start] = new Weight(0, 0);

        for (int i = 0; i < 1000; i++) {
            Node cur = new Node(-1, new Weight());
            for (int v = 1; v <= 1000; v++) {
                if (cur.weight.isCheaperOrSameThan(dist[v])) {
                    continue;
                }
                if (visited[v]) {
                    continue;
                }
                cur = new Node(v, dist[v]);
            }

            if (cur.number == -1) {
                break;
            }

            visited[cur.number] = true;

            for (int v = 1; v <= NODE_COUNT; v++) {
                if (graph[cur.number][v].isNotExist()) {
                    continue;
                }
                Weight nextWeight = Weight.sum(dist[cur.number], graph[cur.number][v]);
                if (dist[v].isCheaperOrSameThan(nextWeight)) {
                    continue;
                }
                dist[v] = nextWeight;
            }
        }
        if (dist[end].isNotExist()) {
            System.out.println(-1 + " " + -1);
        } else {
            System.out.println(dist[end].cost + " " + dist[end].time);
        }
    }
}

class Node {

    int number;
    Weight weight;

    public Node(int number, Weight weight) {
        this.number = number;
        this.weight = weight;
    }
}

class Weight {

    private static final int NOT_ALLOCATED = Integer.MAX_VALUE;

    int cost;
    int time;

    public Weight() {
        this.cost = NOT_ALLOCATED;
        this.time = NOT_ALLOCATED;
    }

    public Weight(int cost, int time) {
        this.cost = cost;
        this.time = time;
    }

    public static Weight sum(Weight a, Weight b) {
        return new Weight(a.cost + b.cost, a.time + b.time);
    }

    public boolean isNotExist() {
        return cost == NOT_ALLOCATED && time == NOT_ALLOCATED;
    }

    public boolean isCheaperOrSameThan(Weight other) {
        if (cost == other.cost && time == other.time) {
            return true;
        }
        if (cost == other.cost) {
            return Integer.compare(time, other.time) == -1 ? true : false;
        }
        return Integer.compare(cost, other.cost) == -1 ? true : false;
    }
}

class BusInfo {

    int cost;
    List<Integer> sequentiallyVisitNodes;

    public BusInfo(int cost, List<Integer> sequentiallyVisitNodes) {
        this.cost = cost;
        this.sequentiallyVisitNodes = sequentiallyVisitNodes;
    }
}