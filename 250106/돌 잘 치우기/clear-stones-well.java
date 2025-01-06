import java.util.*;

public class Main {
    private static class Solver{
        int removeRockCount;
        int gridIndex;
        int answer=0;

        final int ROCK_VALUE = 1;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        List<Coordinate> startPoints;
        List<Coordinate> rocks;

        boolean[][] visited;

        int[][] grid;

        public Solver(
                int[][] grid,
                List<Coordinate> startPoints,
                int m
        ){
            this.grid =grid;
            this.gridIndex = grid.length-1;
            this.startPoints = startPoints;
            this.removeRockCount = m;
            this.visited = new boolean[grid.length][grid.length];
        }

        public void solve(){
            initVisited();
            rocks = getRocks();
            getCombination(-1, 0);
            System.out.println(answer);
        }

        private void getCombination(int lastIndex, int curRemoveRockCount){
            if(curRemoveRockCount == removeRockCount){
                initVisited();
                bfs();
                return;
            }

            for(int i = lastIndex+1; i < rocks.size(); i++){
                Coordinate rock = rocks.get(i);
                grid[rock.row][rock.col] = 0;
                getCombination(i, curRemoveRockCount+1);
                grid[rock.row][rock.col] = ROCK_VALUE;
            }
        }

        private void bfs(){
            int areaCount = 0;
            Queue<Coordinate> bfsQ = new LinkedList<>();
            for(Coordinate startPoint: startPoints){
                areaCount++;
                visited[startPoint.row][startPoint.col] = true;
                bfsQ.add(startPoint);
            }

            while(!bfsQ.isEmpty()){
                Coordinate prevCoordinate = bfsQ.poll();
                for(int i = 0; i < 4; i++){
                    Coordinate curCoordinate = new Coordinate(prevCoordinate.row+dRow[i], prevCoordinate.col+dCol[i]);
                    if(isOutOfRange(curCoordinate)){
                        continue;
                    }
                    if(isVisited(curCoordinate)){
                        continue;
                    }
                    if(isRock(curCoordinate)){
                        continue;
                    }
                    areaCount++;
                    visited[curCoordinate.row][curCoordinate.col] = true;
                    bfsQ.add(curCoordinate);
                }
            }

            answer = Math.max(answer, areaCount);
        }

        private boolean isRock(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == ROCK_VALUE;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 1 || gridIndex < coordinate.row || coordinate.col < 1 || gridIndex < coordinate.col;
        }


        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
        }

        private List<Coordinate> getRocks(){
            List<Coordinate> rocks = new ArrayList<>();
            for(int row = 1; row <= gridIndex; row++){
                for(int col = 1; col <= gridIndex; col++){
                    if(grid[row][col] == ROCK_VALUE){
                        rocks.add(new Coordinate(row, col));
                    }
                }
            }
            return rocks;
        }

        private void initVisited(){
            for(boolean[] array: visited){
                Arrays.fill(array, false);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n+1][n+1];
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        List<Coordinate> startPoints = new ArrayList<>();
        for(int i = 0; i < k; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            startPoints.add(new Coordinate(r, c));
        }

        new Solver(grid, startPoints, m).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int r,
                int c
        ){
            this.row = r;
            this.col = c;
        }
    }
}