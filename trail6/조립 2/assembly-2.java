import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Recipe> recipes = new ArrayList<>();
        List<Integer> preparedMatters = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int target = toInt(st);
            int count = toInt(st);
            st = new StringTokenizer(br.readLine());
            List<Integer> matters = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                matters.add(toInt(st));
            }
            recipes.add(new Recipe(target, matters));
        }
        st = new StringTokenizer(br.readLine());
        int count = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            preparedMatters.add(toInt(st));
        }

        new Solver(N, recipes, preparedMatters).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int pieceCount;
    int[] indegree;
    boolean[] visited;
    List<Recipe> recipes;
    List<Integer> preparedMatters;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int pieceCount, List<Recipe> recipes, List<Integer> preparedMatters) {
        this.pieceCount = pieceCount;
        this.indegree = new int[pieceCount + 1];
        this.visited = new boolean[pieceCount + 1];
        this.recipes = recipes;
        this.preparedMatters = preparedMatters;
    }

    public void solve() {
        for (int v = 0; v <= pieceCount; v++) {
            graph.add(new ArrayList<>());
        }
        for (Recipe recipe : recipes) {
            int child = recipe.target;
            for (int parents : recipe.matters) {
                graph.get(parents).add(child);
                indegree[child]++;
            }
        }

        List<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int matter : preparedMatters) {
            q.add(matter);
            visited[matter] = true;
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            answer.add(cur);
            visited[cur] = true;

            List<Integer> nears = graph.get(cur);
            for (int near : nears) {
                if (visited[near]) {
                    continue;
                }
                indegree[near]--;
                if (indegree[near] == 0) {
                    q.add(near);
                }
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for (int value : answer) {
            System.out.print(value + " ");
        }
    }
}

class Recipe {

    int target;
    List<Integer> matters;

    public Recipe(int target, List<Integer> matters) {
        this.target = target;
        this.matters = matters;
    }
}