import java.util.*;

public class Main {
    private static class Solver{
        int visitedNodeCount = 0;

        List<List<Integer>> graph;
        boolean[] isVisited;

        public Solver(
                List<List<Integer>> graph
        ){
            this.graph = graph;
            this.isVisited = new boolean[graph.size()];
        }

        public void solve(){
            initIsVisited();
            dfs(1);
            System.out.println(visitedNodeCount);
        }

        private void dfs(int currentNode){
            for(int nextNode: graph.get(currentNode)){
                if(isVisited[nextNode]){
                    continue;
                }
                if(nextNode != 1){
                    visitedNodeCount++;
                }
                isVisited[nextNode] = true;
                dfs(nextNode);
            }
        }

        private void initIsVisited(){
            Arrays.fill(isVisited, false);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 1; i <= M; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        new Solver(graph).solve();
    }
}