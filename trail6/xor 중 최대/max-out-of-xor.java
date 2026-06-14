import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(toInt(st));
        }

        new Solver(numbers).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    Node root = new Node();
    List<Integer> numbers;
    List<String> patterns = new ArrayList<>();

    public Solver(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void solve() {
        setPattern();
        for (String pattern : patterns) {
            root.insert(pattern);
        }

        int answer = 0;
        for (String pattern : patterns) {
            answer = Math.max(answer, root.walk(pattern));
        }
        System.out.println(answer);
    }

    private void setPattern() {
        List<String> temp = new ArrayList<>();
        for (int number : numbers) {
            temp.add(Integer.toBinaryString(number));
        }
        final int SLOT_SIZE = 32;
        for (String binary : temp) {
            int emptySlot = SLOT_SIZE - binary.length();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < emptySlot; i++) {
                sb.append(0);
            }
            sb.append(binary);
            patterns.add(sb.toString());
        }
    }
}

class Node {

    Character value;
    boolean isEnd = false;
    Node[] children = new Node[2];

    public Node() {
    }

    public void insert(String pattern) {
        Node cur = this;
        for (Character letter : pattern.toCharArray()) {
            int value = letter - '0';
            Node child = cur.children[value];
            if (child == null) {
                child = new Node();
                child.value = letter;
                cur.children[value] = child;
            }
            cur = child;
        }
        cur.isEnd = true;
    }

    public int walk(String pattern) {
        Node cur = this;
        StringBuilder sb = new StringBuilder();
        for (Character letter : pattern.toCharArray()) {
            Node child;
            if (letter == '0') {
                child = cur.children[1];
            } else {
                child = cur.children[0];
            }
            if (child == null) {
                if (letter == '0') {
                    child = cur.children[0];
                } else {
                    child = cur.children[1];
                }
            }
            sb.append(child.value);
            cur = child;
            if (child.isEnd) {
                return getXorValue(pattern, sb.toString());
            }
        }
        return -1;
    }

    private int getXorValue(String a, String b) {
        int aValue = Integer.parseInt(a, 2);
        int bValue = Integer.parseInt(b, 2);
        return aValue ^ bValue;
    }
}
