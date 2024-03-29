#include <iostream>
#include <cstring>

using namespace std;

int aPositionAtSecond[1000*1000+1];
int bPositionAtSecond[1000*1000+1];

void moveLeft(char who, int& currentTime, int& position, int time){
    int* positionAtSecond;
    if(who == 'A'){
        positionAtSecond = aPositionAtSecond;    
    }
    if(who == 'B'){
        positionAtSecond = bPositionAtSecond;    
    }
    while(time--){
        positionAtSecond[++currentTime] = --position;
    }
}

void moveRight(char who, int& currentTime, int& position, int time){
    int* positionAtSecond;
     if(who == 'A'){
        positionAtSecond = aPositionAtSecond;    
    }
    if(who == 'B'){
        positionAtSecond = bPositionAtSecond;    
    }
    while(time--){
        positionAtSecond[++currentTime] = ++position;
    }
}

int main() {
    int N, M;
    cin >> N >> M;
    
    int currentTime = 0;
    int aPosition = 0;

    while(N--){
        char d;
        int t;
        cin >> d >> t;
        if(d == 'L'){
            moveLeft('A', currentTime, aPosition, t);
        }
        if(d == 'R'){
            moveRight('A', currentTime, aPosition, t);
        }
    }

    currentTime= 0;
    int bPosition = 0;
    while(M--){
        char d;
        int t;
        cin >> d >> t;
        if(d == 'L'){
            moveLeft('B', currentTime, bPosition, t);
        }
        if(d == 'R'){
            moveRight('B', currentTime, bPosition, t);
        }
    }

    for(int i = 1; i <= currentTime; i++){
        if(aPositionAtSecond[i] == bPositionAtSecond[i]){
            cout << i;
            return 0;
        }
    }

    cout << -1;
    return 0;
}