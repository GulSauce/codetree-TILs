import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Input> inputs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        M = toInt(st);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            inputs.add(new Input(toInt(st), toInt(st), toInt(st)));
        }

        new Solver(N, inputs).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int endNumber;
    List<Input> inputs;

    int[] parent;

    public Solver(int endNumber, List<Input> inputs) {
        this.endNumber = endNumber;
        this.inputs = inputs;
        this.parent = new int[endNumber + 1];
    }

    public void solve() {
        for (int i = 1; i <= endNumber; i++) {
            parent[i] = i;
        }

        for (Input input : inputs) {
            if (input.command == 0) {
                unionParent(input.a, input.b);
            }
            if (input.command == 1) {
                System.out.println(findParent(input.a) == findParent(input.b) ? 1 : 0);
            }
        }
    }

    public void unionParent(int x, int y) {
        int xParent = findParent(x);
        int yParent = findParent(y);

        if (xParent != yParent) {
            parent[yParent] = xParent;
        }
    }

    public int findParent(int number) {
        if (parent[number] == number) {
            return number;
        }

        parent[number] = findParent(parent[number]);
        return parent[number];
    }
}

class Input {

    int command;
    int a;
    int b;

    public Input(int command, int a, int b) {
        this.command = command;
        this.a = a;
        this.b = b;
    }
}