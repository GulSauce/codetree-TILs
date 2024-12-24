import java.util.*;

public class Main {
    private static class Solver{
        List<LineInfo> lineInfos;

        public Solver(
                List<LineInfo> lineInfos
        ){
            this.lineInfos = lineInfos;
        }

        public void solve(){
            Collections.sort(lineInfos);
            int currentEnd = lineInfos.get(0).end;
            int count = 1;
            for(LineInfo lineInfo: lineInfos){
                if(lineInfo.end <= currentEnd){
                    continue;
                }
                currentEnd = lineInfo.end;
                count++;
            }
            System.out.print(count);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<LineInfo> lineInfos = new ArrayList<>();
        for(int i = 0; i < n; i++){
            lineInfos.add(new LineInfo(sc.nextInt(), sc.nextInt()));
        }

        new Solver(lineInfos).solve();
    }

    private static class LineInfo implements Comparable<LineInfo>{
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
        public int compareTo(LineInfo lineinfo){
            return this.end - lineinfo.end;
        }
    }
}