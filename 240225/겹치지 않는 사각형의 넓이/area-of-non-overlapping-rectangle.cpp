#include <iostream>

using namespace std;

int offset = 1000;

bool board[2000][2000];

void fetchSquare(int x1, int y1, int x2, int y2){
    for(int y = y1; y <= y2-1; y++){
        for(int x = x1; x <= x2-1; x++){
            board[y][x] = true;
        }
    }
}

void fetchSquareM(int x1, int y1, int x2, int y2){
    for(int y = y1; y <= y2-1; y++){
        for(int x = x1; x <= x2-1; x++){
            board[y][x] = false;
        }
    }
}

void setOffset(int& x1, int& y1, int& x2, int& y2){
    x1 += offset;
    y1 += offset;
    x2 += offset;
    y2 += offset;
}

int main() {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    setOffset(x1, y1, x2, y2);
    fetchSquare(x1, y1, x2, y2);

    cin >> x1 >> y1 >> x2 >> y2;
    setOffset(x1, y1, x2, y2);
    fetchSquare(x1, y1, x2, y2);

    cin >> x1 >> y1 >> x2 >> y2;
    setOffset(x1, y1, x2, y2);
    fetchSquareM(x1, y1, x2, y2);

    int area = 0;

    for(int y = 0; y <= 1999; y++){
        for(int x = 0; x <= 1999; x++){
            if(board[y][x]){
                area++;
            }
        }
    }

    cout << area;
    return 0;
}