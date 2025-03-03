import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int start;
        int TARGET = 1;
        final int NOT_ALLOCATED = Integer.MAX_VALUE;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[3000001];
        int[] dist = new int[3000001];

        public Solver(
            int N
        ) {
            this.start = N;
        }

        public void solve() {
            initDist();
            bfs();
            printAnswer();
        }

        private void printAnswer() {
            System.out.println(dist[TARGET]);
        }

        private void bfs() {
            visited[start] = true;
            dist[start] = 0;
            q.add(start);
            while (!q.isEmpty()) {
                int cur = q.poll();
                tryBfs(cur + 1, cur);
                tryBfs(cur - 1, cur);
                if (cur % 2 == 0) {
                    tryBfs(cur / 2, cur);
                }
                if (cur % 3 == 0) {
                    tryBfs(cur / 3, cur);
                }
            }
        }

        private void tryBfs(int num, int origin) {
            if (isOutOfRange(num)) {
                return;
            }
            if (dist[num] != NOT_ALLOCATED) {
                return;
            }
            if (visited[num]) {
                return;
            }
            visited[num] = true;
            dist[num] = dist[origin] + 1;
            q.add(num);
        }

        private boolean isOutOfRange(int num) {
            return num < 0 || 300000 < num;
        }

        private void initDist() {
            Arrays.fill(dist, NOT_ALLOCATED);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        new Solver(N).solve();
    }
}