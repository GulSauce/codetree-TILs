#include <iostream>

using namespace std;

int board[100][100];

int n;
int dx[4] = {1, 0, -1 ,0};
int dy[4] = {0, 1, 0, -1};

bool isInRange(int x, int y){
    return 0 <= x && x <= n-1
    && 0 <= y && y <= n-1;
}

int main() {
    cin >> n;

    int currentX = n / 2;
    int currentY = n / 2;
    int value = 1;
    int lastValue = n*n;

    board[currentY][currentX] = value;

    int currentMoveCount = 0;
    int moveLength = 1;
    int directionIndex = 0;

    for(value = 2; value <= lastValue; value++){
        while(true){
            int testX = currentX + dx[directionIndex];
            int testY = currentY + dy[directionIndex];
            if(!isInRange(testX, testY) && currentMoveCount == moveLength){
                directionIndex = (directionIndex - 1 + 4) % 4;
                continue;
            }
            currentMoveCount = 0;
            break;
        }
        currentMoveCount++;
        currentX += dx[directionIndex];
        currentY += dy[directionIndex];
        board[currentY][currentX] = value;
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= n-1; x++){
            cout << board[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}