import java.util.*;

public class Main {
    private static class Solver{
        int[][] board;
        BidMoveInfo[] bidMoveInfos;

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        public Solver(
                int[][] board,
                BidMoveInfo[] bidMoveInfos
        ){
            this.board = board;
            this.bidMoveInfos = bidMoveInfos;
        }

        public void solve(){
            for(int i = 0; i < 100; i++) {
                setBidOnBoard();
                removeCollideBids();
                setNextMove();
            }
            printResult();
        }

        private void printResult(){
            int result = 0;
            for(int y = 1; y < board.length; y++){
                for(int x = 1; x < board.length; x++){
                    if(board[y][x] == 1){
                        result++;
                    }
                }
            }
            System.out.println(result);
        }

        private void removeCollideBids() {
            ArrayList<BidMoveInfo> surviveBids = new ArrayList<>();
            for (BidMoveInfo bidMoveInfo : bidMoveInfos) {
                if(2 <= board[bidMoveInfo.x][bidMoveInfo.y]){
                    continue;
                }
                surviveBids.add(bidMoveInfo);
            }
            bidMoveInfos = surviveBids.toArray(new BidMoveInfo[0]);
        }

        private void setNextMove(){
            for(int i = 0; i < bidMoveInfos.length; i++){
                BidMoveInfo bidMoveInfo = bidMoveInfos[i];
                int directionIndex = getDirectionIndex(bidMoveInfo.d);
                int nextY = bidMoveInfo.y + dy[directionIndex];
                int nextX = bidMoveInfo.x + dx[directionIndex];
                if(isOutOfRange(nextY, nextX)){
                    Character reverseDirection = getReverseDirection(bidMoveInfo.d);
                    bidMoveInfos[i] = new BidMoveInfo(bidMoveInfo.x, bidMoveInfo.y, reverseDirection);
                    continue;
                }
                bidMoveInfos[i] = new BidMoveInfo(nextX, nextY, bidMoveInfo.d);
            }
        }

        private boolean isOutOfRange(int y, int x){
            return y <= 0 || board.length <= y || x <= 0 || board.length <= x;
        }

        private Character getReverseDirection(Character d){
            if(d.equals('R')){
                return 'L';
            }
            if(d.equals('U')){
                return 'D';
            }
            if(d.equals('L')){
                return 'R';
            }
            if(d.equals('D')){
                return 'U';
            }
            return '?';
        }

        private int getDirectionIndex(Character d){
            if(d.equals('R')){
                return 0;
            }
            if(d.equals('U')){
                return 1;
            }
            if(d.equals('L')){
                return 2;
            }
            if(d.equals('D')){
                return 3;
            }
            return -1;
        }

        private void setBidOnBoard(){
            for(int y = 1; y < board.length; y++){
                for(int x = 1; x < board.length; x++){
                    board[y][x] = 0;
                }
            }
            for(BidMoveInfo bidMoveInfo: bidMoveInfos){
                board[bidMoveInfo.x][bidMoveInfo.y]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int[][] board = new int[N+1][N+1];
            for(int y = 1; y <= N; y++){
                for(int x = 1; x <= N; x++){
                    board[y][x] = 0;
                }
            }

            int M = sc.nextInt();

            BidMoveInfo[] bidMoveInfos = new BidMoveInfo[M];

            for(int j = 0; j < M; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                Character d = sc.next().charAt(0);
                bidMoveInfos[j] = new BidMoveInfo(x, y, d);
            }

            new Solver(board, bidMoveInfos).solve();
        }
    }

    private static class BidMoveInfo{
        int x;
        int y;
        Character d;

        public BidMoveInfo(
                int x,
                int y,
                Character d
        ){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}