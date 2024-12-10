import java.util.*;

public class Main {
    private static class Solver{
        int[][] board;
        final int BOARD_SIZE;
        final int BLOCK_START_COLUMN;
        int blockSize;


        public Solver(
                int[][] board,
                int m,
                int k
        ){
            this.board = board;
            this.BLOCK_START_COLUMN = k;
            this.blockSize = m;
            this.BOARD_SIZE = board.length-1;
        }

        public void solve(){
            setBlockToFirstRow();
            moveNextRow(1);
            printBoard();
        }

        private void printBoard(){
            for(int r = 1; r <= BOARD_SIZE; r++){
                for(int c =1; c <= BOARD_SIZE; c++){
                    System.out.printf("%d ", board[r][c]);
                }
                System.out.println();
            }
        }

        private void moveNextRow(int currentBlockRow){
            int nextBlockRow = currentBlockRow+1;
            if(isOutOfRange(nextBlockRow)){
                return;
            }
            if(isCollide(nextBlockRow)){
                return;
            }
            for(int c = BLOCK_START_COLUMN; c < BLOCK_START_COLUMN+ blockSize; c++){
                board[currentBlockRow][c] = 0;
                board[nextBlockRow][c] = 1;
            }
            moveNextRow(nextBlockRow);
        }

        private boolean isCollide(int r){
            for(int c = BLOCK_START_COLUMN; c < BLOCK_START_COLUMN+ blockSize; c++){
                if(board[r][c] == 1){
                    return true;
                }
            }
            return false;
        }

        private boolean isOutOfRange(int r){
            return !(1 <= r && r <= BOARD_SIZE);
        }

        private void setBlockToFirstRow(){
            for(int c = BLOCK_START_COLUMN; c < BLOCK_START_COLUMN+ blockSize; c++){
                board[1][c] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] board = new int[n+1][n+1];
        for(int r = 1; r <= n; r++){
            for(int c =1; c <= n; c++){
                board[r][c] = sc.nextInt();
            }
        }

        new Solver(board, m, k).solve();
    }
}