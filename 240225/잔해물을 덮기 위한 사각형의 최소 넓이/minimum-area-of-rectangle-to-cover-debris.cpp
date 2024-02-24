#include <iostream>

using namespace std;

int offset = 1000;

bool board[2000][2000];

void setOffset(int& x1, int& y1, int& x2, int& y2){
    x1 += offset;
    y1 += offset;
    x2 += offset;
    y2 += offset;
}

int findLeftMostY(){
    for(int y = 1999; y >= 0; y--){
        for(int x = 0; x <= 1999; x++){
            if(board[y][x]){
                return y;
            }
        }
    }
}

int findLeftMostX(){
    for(int x = 0; x <= 1999; x++){
        for(int y = 0; y <= 1999; y++){
            if(board[y][x]){
                return x;
            }
        }
    }
};

int findRightMostY(){
    for(int y = 0; y <= 1999; y++){
        for(int x = 0; x <= 1999; x++){
            if(board[y][x]){
                return y;
            }
        }
    }
}

int findRightMostX(){
    for(int x = 1999; x >= 0; x--){
        for(int y = 0; y <= 1999; y++){
            if(board[y][x]){
                return x;
            }
        }
    }
}

void fetchFirstSquare(int x1, int y1, int x2, int y2){
    for(int y = y1; y <= y2-1; y++){
        for(int x = x1; x <= x2-1; x++){
            board[y][x] = true;
        }
    }
}

void fetchSecondSquare(int x1, int y1, int x2, int y2){
    for(int y = y1; y <= y2-1; y++){
        for(int x = x1; x <= x2-1; x++){
            board[y][x] = false;
        }
    }
}


int main() {
    int x1, y1, x2, y2;

    cin >> x1 >> y1 >> x2 >> y2;
    setOffset(x1, y1, x2, y2);
    fetchFirstSquare(x1, y1, x2, y2);
    
    cin >> x1 >> y1 >> x2 >> y2;
    setOffset(x1, y1, x2, y2);
    fetchSecondSquare(x1, y1, x2, y2);

    int leftmostY = findLeftMostY();
    int leftmostX = findLeftMostX();
    int rightmostY = findRightMostY();
    int rightmostX = findRightMostX();

    int width = rightmostX - leftmostX + 1;
    int heigth = leftmostY - rightmostY + 1;

    cout << width * heigth;
    return 0;
}