import java.util.*;

public class Main {
    private static class Solver{
        final int MAX_NUMBER = 2000000;
        int start;

        boolean[] visited = new boolean[MAX_NUMBER+1];
        int[] dist = new int[MAX_NUMBER+1];

        public Solver(
                int N
        ){
            this.start = N;
        }

        public void solve(){
            initVisited();
            bfs();
            printAnswer();
        }

        private void printAnswer(){
            System.out.println(dist[1]);
        }

        private void initVisited(){
            Arrays.fill(visited, false);
        }

        private void bfs(){
            Queue<Integer> q = new LinkedList<>();
            visited[start] = true;
            dist[start] = 0;
            q.add(start);
            while (!q.isEmpty()){
                boolean found = false;
                int prev = q.poll();
                List<Integer> curNumbers = getCurNumbers(prev);
                for(int curNumber: curNumbers){
                    if(isOutOfRange(curNumber)){
                        continue;
                    }
                    if(isVisited(curNumber)){
                        continue;
                    }

                    visited[curNumber] = true;
                    dist[curNumber] = dist[prev] + 1;
                    q.add(curNumber);
                    if(curNumber == 1){
                        found = true;
                    }
                }
                if(found){
                    break;
                }
            }
        }

        private boolean isOutOfRange(int number){
            return number < 0 || MAX_NUMBER < number;
        }

        private boolean isVisited(int number){
            return visited[number];
        }

        private List<Integer> getCurNumbers(int prev) {
            List<Integer> curNumbers = new ArrayList<>();
            curNumbers.add(prev + 1);
            curNumbers.add(prev - 1);
            if (prev % 2 == 0) {
                curNumbers.add(prev / 2);
            }
            if (prev % 3 == 0) {
                curNumbers.add(prev / 3);
            }
            return curNumbers;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        new Solver(N).solve();
    }
}