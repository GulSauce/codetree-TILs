import javax.sound.sampled.Line;
import java.util.*;

public class Main {
    private static class Solver{
        int lineInfosIndex;

        int[] dp;

        List<LineInfo> lineInfos;

        public Solver(
                int n,
                List<LineInfo> lineInfos
        ){
            this.lineInfosIndex = n-1;
            this.dp = new int[n];
            this.lineInfos = lineInfos;
        }

        public void solve(){
            Collections.sort(lineInfos);
            initDP();
            calcDP();
            printAnswer();
        }
        
        private void printAnswer(){
            int answer = 0;
            for(int value: dp){
                answer = Math.max(answer, value);
            }
            System.out.println(answer);
        }

        private void calcDP(){
            for(int cur = 1; cur <= lineInfosIndex; cur++){
                for(int prev =0; prev < cur; prev++){
                    LineInfo prevLineInfo = lineInfos.get(prev);
                    LineInfo curLineInfo = lineInfos.get(cur);
                    if(curLineInfo.start <= prevLineInfo.end){
                        continue;
                    }
                    dp[cur] = Math.max(dp[cur], dp[prev]+1);
                }
            }
        }

        private void initDP(){
            Arrays.fill(dp, 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<LineInfo> lineInfos = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            lineInfos.add(new LineInfo(x1, x2));
        }
        new Solver(n, lineInfos).solve();
    }

    private static class LineInfo implements Comparable<LineInfo> {
        int start;
        int end;

        public LineInfo(
                int x1,
                int x2
        ){
            this.start = x1;
            this.end = x2;
        }

        @Override
        public int compareTo(LineInfo lineInfo){
            return this.start - lineInfo.start;
        }
    }
}