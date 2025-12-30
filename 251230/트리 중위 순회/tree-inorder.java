import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int K;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < (1 << K) - 1; i++) {
            numbers.add(toInt(st));
        }

        new Solver(K, numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int height;
    int[] fullBinaryTree;
    List<Integer> numbers;

    public Solver(int height, List<Integer> numbers) {
        this.height = height;
        this.numbers = numbers;
        this.fullBinaryTree = new int[(1 << height)];
    }

    public void solve() {
        inorderDFS(1);
        for (int h = 1; h <= height; h++) {
            int nodeCount = 1 << (h - 1);
            int nodeStart = 1 << (h - 1);
            for (int v = 0; v < nodeCount; v++) {
                System.out.print(fullBinaryTree[nodeStart + v] + " ");
            }
            System.out.println();
        }
    }

    int index = 0;

    private void inorderDFS(int cur) {
        if (fullBinaryTree.length <= cur) {
            return;
        }
        inorderDFS(cur * 2);
        fullBinaryTree[cur] = numbers.get(index);
        index++;
        inorderDFS(cur * 2 + 1);
    }
}