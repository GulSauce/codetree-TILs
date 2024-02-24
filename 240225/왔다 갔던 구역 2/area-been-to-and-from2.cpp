#include <iostream>

using namespace std;

int board[2001];

void moveLeft(int& currentPosition, int count){
    currentPosition -= count;
     for(int i = 0; i <= count-1; i++){
        board[currentPosition+i]++;
    }
}

void moveRight(int& currentPosition, int count){
    while(count--){
        board[currentPosition++]++;
    }
}


int main() {
    int n;
    cin >> n;
    int currentPosition = 1000;
    while(n--){
        int x;
        char direction;
        cin >> x >> direction;
        if(direction == 'L'){
            moveLeft(currentPosition, x);
        }
        if(direction == 'R'){
            moveRight(currentPosition, x);
        }
    }

    int area = 0;

    for(int i = 0; i <= 2000; i++){
        if(2 <= board[i]){
            area++;
        }
    }

    cout << area;
    return 0;
}