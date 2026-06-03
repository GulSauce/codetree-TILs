import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<JobInfo> jobInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = toInt(st);
            List<Integer> preJobs = new ArrayList<>();

            int preCount = toInt(st);
            for (int j = 0; j < preCount; j++) {
                preJobs.add(toInt(st));
            }

            jobInfos.add(new JobInfo(i, time, preJobs));
        }

        new Solver(N, jobInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int jobCount;
    int[] dp;
    int[] indegree;

    HashMap<Integer, JobInfo> jobInfoHashMap = new HashMap<>();
    List<JobInfo> jobInfos;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int jobCount, List<JobInfo> jobInfos) {
        this.jobCount = jobCount;
        this.dp = new int[jobCount + 1];
        this.indegree = new int[jobCount + 1];
        this.jobInfos = jobInfos;
    }

    public void solve() {
        for (int i = 0; i <= jobCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (JobInfo jobInfo : jobInfos) {
            jobInfoHashMap.put(jobInfo.number, jobInfo);
            for (int preJobNumber : jobInfo.preJobs) {
                indegree[jobInfo.number]++;
                graph.get(preJobNumber).add(jobInfo.number);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int v = 1; v <= jobCount; v++) {
            if (indegree[v] == 0) {
                q.add(v);
                dp[v] = jobInfoHashMap.get(v).time;
            }
        }

        int answer = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                dp[near] = Math.max(dp[cur] + jobInfoHashMap.get(near).time, dp[near]);
                answer = Math.max(answer, dp[near]);
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }

        System.out.println(answer);
    }
}

class JobInfo {

    int number;
    int time;
    List<Integer> preJobs;

    public JobInfo(int number, int time, List<Integer> preJobs) {
        this.number = number;
        this.time = time;
        this.preJobs = preJobs;
    }
}