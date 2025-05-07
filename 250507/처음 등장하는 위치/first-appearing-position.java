import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        numbers.add(-1);
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(numbers).solve();
    }
}

class Solver {
    int numbersIndex;
    ArrayList<Integer> numbers;


    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbersIndex = numbers.size() - 1;
        this.numbers = numbers;
    }

    public void solve() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 1; i <= numbersIndex; i++) {
            if (treeMap.containsKey(numbers.get(i))) {
                continue;
            }
            treeMap.put(numbers.get(i), i);
        }
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}