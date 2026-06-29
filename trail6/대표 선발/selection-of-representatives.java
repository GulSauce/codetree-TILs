import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        List<List<Integer>> groupInfoList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = toInt(st);

        for (int i = 0; i < n; i++) {
            groupInfoList.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int memberCount = toInt(st);
            for (int j = 0; j < memberCount; j++) {
                groupInfoList.get(i).add(toInt(st));
            }
        }
        new Solver(groupInfoList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int FULL;
    final int R = 10_007;
    int[] dp;

    List<List<Integer>> groupInfoList;
    HashMap<Integer, List<Integer>> memberJoinedGroupHashMap = new HashMap<>();

    public Solver(List<List<Integer>> groupInfoList) {
        this.FULL = (1 << groupInfoList.size()) - 1;
        this.groupInfoList = groupInfoList;
        this.dp = new int[FULL + 1];
    }

    public void solve() {
        setMemberJoinedGroupHashMap();

        dp[0] = 1;
        for (Entry<Integer, List<Integer>> memberJoinedGroup : memberJoinedGroupHashMap.entrySet()) {
            for (int bitmask = FULL; bitmask >= 0; bitmask--) {
                List<Integer> joinedGroupNoList = memberJoinedGroup.getValue();
                for (int joinedGroupNo : joinedGroupNoList) {
                    // 그 그룹 대표가 없음
                    if ((bitmask >> joinedGroupNo & 1) == 0) {
                        // 내가 대표가 됨
                        dp[bitmask | 1 << joinedGroupNo] += dp[bitmask];
                        dp[bitmask | 1 << joinedGroupNo] %= R;
                    }
                }
            }
        }
        System.out.println(dp[FULL]);
    }

    private void setMemberJoinedGroupHashMap() {
        for (int i = 0; i < groupInfoList.size(); i++) {
            for (int member : groupInfoList.get(i)) {
                int finalI = i;
                memberJoinedGroupHashMap
                    .compute(member, (k, v) -> {
                        if (v == null) {
                            v = new ArrayList<>();
                            v.add(finalI);
                        } else {
                            v.add(finalI);
                        }
                        return v;
                    });
            }
        }
    }
}