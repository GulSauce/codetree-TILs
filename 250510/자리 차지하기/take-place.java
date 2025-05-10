import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int N, M;
        HashMap<Integer, Integer> personNumberMaxSeatableChairNumberMapper = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            personNumberMaxSeatableChairNumberMapper.put(i + 1, sc.nextInt());
        }
        sc.close();

        new Solver(M, personNumberMaxSeatableChairNumberMapper).solve();
    }
}

class Solver {
    int chairCount;
    TreeSet<Integer> treeSet = new TreeSet<>();
    HashMap<Integer, Integer> personNumberMaxSeatableChairNumberMapper;

    public Solver(
            int chairCount,
            HashMap<Integer, Integer> personNumberMaxSeatableChairNumberMapper
    ) {
        this.chairCount = chairCount;
        this.personNumberMaxSeatableChairNumberMapper = personNumberMaxSeatableChairNumberMapper;
    }

    public void solve() {
        for (int i = 1; i <= chairCount; i++) {
            treeSet.add(i);
        }

        int answer = 0;
        for (Map.Entry<Integer, Integer> entry : personNumberMaxSeatableChairNumberMapper.entrySet()) {
            int maxSeatableChairNumber = entry.getValue();
            Integer chairNumber = treeSet.floor(maxSeatableChairNumber);
            if (chairNumber == null) {
                break;
            }
            treeSet.remove(chairNumber);
            answer++;
        }
        System.out.println(answer);
    }
}