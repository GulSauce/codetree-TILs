import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<Line> lines = new ArrayList<>();
        int x1, x2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            lines.add(new Line(x1, x2));
        }

        new Solver(lines).solve();
    }
}

class Solver {
    ArrayList<Line> lines;

    public Solver(
            ArrayList<Line> lines
    ) {
        this.lines = lines;
    }

    public void solve() {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < lines.size(); i++) {
            answer = Math.min(answer, calcRemoveOneLine(i));
        }
        System.out.println(answer);
    }

    private int calcRemoveOneLine(int index) {
        int leftMostX = Integer.MAX_VALUE;
        int rightMostX = Integer.MIN_VALUE;
        for (int i = 0; i < lines.size(); i++) {
            if (i == index) {
                continue;
            }
            leftMostX = Math.min(leftMostX, lines.get(i).left);
            rightMostX = Math.max(rightMostX, lines.get(i).right);
        }
        return rightMostX - leftMostX;
    }
}

class Line {
    int left;
    int right;

    public Line(
            int left,
            int right
    ) {
        this.left = left;
        this.right = right;
    }
}