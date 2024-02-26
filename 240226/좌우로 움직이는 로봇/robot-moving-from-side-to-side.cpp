#include <iostream>

using namespace std;

int robotAPosition[2000001];
int robotBPosition[2000001];

void moveLeft(int* robotPostion, int& currentPosition, int& currentTime, int t){
    while(t--){
        robotPostion[++currentTime] = --currentPosition;
    }
}

void moveRight(int* robotPostion, int& currentPosition, int& currentTime, int t){
    while(t--){
        robotPostion[++currentTime] = ++currentPosition;
    }
}

int main() {
    int n, m;
    cin >> n >> m;
    
    int currentAPosition = 0;
    int currentATime = 0;

    int currentBPosition = 0;
    int currentBTime = 0;

    while(n--){
        int t;
        char d;
        cin >> t >> d;
        if(d == 'L'){
            moveLeft(robotAPosition, currentAPosition, currentATime, t);
        }
        if(d == 'R'){
            moveRight(robotAPosition, currentAPosition, currentATime, t);
        }
    }
    while(m--){
        int t;
        char d;
        cin >> t >> d;
        if(d == 'L'){
            moveLeft(robotBPosition, currentBPosition, currentBTime, t);
        }
        if(d == 'R'){
            moveRight(robotBPosition, currentBPosition, currentBTime, t);
        }
    }

    int totalTime = max(currentATime, currentBTime);
    
    for(int i = currentATime; i <= totalTime; i++){
        robotAPosition[i] = robotAPosition[currentATime];
    }
    for(int i = currentBTime; i <= totalTime; i++){
        robotBPosition[i] = robotBPosition[currentBTime];
    }


    int ans = 0;
    for(int i = 1; i <= totalTime; i++){
        if(robotAPosition[i-1] != robotBPosition[i-1] 
        && robotAPosition[i] == robotBPosition[i]){
            ans++;
        }
    }
    cout << ans;
    return 0;
}