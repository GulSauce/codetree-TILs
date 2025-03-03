import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int start;
        int TARGET = 1;
        final int NOT_ALLOCATED = Integer.MAX_VALUE;
        final int MAX_INDEX = 3000001;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[MAX_INDEX];
        int[] dist = new int[MAX_INDEX];

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
            return num < 0 || MAX_INDEX <= num;
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