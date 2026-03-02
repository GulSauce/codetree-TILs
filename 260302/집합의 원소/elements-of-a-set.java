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

    int[] rootOf;

    public Solver(int endNumber, List<Input> inputs) {
        this.endNumber = endNumber;
        this.inputs = inputs;
        this.rootOf = new int[endNumber + 1];
    }

    public void solve() {
        for (int i = 1; i <= endNumber; i++) {
            rootOf[i] = i;
        }

        for (Input input : inputs) {
            if (input.command == 0) {
                unionRoot(input.a, input.b);
            }
            if (input.command == 1) {
                System.out.println(
                    findRootWithCompacting(input.a) == findRootWithCompacting(input.b) ? 1 : 0);
            }
        }
    }

    public void unionRoot(int x, int y) {
        int xRoot = findRootWithCompacting(x);
        int yRoot = findRootWithCompacting(y);

        if (xRoot != yRoot) {
            rootOf[yRoot] = xRoot;
        }
    }

    public int findRootWithCompacting(int cur) {
        int root = rootOf[cur];
        if (root == cur) {
            return cur;
        }

        rootOf[cur] = findRootWithCompacting(root);
        return rootOf[cur];
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