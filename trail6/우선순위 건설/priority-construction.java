import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<BuildInfo> buildInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = toInt(st);
            List<Integer> prevBuildingNumbers = new ArrayList<>();
            int number = toInt(st);
            while (number != -1) {
                prevBuildingNumbers.add(number);
                number = toInt(st);
            }
            buildInfos.add(new BuildInfo(i + 1, time, prevBuildingNumbers));
        }

        new Solver(N, buildInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endBuildingNumber;
    int[] dp;
    int[] indegree;
    List<List<Node>> graph = new ArrayList<>();
    List<BuildInfo> buildInfos;

    public Solver(int endBuildingNumber, List<BuildInfo> buildInfos) {
        this.endBuildingNumber = endBuildingNumber;
        this.indegree = new int[endBuildingNumber + 1];
        this.dp = new int[endBuildingNumber + 1];
        this.buildInfos = buildInfos;
    }

    public void solve() {
        for (int i = 0; i <= endBuildingNumber; i++) {
            graph.add(new ArrayList<>());
        }
        for (BuildInfo buildInfo : buildInfos) {
            for (int prevNumber : buildInfo.prevBuildingNumbers) {
                indegree[buildInfo.number]++;
                graph.get(prevNumber).add(new Node(buildInfo.number, buildInfo.time));
            }
            dp[buildInfo.number] = buildInfo.time;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= endBuildingNumber; v++) {
            if (indegree[v] == 0) {
                q.add(v);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Node> nears = graph.get(cur);
            for (Node near : nears) {
                dp[near.number] = Math.max(dp[cur] + near.weight, dp[near.number]);
                indegree[near.number]--;
                if (indegree[near.number] == 0) {
                    q.add(near.number);
                }
            }
        }

        for (int v = 1; v <= endBuildingNumber; v++) {
            System.out.println(dp[v]);
        }
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

class BuildInfo {

    int number;
    int time;
    List<Integer> prevBuildingNumbers = new ArrayList<>();

    public BuildInfo(int number, int time, List<Integer> prevBuildingNumbers) {
        this.number = number;
        this.time = time;
        this.prevBuildingNumbers = prevBuildingNumbers;
    }
}