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
        int N, K;
        List<Integer> graphInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        K = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            graphInfos.add(toInt(st));
        }

        new Solver(K, graphInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    HashMap<Integer, Integer> parentHashMap = new HashMap<>();
    int targetNode;
    List<Integer> graphInfos;

    public Solver(int targetNode, List<Integer> graphInfos) {
        this.targetNode = targetNode;
        this.graphInfos = graphInfos;
    }

    public void solve() {

        if (graphInfos.size() == 1) {
            System.out.println(0);
            return;
        }

        int i = 0;
        int j = 1;
        for (; i < graphInfos.size(); i++) {
            while (true) {
                if (graphInfos.size() <= j) {
                    break;
                }
                int cur = graphInfos.get(j);
                parentHashMap.put(cur, graphInfos.get(i));
                j++;
                if (graphInfos.size() <= j) {
                    break;
                }
                if (graphInfos.get(j) != graphInfos.get(j - 1) + 1) {
                    break;
                }
            }
        }

        Integer parentNumber = parentHashMap.get(targetNode);
        Integer grandParentNumber = parentHashMap.get(parentNumber);
        if (grandParentNumber == null) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        int targetParent = parentHashMap.get(targetNode);
        int targetGrandParent = parentHashMap.get(targetParent);
        for (Entry<Integer, Integer> entry : parentHashMap.entrySet()) {
            if (entry.getKey().equals(targetNode)) {
                continue;
            }
            Integer parent = entry.getValue();
            Integer grandParent = parentHashMap.get(parent);
            if(grandParent == null){
                continue;
            }
            if (!parent.equals(targetParent) && grandParent.equals(targetGrandParent)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}