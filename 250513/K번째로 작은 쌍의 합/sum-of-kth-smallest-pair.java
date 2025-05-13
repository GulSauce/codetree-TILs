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
        for (int i = 0; i < A.size(); i++) {
            priorityQueue.add(new SumInfo(A.get(i) + B.get(0), i, 0));
        }
        for (int i = 1; i < targetSequenceNumber; i++) {
            SumInfo prev = priorityQueue.poll();
            if (prev.bIndex + 1 < B.size()) {
                int curAIndex = prev.aIndex;
                int curBIndex = prev.bIndex + 1;
                int sum2 = A.get(curAIndex) + B.get(curBIndex);
                priorityQueue.add(new SumInfo(sum2, curAIndex, curBIndex));
            }
        }
        System.out.println(priorityQueue.peek().sum);
    }
}

class SumInfo implements Comparable<SumInfo> {
    @Override
    public int compareTo(SumInfo other) {
        return Integer.compare(sum, other.sum);
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