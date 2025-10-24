import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        List<Integer> moveEnergyPrices = new ArrayList<>();
        List<Integer> chargeEnergyPrices = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            moveEnergyPrices.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            chargeEnergyPrices.add(Integer.parseInt(st.nextToken()));
        }

        new Solver(moveEnergyPrices, chargeEnergyPrices).solve();
    }

}

class Solver {

    List<Integer> moveEnergyPrices;
    List<Integer> chargeEnergyPrices;

    int[] minChargeEnergyPrices = new int[100_000];

    public Solver(
        List<Integer> moveEnergyPrices,
        List<Integer> chargeEnergyPrices

    ) {
        this.moveEnergyPrices = moveEnergyPrices;
        this.chargeEnergyPrices = chargeEnergyPrices;
    }

    public void solve() {
        minChargeEnergyPrices[0] = chargeEnergyPrices.get(0);
        for (int i = 1; i < chargeEnergyPrices.size(); i++) {
            minChargeEnergyPrices[i] = Math.min(minChargeEnergyPrices[i - 1],
                chargeEnergyPrices.get(i));
        }

        int answer = 0;
        for (int i = 0; i < moveEnergyPrices.size(); i++) {
            answer += moveEnergyPrices.get(i) * minChargeEnergyPrices[i];
        }
        System.out.println(answer);
    }
}