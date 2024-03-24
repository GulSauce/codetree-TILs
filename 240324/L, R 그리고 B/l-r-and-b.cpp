#include <iostream>

using namespace std;

int main() {
    string board[10];

    for(int y= 0; y <= 9; y++){
        cin >> board[y];
    }

    int bX, bY;
    int lX, lY;
    int rX, rY;

    for(int y = 0; y <= 9; y++){
        for(int x= 0; x <= 9; x++){
            if(board[y][x]== 'B'){
                bX = x;
                bY = y;
            }
            if(board[y][x]== 'L'){
                lX = x;
                lY = y;
            }
            if(board[y][x]== 'R'){
                rX = x;
                rY = y;
            }
        }
    }

    if(lX == bX){
        int result = abs(lY-bY)-1;
        if(rX == lX
        && min(lY, bY) <= rY
        && rY <= max(lY, bY)){
            result += 2;
        }
        cout << result;
        return 0;
    }

    if(lY == bY){
        int result = abs(lX-bX)-1;
        if(rY == lY
        && min(lX, bX) <= rX
        && rX <= max(lX, bX)){
            result += 2;
        }
        cout << result;
        return 0;
    }

    int result = abs(lX-bX) + abs(lY-bY) - 1;
    
    cout << result;
    return 0;
}