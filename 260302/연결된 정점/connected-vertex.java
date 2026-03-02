import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
            char command = st.nextToken().charAt(0);
            if (command == 'x') {
                inputs.add(new Input(command, toInt(st), toInt(st)));
            } else if (command == 'y') {
                inputs.add(new Input(command, toInt(st)));
            }
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
    HashMap<Integer, Integer> linkedCountHashMap = new HashMap<>();
    int[] rootOf;

    public Solver(int endNumber, List<Input> inputs) {
        this.endNumber = endNumber;
        this.inputs = inputs;
        this.rootOf = new int[endNumber + 1];
    }

    public void solve() {
        for (int i = 1; i <= endNumber; i++) {
            rootOf[i] = i;
            linkedCountHashMap.put(i, 1);
        }

        for (Input input : inputs) {
            if (input.command == 'x') {
                unionRoot(input.a, input.b);
            }
            if (input.command == 'y') {
                System.out.println(
                    linkedCountHashMap.get(findRootParentWitCompacting(input.a))
                );
            }
        }
    }

    private void unionRoot(int x, int y) {
        int xRoot = findRootParentWitCompacting(x);
        int yRoot = findRootParentWitCompacting(y);

        rootOf[yRoot] = xRoot;
        linkedCountHashMap.compute(xRoot, (k, v) -> {
            return v + linkedCountHashMap.get(yRoot);
        });
    }

    private int findRootParentWitCompacting(int cur) {
        int root = rootOf[cur];
        if (root == cur) {
            return cur;
        }

        rootOf[cur] = findRootParentWitCompacting(root);
        return rootOf[cur];
    }
}

class Input {

    char command;
    int a;
    int b;

    public Input(char command, int a, int b) {
        this.command = command;
        this.a = a;
        this.b = b;
    }

    public Input(char command, int a) {
        this.command = command;
        this.a = a;
    }
}