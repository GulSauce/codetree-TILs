import java.util.*;

public class Main {
    private static class Solver {
        int[][] buildingState;
        WindInfo[] windInfos;
        final  int MAX_ROW_INDEX;
        final  int MAX_COLUMN_INDEX;

        public  Solver(
                int[][] buildingState,
                WindInfo[] windInfos
        ){
            this.buildingState = buildingState;
            this.windInfos = windInfos;
            MAX_ROW_INDEX = buildingState.length-1;
            MAX_COLUMN_INDEX = buildingState[0].length-1;
        }

        public void  solve(){
            for(WindInfo windInfo: windInfos){
                blowWindBothSide(windInfo);
            }
            printBuildingState();
        }

        private void printBuildingState(){
            for(int i = 1; i <= MAX_ROW_INDEX; i++){
                int[] state = buildingState[i];
                for(int value: state){
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void blowWindBothSide(WindInfo windInfo){
            if(windInfo.windDirection == WindDirection.valueOf("L")){
                moveRightAtRow(windInfo.r);
                if(isUpAffected(windInfo.r)) {
                    blowWindUpSide(new WindInfo(windInfo.r-1, "R"));
                }
                if(isDownAffected(windInfo.r)) {
                    blowWindDownSide(new WindInfo(windInfo.r+1, "R"));
                }
            }
            if(windInfo.windDirection == WindDirection.valueOf("R")){
                moveLeftAtRow(windInfo.r);
                if(isUpAffected(windInfo.r)){
                    blowWindUpSide(new WindInfo(windInfo.r-1, "L"));
                }
                if(isDownAffected(windInfo.r)) {
                    blowWindDownSide(new WindInfo(windInfo.r+1, "L"));
                }
            }
        }

        private void blowWindUpSide(WindInfo windInfo){
            if(windInfo.windDirection == WindDirection.valueOf("L")){
                moveRightAtRow(windInfo.r);
                if(!isUpAffected(windInfo.r)) {
                    return;
                }
                blowWindUpSide(new WindInfo(windInfo.r-1, "R"));
            }
            if(windInfo.windDirection == WindDirection.valueOf("R")){
                moveLeftAtRow(windInfo.r);
                if(!isUpAffected(windInfo.r)) {
                    return;
                }
                blowWindUpSide(new WindInfo(windInfo.r-1, "L"));
            }
        }

        private void blowWindDownSide(WindInfo windInfo){
            if(windInfo.windDirection == WindDirection.valueOf("L")){
                moveRightAtRow(windInfo.r);
                if(!isDownAffected(windInfo.r)){
                    return;
                }
                blowWindDownSide(new WindInfo(windInfo.r+1, "R"));
            }
            if(windInfo.windDirection == WindDirection.valueOf("R")){
                moveLeftAtRow(windInfo.r);
                if(!isDownAffected(windInfo.r)){
                    return;
                }
                blowWindDownSide(new WindInfo(windInfo.r+1, "L"));
            }
        }

        private void moveRightAtRow(int row){
            int lastValue = buildingState[row][MAX_COLUMN_INDEX];
            for(int i = MAX_COLUMN_INDEX; i >= 1; i--){
                buildingState[row][i] = buildingState[row][i-1];
            }
            buildingState[row][0] = lastValue;
        }

        private void moveLeftAtRow(int row){
            int firstValue = buildingState[row][0];
            for(int i = 1; i <= MAX_COLUMN_INDEX; i++){
                buildingState[row][i-1] = buildingState[row][i];
            }
            buildingState[row][MAX_COLUMN_INDEX] = firstValue;
        }

        private boolean isUpAffected(int row){
            if(row == 1) {
                return false;
            }
            for(int i = 0; i <= MAX_COLUMN_INDEX; i++){
                if(buildingState[row][i] == buildingState[row-1][i]){
                    return true;
                }
            }
            return false;
        }

        private boolean isDownAffected(int row){
            if(row == MAX_ROW_INDEX) {
                return false;
            }
            for(int i = 0; i <= MAX_COLUMN_INDEX; i++){
                if(buildingState[row][i] == buildingState[row+1][i]){
                    return true;
                }
            }
            return false;
        }
    }

    private enum WindDirection {
        L,
        R
    }

    private static class  WindInfo{
        int r;
        Enum<WindDirection> windDirection;

        public WindInfo(
                int r,
                String windDirection
        ){
            this.r = r;
            this.windDirection = WindDirection.valueOf(windDirection);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        final int Q = sc.nextInt();

        int[][] buildingState = new int[N+1][M];
        for(int y = 1; y <= N; y++){
            for(int x= 0; x < M; x++){
                buildingState[y][x] = sc.nextInt();
            }
        }

        WindInfo[] windInfos = new WindInfo[Q];
        for(int i = 0; i < Q; i++){
            int r = sc.nextInt();
            String d = sc.next();
            windInfos[i] = new WindInfo(r, d);
        }

        new Solver(buildingState, windInfos).solve();
    }
}