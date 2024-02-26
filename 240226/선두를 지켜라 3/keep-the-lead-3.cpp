#include <iostream>

using namespace std;

int aPosition[1000001];
int bPosition[1000001];

void calcPosition(int* position, int& currentTime, int v, int t){
    while(t--){
        int currentPosition = position[currentTime];
        position[++currentTime] = currentPosition + v;
    }
}

int calcHallOfFame(int totalTime){
    int beforeLeader = 0;
    int ans = 0;
    for(int t = 1; t <= totalTime; t++){
        int leader = 0;
        if(bPosition[t] < aPosition[t]){
            leader = 1;
        }
        if(aPosition[t] < bPosition[t]){
            leader = 2;
        }
        if(bPosition[t] == aPosition[t]){
            leader = 3;
        }
        if(beforeLeader != leader){
            ans++;
        }
        beforeLeader = leader;
    }
    return ans;
}

int main() {
    int N, M;
    cin >> N >> M;

    int currentAtime = 0;
    while(N--){
        int v, t;
        cin >> v >> t;
        calcPosition(aPosition, currentAtime, v, t);
    }
    
    int currentBtime = 0;
    while(M--){
        int v, t;
        cin >> v >> t;
        calcPosition(bPosition, currentBtime, v, t);
    }

    int totalTime = max(currentAtime, currentBtime);
    
    int ans = calcHallOfFame(totalTime);
    cout << ans;
    
    return 0;
}