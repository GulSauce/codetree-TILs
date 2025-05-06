import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N, K;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(K, numbers).solve();
    }
}

class Solver {
    int topK;
    ArrayList<Integer> numbers;

    public Solver(
            int K,
            ArrayList<Integer> numbers
    ) {
        this.topK = K;
        this.numbers = numbers;
    }

    public void solve() {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        ArrayList<NumberWithFrequency> numberWithFrequencies = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            numberWithFrequencies.add(new NumberWithFrequency(entry.getKey(), entry.getValue()));
        }
        Collections.sort(numberWithFrequencies);
        for (int i = 0; i < topK; i++) {
            System.out.print(numberWithFrequencies.get(i).number + " ");
        }
    }
}

class NumberWithFrequency implements Comparable<NumberWithFrequency> {

    int number;
    int frequency;

    @Override
    public int compareTo(NumberWithFrequency other) {
        if (other.frequency == frequency) {
            return other.number - number;
        }
        return other.frequency - frequency;
    }

    public NumberWithFrequency(
            int number,
            int frequency
    ) {
        this.number = number;
        this.frequency = frequency;
    }
}