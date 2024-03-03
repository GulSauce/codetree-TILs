#include <iostream>

using namespace std;

int dx[8] = {1, 1, 0, -1, -1, -1, 0, 1};
int dy[8] = {0, 1, 1, 1, 0, -1, -1, -1};

string ticTacToColumn[3];

bool isInRange(int x, int y){
    return 0 <= x && x <= 2 && 0 <= y && y <= 2;
}

bool checkIsCurrentCombiWin(int p1, int p2){
    for(int y = 0; y <= 2; y++){
        for(int x = 0; x <= 2; x++){
            for(int directionIndex = 0; directionIndex <= 7; directionIndex++){
                int p1Count = 0;
                int p2Count = 0;

                int currentX = x;
                int currentY = y;

                if(ticTacToColumn[currentY][currentX] == p1 + '0'){
                    p1Count++;
                }
                if(ticTacToColumn[currentY][currentX] == p2 + '0'){
                    p2Count++;
                }

                int loopCount = 2;
                while(loopCount--){
                    int testX = currentX + dx[directionIndex];
                    int testY = currentY + dy[directionIndex];
                    if(isInRange(testX, testY) == false){
                        break;
                    }

                    currentX = testX;
                    currentY = testY;
                    if(ticTacToColumn[currentY][currentX] == p1 + '0'){
                        p1Count++;
                    }
                    if(ticTacToColumn[currentY][currentX] == p2 + '0'){
                        p2Count++;
                    }
                }
                if(p1Count + p2Count == 3
                && 1 <= p1Count
                && 1 <= p2Count){
                    return true;
                }
            }
        }
    }

    return false;
}

int main() {
    for(int i = 0; i <= 2; i++){
        string currentColumn;
        cin >> currentColumn;
        ticTacToColumn[i] = currentColumn;
    }

    int ans = 0;

    for(int p1 = 1; p1 <= 9; p1++){
        for(int p2 = p1+1; p2 <= 9; p2++){
            bool isCurrentCombiWin = checkIsCurrentCombiWin(p1, p2);
            if(isCurrentCombiWin){
                ans++;
            }
        }
    }

    cout << ans;
    return 0;
}