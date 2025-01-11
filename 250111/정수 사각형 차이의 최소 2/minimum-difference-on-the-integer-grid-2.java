import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] grid;
    static ArrayList<Integer> vals;  // 중복 제거 후 정렬할 모든 숫자 목록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];

        // 격자 입력 받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 숫자 수집 후 중복 제거 및 정렬
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                set.add(grid[i][j]);
            }
        }
        vals = new ArrayList<>(set);
        // 이미 TreeSet이라 정렬은 되어 있지만, 명시적으로 정렬
        Collections.sort(vals);

        // 최소 차이를 찾기 위한 두 포인터
        int i = 0, j = 0;
        int m = vals.size();
        int minDiff = Integer.MAX_VALUE;

        // i, j가 모두 m 미만에서 탐색
        while (i < m && j < m) {
            // 현재 범위로 (0,0)에서 (n-1,n-1)로 갈 수 있는지 체크
            if (canTravel(vals.get(i), vals.get(j))) {
                // 갈 수 있으면 차이 계산 후 갱신
                int diff = vals.get(j) - vals.get(i);
                if (diff < minDiff) {
                    minDiff = diff;
                }
                // 더 작은 차이를 위해 i를 늘려 범위를 좁힘
                i++;
            } else {
                // 갈 수 없으면 j를 늘려 범위를 넓힘
                j++;
            }
        }

        System.out.println(minDiff);
    }

    // BFS를 통해 [low, high] 범위로만 이동 가능한지 확인
    static boolean canTravel(int low, int high) {
        // 시작점이 범위에 안 들어가면 불가능
        if (grid[0][0] < low || grid[0][0] > high) {
            return false;
        }
        // 도착점이 범위에 안 들어가면 아무리 가도 의미가 없으므로
        if (grid[n-1][n-1] < low || grid[n-1][n-1] > high) {
            return false;
        }

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        // 시작점 추가
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        // 방향 정의 (오른쪽, 아래)
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            // 도착점이면 true
            if (x == n - 1 && y == n - 1) {
                return true;
            }

            for (int k = 0; k < 2; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny]) {
                        int val = grid[nx][ny];
                        if (val >= low && val <= high) {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return false;
    }
}