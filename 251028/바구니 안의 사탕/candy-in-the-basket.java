import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, K;
        List<CandyInfo> candyInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            candyInfos.add(new CandyInfo(st.nextToken(), st.nextToken()));
        }

        new Solver(K, candyInfos).solve();
    }

}

class CandyInfo implements Comparable<CandyInfo> {

    int pos;
    int count;

    public CandyInfo(String count, String pos) {
        this.pos = Integer.parseInt(pos);
        this.count = Integer.parseInt(count);
    }

    public CandyInfo(Integer count, Integer pos) {
        this.pos = pos;
        this.count = count;
    }

    @Override
    public int compareTo(CandyInfo other) {
        return Integer.compare(this.pos, other.pos);
    }
}

class Solver {

    final int MAX_DIST;
    List<CandyInfo> candyInfos;
    HashMap<Integer, Integer> candySumMap = new HashMap<>();

    public Solver(
        int K,
        List<CandyInfo> candyInfos
    ) {
        this.candyInfos = candyInfos;
        this.MAX_DIST = 2 * K;
    }

    public void solve() {
        for (CandyInfo candyInfo : candyInfos) {
            int prev = candySumMap.getOrDefault(candyInfo.pos, 0);
            candySumMap.put(candyInfo.pos, prev + candyInfo.count);
        }
        ArrayList<CandyInfo> sortedCandyInfos = new ArrayList<>();
        for (Entry<Integer, Integer> entry : candySumMap.entrySet()) {
            sortedCandyInfos.add(new CandyInfo(entry.getValue(), entry.getKey()));
        }
        Collections.sort(sortedCandyInfos);

        int i = 0;
        int j = 0;
        int answer = 0;
        int candySum = sortedCandyInfos.get(i).count;
        answer = Math.max(answer, candySum);
        for (i = 0; i < sortedCandyInfos.size(); i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (sortedCandyInfos.get(i).pos + MAX_DIST < sortedCandyInfos.get(
                    j).pos) {
                    break;
                }
                if (sortedCandyInfos.get(j).pos <= sortedCandyInfos.get(i).pos + MAX_DIST) {
                    answer = Math.max(answer, candySum);
                    j++;
                    if (sortedCandyInfos.size() <= j) {
                        break;
                    }
                    candySum += sortedCandyInfos.get(j).count;
                }
            }
            candySum -= sortedCandyInfos.get(i).count;
        }
        System.out.println(answer);
    }
}