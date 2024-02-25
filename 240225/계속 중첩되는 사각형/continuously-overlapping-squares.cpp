#include <iostream>

using namespace std;

int board[200][200];

void setOffset(int& x1, int& y1, int& x2, int& y2){
    int offset = 100;
    x1 += offset;
    y1 += offset;
    x2 += offset;
    y2 += offset;  
}

void fetchSquare(int x1, int y1, int x2, int y2, char color){
    for(int y = y1; y <= y2-1; y++){
        for(int x = x1; x <= x2-1; x++){
            board[y][x] = color;
        }
    }
}

int main() {
    int n;
    cin >> n;
    for(int i = 1; i <= n; i++){
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        setOffset(x1, y1, x2, y2);
        char color = 'R';
        if(i % 2 == 0){
            color = 'B';
        }
        fetchSquare(x1, y1, x2, y2, color);
    }

    int area = 0;
    for(int y = 0; y <= 199; y++){
        for(int x = 0; x <= 199; x++){
            if(board[y][x] == 'B'){
                area++;
            }
        }
    }

    cout << area;
    return 0;
}