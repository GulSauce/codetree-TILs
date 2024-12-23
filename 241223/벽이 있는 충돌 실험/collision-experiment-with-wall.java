import java.util.*;

public class Main {
    private static class Solver{
        int gridSize;
        BidMoveInfo[] bidMoveInfos;
        int[][] grid;
        final int TOTAL_TIME = 100;
        Map<String, Integer> directionIndexMap = new HashMap<>();
        Map<String, String> reverseDirectionMap = new HashMap<>();

        int[] deltaRow = {0, -1, 0, 1};
        int[] deltaCol = {1, 0, -1, 0};

        public Solver(
                int N,
                BidMoveInfo[] bidMoveInfos
        ){
            this.gridSize = N;
            this.bidMoveInfos = bidMoveInfos;
            this.grid = new int[N+1][N+1];
        }

        public void solve(){
            initDirectionIndexMap();
            initReverseDirectionMap();
            for(int time = 0; time <= TOTAL_TIME; time++){
                moveBids();
                setBidsAtGrid();
                removeCollideBids();
            }
            System.out.println(bidMoveInfos.length);
        }

        private void removeCollideBids(){
            ArrayList<BidMoveInfo> surviveBids = new ArrayList<>();
            for(BidMoveInfo bidMoveInfo: bidMoveInfos){
                if(2 <= grid[bidMoveInfo.row][bidMoveInfo.col]){
                    continue;
                }
                surviveBids.add(bidMoveInfo);
            }
            bidMoveInfos = surviveBids.toArray(new BidMoveInfo[0]);
        }

        private void moveBids(){
            for(BidMoveInfo bidMoveInfo: bidMoveInfos){
                int directionIndex = directionIndexMap.get(bidMoveInfo.direction);
                int nextRow = bidMoveInfo.row + deltaRow[directionIndex];
                int nextCol = bidMoveInfo.col + deltaRow[directionIndex];
                if(isOutOfGrid(nextRow, nextCol)){
                    bidMoveInfo.direction = reverseDirectionMap.get(bidMoveInfo.direction);
                    continue;
                }
                bidMoveInfo.row = nextRow;
                bidMoveInfo.col = nextCol;
            }
        }

        private boolean isOutOfGrid(int row, int col){
            return row < 1 || gridSize < row || col < 1 || gridSize < col;
        }

        private void setBidsAtGrid(){
            for(int[] array: grid){
                Arrays.fill(array, 0);
            }
            for(BidMoveInfo bidMoveInfo: bidMoveInfos){
                grid[bidMoveInfo.row][bidMoveInfo.col]++;
            }
        }

        private void initReverseDirectionMap(){
            reverseDirectionMap.put("R", "L");
            reverseDirectionMap.put("U", "D");
            reverseDirectionMap.put("L", "R");
            reverseDirectionMap.put("D", "U");
        }

        private void initDirectionIndexMap(){
            directionIndexMap.put("R", 0);
            directionIndexMap.put("U", 1);
            directionIndexMap.put("L", 2);
            directionIndexMap.put("D", 3);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int testCase = 0; testCase < T; testCase++){
            int N = sc.nextInt();
            int M = sc.nextInt();
            BidMoveInfo[] bidMoveInfos = new BidMoveInfo[M];
            for(int i = 0; i < M; i++){
                bidMoveInfos[i] = new BidMoveInfo(sc.nextInt(), sc.nextInt(), sc.next());
            }
            new Solver(N, bidMoveInfos).solve();
        }
    }

    private static class BidMoveInfo{
        int row;
        int col;
        String direction;

        public BidMoveInfo(
                int x,
                int y,
                String d
        ){
            this.row = x;
            this.col = y;
            this.direction= d;
        }
    }
}