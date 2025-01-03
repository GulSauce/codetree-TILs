import javax.swing.text.Style;
import java.util.*;

public class Main {
    private static class Solver{
        int answer = Integer.MAX_VALUE;
        int maxRepeat;

        List<String> grid;
        List<Coin> coins;
        List<Coin> selectedCoins = new ArrayList<>();

        Coordinate startCoordinate;
        Coordinate endCoordinate;

        public Solver(
                List<String> grid
        ){
            this.grid = grid;
            this.startCoordinate = getStartCoordinate();
            this.endCoordinate = getEndCoordinate();
            this.coins = getCoins();
            this.maxRepeat = coins.size();
        }

        public void solve(){
            Collections.sort(coins);
            getCombination(-1, 0);
            printAnswer();
        }

        private void printAnswer(){
            if(answer == Integer.MAX_VALUE){
                System.out.println(-1);
            }else{
                System.out.println(answer);
            }
        }

        private void getCombination(int prevIndex, int repeatCount){
            if(repeatCount == maxRepeat){
                int moveCount = getMoveCount();
                if(moveCount == -1){
                    return;
                }
                answer = Math.min(answer, moveCount);
                return;
            }

            for(int i = prevIndex+1; i < coins.size(); i++){
                selectedCoins.add(coins.get(i));
                getCombination(i, repeatCount+1);
                selectedCoins.remove(selectedCoins.size()-1);
                getCombination(i, repeatCount+1);
            }
        }

        private int getMoveCount(){
            if(selectedCoins.isEmpty()){
                return -1;
            }

            int coinCount = 1;
            Coordinate firstCoordinate = new Coordinate(selectedCoins.get(0).coordinate.row, selectedCoins.get(0).coordinate.col);
            int moveCount = getDist(startCoordinate, firstCoordinate);
            Coordinate prevCoordinate = firstCoordinate;

            for(int i = 1; i < selectedCoins.size(); i++){
                Coordinate curCoordinate = selectedCoins.get(i).coordinate;
                if(grid.get(curCoordinate.row).charAt(curCoordinate.col) - '0' <= grid.get(prevCoordinate.row).charAt(prevCoordinate.col) - '0'){
                    return -1;
                }
                coinCount++;
                moveCount += getDist(prevCoordinate, curCoordinate);
                prevCoordinate = curCoordinate;
            }

            moveCount += getDist(prevCoordinate, endCoordinate);
            if(coinCount < 3){
                return -1;
            }
            return moveCount;
        }

        private int getDist(Coordinate prevCoordinate, Coordinate curCoordinate){
            return Math.abs(curCoordinate.row-prevCoordinate.row) + Math.abs(curCoordinate.col-prevCoordinate.col);
        }

        private Coordinate getStartCoordinate(){
            for(int row = 0; row < grid.size(); row++){
                String str = grid.get(row);
                for(int col = 0; col <str.length(); col++){
                    if(str.charAt(col) == 'S'){
                        return new Coordinate(row, col);
                    }
                }
            }

            return new Coordinate(-1, -1);
        }

        private Coordinate getEndCoordinate(){
            for(int row = 0; row < grid.size(); row++){
                String str = grid.get(row);
                for(int col = 0; col <str.length(); col++){
                    if(str.charAt(col) == 'E'){
                        return new Coordinate(row, col);
                    }
                }
            }

            return new Coordinate(-1, -1);
        }

        private List<Coin> getCoins(){
            List<Coin> coins = new ArrayList<>();
            for(int row = 0; row < grid.size(); row++){
                String str = grid.get(row);
                for(int col = 0; col < str.length(); col++){
                    char charValue = str.charAt(col);
                    if('0' <= charValue && charValue <= '9'){
                        coins.add(new Coin(charValue-'0', new Coordinate(row, col)));
                    }
                }
            }
            return coins;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<String> grid = new ArrayList<>();
        for(int i = 0; i < N; i++){
            grid.add(sc.next());
        }
        new Solver(grid).solve();
    }

    private static class Coin implements Comparable<Coin>{
        int value;
        Coordinate coordinate;

        public Coin(
                int value,
                Coordinate coordinate
        ){
            this.value = value;
            this.coordinate = coordinate;
        }

        @Override
        public int compareTo(Coin coin){
            return this.value - coin.value;
        }
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int row,
                int col
        ){
            this.row = row;
            this.col = col;
        }
    }
}