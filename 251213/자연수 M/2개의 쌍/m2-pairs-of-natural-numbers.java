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

        new Solver(numberInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<NumberInfo> numberInfos;
    Deque<NumberInfo> numberInfoDeque;

    public Solver(List<NumberInfo> numberInfos) {
        this.numberInfos = numberInfos;
    }

    public void solve() {
        Collections.sort(numberInfos);
        numberInfoDeque = new ArrayDeque<>(numberInfos);
        int answer = Integer.MAX_VALUE;
        while (!numberInfoDeque.isEmpty()) {
            NumberInfo first = numberInfoDeque.peekFirst();
            NumberInfo last = numberInfoDeque.peekLast();
            if (first.count > last.count) {
                first.count = first.count - last.count;
                numberInfoDeque.pollLast();
            }
            if (first.count == last.count) {
                numberInfoDeque.pollFirst();
                numberInfoDeque.pollLast();
            }
            if (first.count < last.count) {
                first.count = last.count - first.count;
                numberInfoDeque.pollFirst();
            }
            answer = Math.min(answer, first.value + last.value);
        }

        System.out.println(answer);
    }
}

class NumberInfo implements Comparable<NumberInfo> {

    int count;
    int value;

    public NumberInfo(int count, int value) {
        this.count = count;
        this.value = value;
    }

    @Override
    public int compareTo(NumberInfo other) {
        return Integer.compare(this.value, other.value);
    }
}