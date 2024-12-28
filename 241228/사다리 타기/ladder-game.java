import java.util.*;

public class Main {
    private static class Solver{
        int totalLadder;
        int ladderHeight;
        List<LineInfo> lineInfos;
        List<Integer> selectedLineNumbers = new ArrayList<>();
        int[][] ladderLinkInfos;
        List<Integer> answer = new ArrayList<>();
        List<Integer> submission = new ArrayList<>();

        int minLineCount = Integer.MAX_VALUE;

        public Solver(
                int n,
                List<LineInfo> lineInfos
        ){
            this.totalLadder = n;
            this.lineInfos = lineInfos;
            this.ladderHeight = getLadderHeight();
            this.ladderLinkInfos = new int[totalLadder+1][ladderHeight];
        }

        private int getLadderHeight(){
            int maxHeight = 0;
            for(LineInfo lineInfo: lineInfos){
                maxHeight = Math.max(maxHeight, lineInfo.height);
            }
            return maxHeight+1;
        }

        public void solve(){
            getAnswer();
            getCombinationAndStartLadderGame(0);
            System.out.println(minLineCount);
        }

        private void getCombinationAndStartLadderGame(int ladderNumber){
            if(lineInfos.size() == ladderNumber){
                setLadderLinkInfo();
                startLadderGame();
                if(isSame(answer, submission)) {
                    minLineCount = Math.min(minLineCount, selectedLineNumbers.size());
                }
                return;
            }
            selectedLineNumbers.add(ladderNumber);
            getCombinationAndStartLadderGame(ladderNumber+1);
            selectedLineNumbers.remove(selectedLineNumbers.size()-1);
            getCombinationAndStartLadderGame(ladderNumber+1);
        }

        private void setLadderLinkInfo(){
            for(int[] array: ladderLinkInfos){
                Arrays.fill(array, 0);
            }
            for(int number: selectedLineNumbers){
                LineInfo lineInfo = lineInfos.get(number);
                ladderLinkInfos[lineInfo.startLadderNumber][lineInfo.height] = lineInfo.startLadderNumber + 1;
                ladderLinkInfos[lineInfo.startLadderNumber+1][lineInfo.height] = lineInfo.startLadderNumber;
            }
        }

        private void startLadderGame(){
            submission.clear();
            for(int ladderNumber = 1; ladderNumber <= totalLadder; ladderNumber++){
                int dest = getDest(ladderNumber);
                submission.add(dest);
            }
        }

        private int getDest(int ladderNumber){
            int dest;
            int currentNumber = ladderNumber;
            for(int i = 1; i < ladderHeight; i++){
                if(ladderLinkInfos[currentNumber][i] == 0){
                    continue;
                }
                currentNumber = ladderLinkInfos[currentNumber][i];
            }
            dest = currentNumber;
            return dest;
        }

        private boolean isSame(List<Integer> answer, List<Integer> submission) {
            if (answer.size() != submission.size()) {
                return false;
            }
            for (int i = 0; i < answer.size(); i++) {
                if (answer.get(i) != submission.get(i)) {
                    return false;
                }
            }
            return true;
        }

        private void getAnswer(){
            for(int[] array: ladderLinkInfos){
                Arrays.fill(array, 0);
            }
            for(LineInfo lineInfo: lineInfos) {
                ladderLinkInfos[lineInfo.startLadderNumber][lineInfo.height] = lineInfo.startLadderNumber + 1;
                ladderLinkInfos[lineInfo.startLadderNumber+1][lineInfo.height] = lineInfo.startLadderNumber;
            }
            for(int ladderNumber = 1; ladderNumber <= totalLadder; ladderNumber++){
                int currentNumber = ladderNumber;
                for(int i = 1; i < ladderHeight; i++){
                    if(ladderLinkInfos[currentNumber][i] == 0){
                        continue;
                    }
                    currentNumber = ladderLinkInfos[currentNumber][i];
                }
                int dest = currentNumber;
                answer.add(dest);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<LineInfo> lineInfos = new ArrayList<>();
        for(int i = 0; i  < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            lineInfos.add(new LineInfo(a, b));
        }

        new Solver(n, lineInfos).solve();
    }

    private static class LineInfo{
        int startLadderNumber;
        int height;

        public LineInfo(
                int a,
                int b
        ){
            this.startLadderNumber = a;
            this.height = b;
        }
    }
}