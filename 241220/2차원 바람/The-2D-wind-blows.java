import java.util.*;

public class Main {
    
    // 좌표를 나타내는 클래스
    private static class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // 바람 정보를 담는 클래스
    private static class Wind {
        Coordinate topLeft;
        Coordinate bottomRight;

        public Wind(Coordinate topLeft, Coordinate bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }
    }

    // 문제 해결을 위한 Solver 클래스
    private static class Solver {
        int[][] buildingMatrix;
        Wind[] winds;
        int maxRow;
        int maxCol;

        public Solver(int[][] buildingMatrix, Wind[] winds) {
            this.buildingMatrix = buildingMatrix;
            this.winds = winds;
            this.maxRow = buildingMatrix.length - 1;
            this.maxCol = buildingMatrix[0].length - 1;
        }

        // 전체 바람 작용을 수행하고 결과를 출력
        public void solve() {
            for (Wind wind : winds) {
                rotateBoundary(wind.topLeft, wind.bottomRight);
                updateInternalValues(wind.topLeft, wind.bottomRight);
            }
            printMatrix();
        }

        // 행렬을 출력하는 메서드
        private void printMatrix() {
            for (int row = 1; row <= maxRow; row++) {
                for (int col = 1; col <= maxCol; col++) {
                    System.out.print(buildingMatrix[row][col] + " ");
                }
                System.out.println();
            }
        }

        // 직사각형의 경계를 시계 방향으로 한 칸 회전
        private void rotateBoundary(Coordinate topLeft, Coordinate bottomRight) {
            Deque<Integer> boundaryValues = extractBoundary(topLeft, bottomRight);
            // 시계 방향 회전을 위해 마지막 요소를 앞에 추가
            boundaryValues.addFirst(boundaryValues.pollLast());

            // 회전된 값을 다시 행렬에 삽입
            insertBoundary(topLeft, bottomRight, boundaryValues);
        }

        // 직사각형의 경계 값을 추출하는 메서드
        private Deque<Integer> extractBoundary(Coordinate topLeft, Coordinate bottomRight) {
            Deque<Integer> boundary = new ArrayDeque<>();

            // 상단 경계
            for (int col = topLeft.col; col <= bottomRight.col; col++) {
                boundary.add(buildingMatrix[topLeft.row][col]);
            }
            // 우측 경계
            for (int row = topLeft.row + 1; row <= bottomRight.row; row++) {
                boundary.add(buildingMatrix[row][bottomRight.col]);
            }
            // 하단 경계
            for (int col = bottomRight.col - 1; col >= topLeft.col; col--) {
                boundary.add(buildingMatrix[bottomRight.row][col]);
            }
            // 좌측 경계
            for (int row = bottomRight.row - 1; row >= topLeft.row + 1; row--) {
                boundary.add(buildingMatrix[row][topLeft.col]);
            }

            return boundary;
        }

        // 회전된 경계 값을 행렬에 삽입하는 메서드
        private void insertBoundary(Coordinate topLeft, Coordinate bottomRight, Deque<Integer> boundaryValues) {
            // 상단 경계
            for (int col = topLeft.col; col <= bottomRight.col; col++) {
                buildingMatrix[topLeft.row][col] = boundaryValues.pollFirst();
            }
            // 우측 경계
            for (int row = topLeft.row + 1; row <= bottomRight.row; row++) {
                buildingMatrix[row][bottomRight.col] = boundaryValues.pollFirst();
            }
            // 하단 경계
            for (int col = bottomRight.col - 1; col >= topLeft.col; col--) {
                buildingMatrix[bottomRight.row][col] = boundaryValues.pollFirst();
            }
            // 좌측 경계
            for (int row = bottomRight.row - 1; row >= topLeft.row + 1; row--) {
                buildingMatrix[row][topLeft.col] = boundaryValues.pollFirst();
            }
        }

        // 내부 영역의 값을 인접한 원소들과의 평균으로 업데이트
        private void updateInternalValues(Coordinate topLeft, Coordinate bottomRight) {
            // 업데이트된 값을 저장할 임시 배열
            int[][] temp = new int[maxRow + 1][maxCol + 1];

            for (int row = topLeft.row; row <= bottomRight.row; row++) {
                for (int col = topLeft.col; col <= bottomRight.col; col++) {
                    temp[row][col] = calculateAverage(row, col);
                }
            }

            // 임시 배열의 값을 원래 행렬에 반영
            for (int row = topLeft.row; row <= bottomRight.row; row++) {
                for (int col = topLeft.col; col <= bottomRight.col; col++) {
                    buildingMatrix[row][col] = temp[row][col];
                }
            }
        }

        // 특정 위치의 값과 인접한 원소들의 평균을 계산하는 메서드
        private int calculateAverage(int row, int col) {
            int sum = buildingMatrix[row][col];
            int count = 1;

            // 상
            if (isValid(row - 1, col)) {
                sum += buildingMatrix[row - 1][col];
                count++;
            }
            // 하
            if (isValid(row + 1, col)) {
                sum += buildingMatrix[row + 1][col];
                count++;
            }
            // 좌
            if (isValid(row, col - 1)) {
                sum += buildingMatrix[row][col - 1];
                count++;
            }
            // 우
            if (isValid(row, col + 1)) {
                sum += buildingMatrix[row][col + 1];
                count++;
            }

            return sum / count; // 평균값 (버림 처리)
        }

        // 주어진 좌표가 행렬 내에 있는지 확인하는 메서드
        private boolean isValid(int row, int col) {
            return row >= 1 && row <= maxRow && col >= 1 && col <= maxCol;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 행렬의 크기과 바람의 횟수 입력
        int N = sc.nextInt(); // 행의 수
        int M = sc.nextInt(); // 열의 수
        int Q = sc.nextInt(); // 바람의 횟수

        // 행렬 초기화 (1-based 인덱싱)
        int[][] buildingMatrix = new int[N + 1][M + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                buildingMatrix[row][col] = sc.nextInt();
            }
        }

        // 바람 정보 입력
        Wind[] winds = new Wind[Q];
        for (int i = 0; i < Q; i++) {
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            Coordinate topLeft = new Coordinate(r1, c1);
            Coordinate bottomRight = new Coordinate(r2, c2);
            winds[i] = new Wind(topLeft, bottomRight);
        }

        // Solver를 통해 문제 해결
        Solver solver = new Solver(buildingMatrix, winds);
        solver.solve();
    }
}