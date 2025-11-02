import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(M, numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int NOT_ALLOCATED = Integer.MAX_VALUE;
    int maxNumber;
    List<Integer> numbers;
    HashSet<Integer> inRangeHashSet = new HashSet<>();
    HashSet<Integer> outRangeHashSet = new HashSet<>();
    HashMap<Integer, Integer> inRangeHashMap = new HashMap<>();
    HashMap<Integer, Integer> outRangeHashMap = new HashMap<>();

    public Solver(int maxNumber, List<Integer> numbers) {
        this.maxNumber = maxNumber;
        this.numbers = numbers;
    }

    public void solve() {
        for (int number : numbers) {
            outRangeHashMap.compute(number, (key, value) -> value == null ? 1 : value + 1);
            outRangeHashSet.add(number);
        }
        int i = 0;
        int j = 0;
        int cur = numbers.get(j);
        checkAndRemove(cur, outRangeHashSet, outRangeHashMap);
        checkAndInsert(cur, inRangeHashSet, inRangeHashMap);
        int answer = NOT_ALLOCATED;
        if (outRangeHashSet.size() == maxNumber && inRangeHashSet.size() == maxNumber) {
            answer = Math.min(answer, j - i + 1);
        }
        for (; i < numbers.size(); i++) {
            while (true) {
                if (j < i) {
                    break;
                }
                if (inRangeHashSet.size() == maxNumber) {
                    if (outRangeHashSet.size() == maxNumber) {
                        answer = Math.min(answer, j - i + 1);
                    }
                    break;
                }
                if (j == numbers.size() - 1) {
                    break;
                }
                j++;
                checkAndInsert(numbers.get(j), inRangeHashSet, inRangeHashMap);
                checkAndRemove(numbers.get(j), outRangeHashSet, outRangeHashMap);
            }
            checkAndInsert(numbers.get(i), outRangeHashSet, outRangeHashMap);
            checkAndRemove(numbers.get(i), inRangeHashSet, inRangeHashMap);
        }
        System.out.println(answer == NOT_ALLOCATED ? -1 : answer);
    }

    private void checkAndRemove(int cur, HashSet<Integer> hashSet,
        HashMap<Integer, Integer> hashMap) {
        hashMap.compute(cur, (key, value) -> value - 1);
        if (0 < hashMap.get(cur)) {
            return;
        }
        if (maxNumber < cur) {
            return;
        }
        hashSet.remove(cur);
    }

    private void checkAndInsert(int cur, HashSet<Integer> hashSet,
        HashMap<Integer, Integer> hashMap) {
        hashMap.compute(cur, (key, value) -> value == null ? 1 : value + 1);
        if (maxNumber < cur) {
            return;
        }
        hashSet.add(cur);
    }
}
