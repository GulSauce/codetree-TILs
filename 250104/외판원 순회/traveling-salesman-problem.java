import java.util.*;

public class Main {
    private static class Solver{
        int answer = Integer.MAX_VALUE;
        int graphLength;
        int maxNodeNumber;

        final int CAN_NOT_MOVE = 0;

        int[][] graph;

        boolean[] isVisited;

        public Solver(
                int[][] graph
        ){
            this.graph = graph;
            this.graphLength = graph.length;
            this.maxNodeNumber = graph.length-1;
            this.isVisited = new boolean[graphLength];
        }

        public void solve(){
            initIsVisited();
            getPermutation(0, 0,1);
            System.out.println(answer);
        }

        private void getPermutation(int prevRepeat, int prevSum, int prevNodeNumber){
            if(prevRepeat == graphLength-2){
                int curSum = prevSum + graph[prevNodeNumber][1];
                answer = Math.min(answer, curSum);
            }

            for(int curNodeNumber = 2; curNodeNumber <= maxNodeNumber; curNodeNumber++){
                if(isVisited[curNodeNumber]){
                    continue;
                }
                if(graph[prevNodeNumber][curNodeNumber] == CAN_NOT_MOVE){
                    continue;
                }
                isVisited[curNodeNumber] = true;
                getPermutation(prevRepeat+1, prevSum+graph[prevNodeNumber][curNodeNumber], curNodeNumber);
                isVisited[curNodeNumber] = false;
            }
        }

        private void initIsVisited(){
            Arrays.fill(isVisited, false);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] graph = new int[n+1][n+1];
        for(int source = 1; source <= n; source++){
            for(int dest = 1; dest <= n; dest++){
                graph[source][dest] = sc.nextInt();
            }
        }

        new Solver(graph).solve();
    }
}