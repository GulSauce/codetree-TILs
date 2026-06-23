import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<List<Integer>> groupList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i <= N; i++) {
            groupList.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int peopleCount = toInt(st);
            for (int j = 0; j < peopleCount; j++) {
                groupList.get(i).add(toInt(st));
            }
        }

        new Solver(groupList).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<List<Integer>> groupList;
    List<Integer> groupBitList = new ArrayList<>();

    public Solver(List<List<Integer>> groupList) {
        this.groupList = groupList;
    }

    public void solve() {
        for (List<Integer> numberList : groupList) {
            int bitValue = 0;
            for (int number : numberList) {
                bitValue = bitValue | (1 << number);
            }
            groupBitList.add(bitValue);
        }
        int answer = 0;
        for (int i = 1; i < groupBitList.size(); i++) {
            for (int j = i + 1; j < groupBitList.size(); j++) {
                if (groupBitList.get(i) + groupBitList.get(j) == (groupBitList.get(i)
                    | groupBitList.get(j))) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}