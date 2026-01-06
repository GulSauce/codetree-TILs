import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        if (!st.hasMoreTokens()) return;
        int n = Integer.parseInt(st.nextToken());
        
        // 간선 정보 저장
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            String line = br.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        new Solver(n, edges).solve();
    }
}

class Solver {
    int nodeCount;
    // 오버플로우 방지를 위한 적당히 큰 값 (최대 N=10만이므로 1억이면 충분)
    final int INF = 100000000; 

    // 상태 정의 (작성하신 코드 기준)
    final int CHILD_COVERED = 0; // 나는 OFF, 자식이 커버해줌
    final int SELECTED = 1;      // 나는 ON (내가 선택됨)
    final int PARENT_COVERED = 2;// 나는 OFF, 부모가 커버해줌

    int[][] dp;
    List<List<Integer>> graph = new ArrayList<>();

    public Solver(int nodeCount, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.dp = new int[nodeCount + 1][3];
        
        // 그래프 초기화
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.start).add(edge.end);
            graph.get(edge.end).add(edge.start);
        }
    }

    public void solve() {
        final int ROOT_NUMBER = 1;
        
        // 루트는 부모가 없으므로 -1을 부모로 넘김
        DPDFS(ROOT_NUMBER, -1);

        // 루트는 부모가 없으므로 PARENT_COVERED(2) 상태 불가능
        int answer = Math.min(dp[ROOT_NUMBER][SELECTED], dp[ROOT_NUMBER][CHILD_COVERED]);
        System.out.println(answer);
    }

    // visited 배열 대신 parent를 인자로 받아 역류 방지 (더 안전함)
    private void DPDFS(int cur, int parent) {
        
        // 1. 초기값 설정
        dp[cur][SELECTED] = 1; // 선택되면 비용 1 (가중치가 있다면 nodeValue)
        dp[cur][CHILD_COVERED] = 0;
        dp[cur][PARENT_COVERED] = 0;

        boolean isLeaf = true;
        
        // State 1(CHILD_COVERED) 계산을 위한 변수
        boolean isAnyChildSelected = false; // 자연스럽게 선택된 자식이 있는지
        int minDiff = INF; // 강제로 바꿀 때 최소 비용 차이

        for (int child : graph.get(cur)) {
            if (child == parent) continue;
            isLeaf = false;
            
            DPDFS(child, cur);

            // [Logic 1] 내가 선택됨 (SELECTED)
            // 자식은 제약 없음 (0, 1, 2 중 최소)
            int valSelected = Math.min(dp[child][PARENT_COVERED],
                    Math.min(dp[child][SELECTED], dp[child][CHILD_COVERED]));
            
            if(dp[cur][SELECTED] != INF) { // 누적 (INF면 유지)
                 if(valSelected >= INF) dp[cur][SELECTED] = INF;
                 else dp[cur][SELECTED] += valSelected;
            }

            // [Logic 2] 내가 부모에게 커버됨 (PARENT_COVERED)
            // 나는 OFF. 자식은 나한테 기댈 수 없음.
            // 자식은 "선택됨(SELECTED)" 혹은 "자기가 알아서 해결(CHILD_COVERED)" 이어야 함.
            // (주의: 일반 지배 집합에서는 자식이 SELECTED여도 됩니다!)
            int valParentCovered = Math.min(dp[child][SELECTED], dp[child][CHILD_COVERED]);
            
            if(dp[cur][PARENT_COVERED] != INF) {
                if(valParentCovered >= INF) dp[cur][PARENT_COVERED] = INF;
                else dp[cur][PARENT_COVERED] += valParentCovered;
            }

            // [Logic 3] 내가 자식에게 커버됨 (CHILD_COVERED) - 일단 누적
            // 자식은 0 또는 1이어야 함 (2 불가)
            int valChildCovered = Math.min(dp[child][SELECTED], dp[child][CHILD_COVERED]);
            
            if(dp[cur][CHILD_COVERED] != INF) {
                if(valChildCovered >= INF) dp[cur][CHILD_COVERED] = INF;
                else dp[cur][CHILD_COVERED] += valChildCovered;
            }

            // 강제 변환(State 1)을 위한 체크
            if (dp[child][SELECTED] <= dp[child][CHILD_COVERED]) {
                isAnyChildSelected = true; // 자식이 선택되는 게 이득이라 자연스럽게 선택됨
            } else {
                // 자식을 억지로 선택하게 만들었을 때의 손해 비용 계산
                // INF가 아닐 때만 계산
                if(dp[child][SELECTED] < INF) {
                    minDiff = Math.min(minDiff, dp[child][SELECTED] - dp[child][CHILD_COVERED]);
                }
            }
        }

        // [Logic 3 후처리] 리프 노드거나, 자식을 강제로 선택해야 하는 경우
        if (isLeaf) {
            dp[cur][CHILD_COVERED] = INF; // 자식이 없으니 불가능
        } else {
            // 아무 자식도 선택되지 않았다면, 가장 손해가 적은 자식 하나 강제 선택
            if (!isAnyChildSelected) {
                if (minDiff >= INF) {
                    dp[cur][CHILD_COVERED] = INF; // 바꿀 수 있는 자식이 없음
                } else {
                    // 이미 불가능(INF) 상태가 아니라면 추가 비용 더하기
                    if (dp[cur][CHILD_COVERED] < INF) {
                        dp[cur][CHILD_COVERED] += minDiff;
                    }
                }
            }
        }
    }
}

class Edge {
    int start, end;
    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}