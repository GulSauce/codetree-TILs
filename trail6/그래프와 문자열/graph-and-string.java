import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        String S;
        List<Edge> edges = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        S = st.nextToken();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(toInt(st), toInt(st), st.nextToken().charAt(0)));
        }

        new Solver(N, S, edges).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    int answer = 0;
    int nodeCount;
    final int P1 = 31;
    final int P2 = 51;
    ModCal modCal1 = new ModCal(1_000_000_007);
    ModCal modCal2 = new ModCal(1_000_000_009);

    int[] charOfDepthAt;

    long targetHash;
    long[] p1PowerOf;
    long[] p2PowerOf;

    String target;
    List<Edge> edges;
    List<List<Node>> graph = new ArrayList<>();

    public Solver(int nodeCount, String target, List<Edge> edges) {
        this.nodeCount = nodeCount;
        this.target = target;
        this.p1PowerOf = new long[target.length()];
        this.p2PowerOf = new long[target.length()];
        this.charOfDepthAt = new int[nodeCount];
        this.edges = edges;
    }

    public void solve() {
        for (int i = 0; i <= nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.v1).add(new Node(edge.v2, edge.value));
        }

        p1PowerOf[0] = 1;
        for (int i = 1; i < target.length(); i++) {
            p1PowerOf[i] = modCal1
                .of(p1PowerOf[i - 1])
                .mul(P1)
                .get();
        }

        p2PowerOf[0] = 1;
        for (int i = 1; i < target.length(); i++) {
            p2PowerOf[i] = modCal2
                .of(p2PowerOf[i - 1])
                .mul(P2)
                .get();
        }

        long hash1 = 0;
        long hash2 = 0;
        for (int i = 0; i < target.length(); i++) {
            hash1 = modCal1.of(p1PowerOf[target.length() - 1 - i])
                .mul(target.charAt(i) - 'a' + 1)
                .add(hash1)
                .get();

            hash2 = modCal2.of(p2PowerOf[target.length() - 1 - i])
                .mul(target.charAt(i) - 'a' + 1)
                .add(hash2)
                .get();
        }

        targetHash = hash1 << 32 | hash2;

        dfs(1, 0, 0, 0);
        System.out.println(answer);
    }

    private void dfs(int cur, int strLength, int depth, long hashValue) {
        if (hashValue == targetHash) {
            answer++;
        }
        List<Node> nears = graph.get(cur);
        if (strLength == target.length()) {
            for (Node near : nears) {
                charOfDepthAt[depth] = near.letter;
                long hashValue1 = hashValue >>> 32;
                hashValue1 = modCal1.of(hashValue1)
                    .sub(modCal1.of(charOfDepthAt[depth - target.length()] - 'a' + 1)
                        .mul(p1PowerOf[strLength - 1]).get())
                    .mul(P1)
                    .add(near.letter - 'a' + 1)
                    .get();

                long hashValue2 = hashValue << 32 >>> 32;
                hashValue2 = modCal2.of(hashValue2)
                    .sub(modCal2.of(charOfDepthAt[depth - target.length()] - 'a' + 1)
                        .mul(p2PowerOf[strLength - 1]).get())
                    .mul(P2)
                    .add(near.letter - 'a' + 1)
                    .get();

                dfs(near.number, strLength, depth + 1, hashValue1 << 32 | hashValue2);
            }
        } else {
            for (Node near : nears) {
                charOfDepthAt[depth] = near.letter;
                long hashValue1 = hashValue >>> 32;
                hashValue1 = modCal1.of(near.letter - 'a' + 1)
                    .mul(p1PowerOf[target.length() - strLength - 1])
                    .add(hashValue1)
                    .get();

                long hashValue2 = hashValue << 32 >>> 32;
                hashValue2 = modCal2.of(near.letter - 'a' + 1)
                    .mul(p2PowerOf[target.length() - strLength - 1])
                    .add(hashValue2)
                    .get();

                dfs(near.number, strLength + 1, depth + 1, hashValue1 << 32 | hashValue2);
            }
        }
    }
}

class ModCal {

    private final int R;

    public ModCal(int r) {
        R = r;
    }

    public Val of(long v) {
        return new Val(v % R);
    }

    public class Val {

        private long v;

        public Val(long v) {
            this.v = v;
        }

        public Val add(long b) {
            v = ((v % R) + (b % R)) % R;
            return this;
        }

        public Val sub(long b) {
            v = ((v % R) - (b % R)) % R;
            v = v < 0 ? v + R : v;
            return this;
        }

        public Val mul(long b) {
            v = ((v % R) * (b % R)) % R;
            return this;
        }

        public long get() {
            return v;
        }
    }
}

class Node {

    int number;
    char letter;

    public Node(int number, char letter) {
        this.number = number;
        this.letter = letter;
    }
}

class Edge {

    int v1;
    int v2;
    char value;

    public Edge(int v1, int v2, char value) {
        this.v1 = v1;
        this.v2 = v2;
        this.value = value;
    }
}