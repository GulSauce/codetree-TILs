import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M, K;
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.nextInt());
        }
        for (int i = 0; i < M; i++) {
            B.add(sc.nextInt());
        }
        sc.close();

        new Solver(K, A, B).solve();
    }
}

class Solver {
    int targetSequenceNumber;
    PriorityQueue<SumInfo> priorityQueue = new PriorityQueue<>();
    ArrayList<Integer> A;
    ArrayList<Integer> B;

    public Solver(
            int K,
            ArrayList<Integer> A,
            ArrayList<Integer> B
    ) {
        this.targetSequenceNumber = K;
        this.A = A;
        this.B = B;
    }

    public void solve() {
        Collections.sort(A);
        Collections.sort(B);
        priorityQueue.add(new SumInfo(A.get(0) + B.get(0), 0, 0));
        for (int i = 1; i < targetSequenceNumber; i++) {
            SumInfo prev = priorityQueue.poll();
            if (prev.aIndex + 1 < A.size()) {
                int sum1 = A.get(prev.aIndex + 1) + B.get(prev.bIndex);
                priorityQueue.add(new SumInfo(sum1, prev.aIndex + 1, prev.bIndex));
            }
            if (prev.bIndex + 1 < A.size()) {
                int sum2 = A.get(prev.aIndex) + B.get(prev.bIndex + 1);
                priorityQueue.add(new SumInfo(sum2, prev.aIndex, prev.bIndex + 1));
            }
        }
        System.out.println(priorityQueue.peek().sum);
    }
}

class SumInfo implements Comparable<SumInfo> {
    @Override
    public int compareTo(SumInfo other) {
        return sum - other.sum;
    }

    int sum;
    int aIndex;
    int bIndex;

    public SumInfo(
            int sum,
            int aIndex,
            int bIndex
    ) {
        this.sum = sum;
        this.aIndex = aIndex;
        this.bIndex = bIndex;
    }
}