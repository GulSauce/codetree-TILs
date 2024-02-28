#include <iostream>

using namespace std;

int board[20][20];

bool checkColumn(int x, int y){
    int target = board[y][x];
    for(int i = 0; i <= 4; i++){
        if(board[y][x+i] != target){
            return false;
        }
    }
    return true;
}

bool checkRow(int x, int y){
    int target = board[y][x];
    for(int i = 0; i <= 4; i++){
        if(board[y+i][x] != target){
            return false;
        }
    }
    return true;
}

bool checkDiagonal(int x, int y){
    int target = board[y][x];
    for(int i = 0; i <= 4; i++){
        if(board[y+i][x+i] != target){
            return false;
        }
    }
    return true;
}



int main() {
    for(int y = 1; y <= 19; y++){
        for(int x = 1; x <= 19; x++){
            cin >> board[y][x];
        }
    }
    
    for(int y = 1; y <= 15; y++){
        for(int x = 1; x <= 15; x++){
            if(board[y][x] == 0){
                continue;
            }
            if(checkColumn(x, y)){
                cout << board[y][x] << '\n';
                cout << y << ' ' << x + 2;
                return 0;
            }
            if(checkRow(x, y)){
                cout << board[y][x] << '\n';
                cout << y + 2 << ' ' << x;
                return 0;
            }
            if(checkDiagonal(x, y)){
                cout << board[y][x] << '\n';
                cout << y + 2 << ' ' << x + 2;
                return 0;
            }
        }
    }

    cout << 0;
    return 0;
}