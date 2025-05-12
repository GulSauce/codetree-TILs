import java.util.*;

public class Main {
    public static void main(String[] args) {
        int T;

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int M;
            M = sc.nextInt();
            ArrayList<Integer> numbers = new ArrayList<>();
            numbers.add(-1);
            for (int j = 0; j < M; j++) {
                numbers.add(sc.nextInt());
            }
            new Solver(numbers).solve();
        }
    }
}

class Solver {
    ArrayList<Integer> numbers;
    PriorityQueue<Integer> leftPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> rightPriorityQueue = new PriorityQueue<>();

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        System.out.print(numbers.get(1) + " ");
        if (numbers.get(1) <= numbers.get(0)) {
            rightPriorityQueue.add(numbers.get(1));
            leftPriorityQueue.add(numbers.get(2));
        } else {
            leftPriorityQueue.add(numbers.get(1));
            rightPriorityQueue.add(numbers.get(2));
        }
        for (int i = 3; i < numbers.size(); i++) {
            int cur = numbers.get(i);
            if (i % 2 == 1) {
                int leftMax = leftPriorityQueue.poll();
                int rightMin = rightPriorityQueue.poll();
                int[] temp = new int[]{leftMax, rightMin, cur};
                Arrays.sort(temp);
                leftPriorityQueue.add(temp[0]);
                leftPriorityQueue.add(temp[1]);
                rightPriorityQueue.add(temp[2]);

                System.out.print(leftPriorityQueue.peek() + " ");
            }
            if (i % 2 == 0) {
                int leftMax = leftPriorityQueue.poll();
                int rightMin = rightPriorityQueue.poll();
                int[] temp = new int[]{leftMax, rightMin, cur};
                Arrays.sort(temp);
                leftPriorityQueue.add(temp[0]);
                rightPriorityQueue.add(temp[1]);
                rightPriorityQueue.add(temp[2]);
            }
        }
        System.out.println();
    }
}