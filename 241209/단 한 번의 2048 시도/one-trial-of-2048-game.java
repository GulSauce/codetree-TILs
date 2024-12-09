import java.util.*;

public class Main {
    private static class Solver{
        int[][] board2048;
        String dir;
        final int MAX_Y;
        final int MAX_X;

        public Solver(
                int[][] board2048,
                String dir
        ){
            this.board2048 = board2048;
            this.dir = dir;
            this.MAX_X = board2048.length;
            this.MAX_Y = board2048.length;
        }

        public void solve(){
            if(dir.equals("L")){
                moveLeft();
            }
            if(dir.equals("R")){
                moveRight();
            }
            if(dir.equals("U")){
                moveUp();
            }
            if(dir.equals("D")){
                moveDown();
            }
            printResult();
        }

        private void printResult(){
            for(int[] row: board2048){
                for(int number: row){
                    System.out.printf("%d ", number);
                }
                System.out.println();
            }
        }

        private void moveLeft(){
            gatherLeft();
            sumLeftSameValue();
        }

        private void moveRight(){
            gatherRight();
            sumRightSameValue();
        }

        private void moveUp(){
            gatherUp();
            sumUpSameValue();
        }

        private void moveDown(){
            gatherDown();
            sumDownSameValue();
        }

        private void  sumLeftSameValue(){
            for(int y = 0; y < MAX_Y; y++){
                for(int x = 0; x < MAX_X-1; x++){
                    if(board2048[y][x] == board2048[y][x+1]){
                        board2048[y][x] += board2048[y][x+1];
                        board2048[y][x+1] = 0;
                    }
                }
            }
            gatherLeft();
        }

        private void sumRightSameValue(){
            for(int y = 0; y < MAX_Y; y++){
                for(int x = MAX_X-1; x >= 1; x--){
                    if(board2048[y][x] == board2048[y][x-1]){
                        board2048[y][x] += board2048[y][x-1];
                        board2048[y][x-1] = 0;
                    }
                }
            }
            gatherRight();
        }

        private void sumUpSameValue(){
            for(int x = 0; x < MAX_X; x++){
                for(int y = 0; y < MAX_Y-1; y++){
                    if(board2048[y][x] == board2048[y+1][x]){
                        board2048[y][x] += board2048[y+1][x];
                        board2048[y+1][x] = 0;
                    }
                }
            }
            gatherUp();
        }

        private void sumDownSameValue(){
            for(int x = 0; x < MAX_X; x++){
                for(int y = MAX_Y-1; y >= 1; y--){
                    if(board2048[y][x] == board2048[y-1][x]){
                        board2048[y][x] += board2048[y-1][x];
                        board2048[y-1][x] = 0;
                    }
                }
            }
            gatherDown();
        }

        private void gatherLeft(){
            for(int y = 0; y < MAX_Y; y++){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int x= 0; x < MAX_X; x++){
                    if(board2048[y][x] == 0){
                        continue;
                    }
                    temp.add(board2048[y][x]);
                }
                for(int x = 0; x < MAX_X; x++){
                    board2048[y][x] = 0;
                }
                for(int i = 0; i < temp.size(); i++){
                    board2048[y][i] = temp.get(i);
                }
            }
        }

        private void gatherRight() {
            for (int y = 0; y < MAX_Y; y++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int x = 0; x < MAX_X; x++) {
                    if (board2048[y][MAX_X-x-1] == 0) {
                        continue;
                    }
                    temp.add(board2048[y][MAX_X-x-1]);
                }
                for (int x = MAX_X-1; x >= 0; x--) {
                    board2048[y][x] = 0;
                }
                for (int i = 0; i < temp.size(); i++) {
                    board2048[y][MAX_X-i-1] = temp.get(i);

                }
            }
        }

        private void gatherUp(){
            for(int x= 0; x < MAX_X; x++){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int y = 0; y < MAX_Y; y++){
                    if(board2048[y][x] == 0){
                        continue;
                    }
                    temp.add(board2048[y][x]);
                }
                for(int y = 0; y < MAX_Y; y++){
                    board2048[y][x] = 0;
                }
                for(int i = 0; i < temp.size(); i++) {
                    board2048[i][x] = temp.get(i);
                }
            }
        }

        private void gatherDown(){
            for(int x= 0; x < MAX_X; x++){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int y = 0; y < MAX_Y; y++){
                    if(board2048[MAX_Y-y-1][x] == 0){
                        continue;
                    }
                    temp.add(board2048[MAX_Y-y-1][x]);
                }
                for(int y = 0; y < MAX_Y; y++){
                    board2048[y][x] = 0;
                }
                for(int i = 0; i < temp.size(); i++) {
                    board2048[MAX_Y-i-1][x] = temp.get(i);
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[][] board2048 = new int[4][4];

        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                board2048[y][x] = sc.nextInt();
            }
        }

        String dir = sc.next();

        new Solver(board2048, dir).solve();
    }
}