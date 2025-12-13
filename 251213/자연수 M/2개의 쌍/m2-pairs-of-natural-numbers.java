import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<NumberInfo> numberInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numberInfos.add(new NumberInfo(toInt(st), toInt(st)));
        }

        new Solver(N, numberInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int numberCount;
    List<NumberInfo> numberInfos;
    Deque<Integer> numberDeque;

    public Solver(int numberCount, List<NumberInfo> numberInfos) {
        this.numberCount = numberCount;
        this.numberInfos = numberInfos;
    }

    public void solve() {
        List<Integer> numbers = new ArrayList<>();
        for (NumberInfo numberInfo : numberInfos) {
            for (int i = 0; i < numberInfo.count; i++) {
                numbers.add(numberInfo.value);
            }
        }
        Collections.sort(numbers);
        numberDeque = new ArrayDeque<>(numbers);
        int answer = Integer.MAX_VALUE;
        while (!numberDeque.isEmpty()) {
            answer = Math.min(answer, numberDeque.pollFirst() + numberDeque.pollLast());
        }
        System.out.println(answer);
    }
}

class NumberInfo {

    int count;
    int value;

    public NumberInfo(int count, int value) {
        this.count = count;
        this.value = value;
    }
}