import java.util.*;

public class Main {
    private static class Solver{
        List<LineInfo> lineInfos;
        List<Integer> combinations = new ArrayList<>();
        int maxCombinationSize;
        int maxNotCollision = 0;

        public Solver(
                List<LineInfo> lineInfos
        ){
            maxCombinationSize = lineInfos.size();
            this.lineInfos = lineInfos;
        }

        public void solve() {
            findNotCollisionLines(0);
            System.out.print(maxNotCollision);
        }

        private void findNotCollisionLines(int currentIndex){
            if(maxCombinationSize == currentIndex){
                if(!isCollideAboveLines()){
                    maxNotCollision = Math.max(maxNotCollision, combinations.size());
                }
                return;
            }
            combinations.add(currentIndex);
            findNotCollisionLines(currentIndex+1);
            combinations.remove(combinations.size()-1);

            findNotCollisionLines(currentIndex+1);
        }

        private boolean isCollideAboveLines(){
            int[] lineCount = new int[1001];

            for(int index: combinations){
                LineInfo lineInfo = lineInfos.get(index);
                for(int i = lineInfo.start; i <= lineInfo.end; i++){
                    if(lineCount[i] == 1){
                        return true;
                    }
                    lineCount[i]++;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<LineInfo> lineInfos = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            lineInfos.add(new LineInfo(x1, x2));
        }
        new Solver(lineInfos).solve();
    }

    private static class LineInfo{
        int start;
        int end;

        public LineInfo(
                int x1,
                int x2
        ){
            this.start = x1;
            this.end = x2;
        }
    }
}