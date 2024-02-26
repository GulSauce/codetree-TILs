#include <iostream>

using namespace std;

pair<char, char> hallOfFame[1000001];
int aPosition[1000001];
int bPosition[1000001];

void calcPosition(int* position, int& currentTime, int v, int t){
    while(t--){
        int currentPosition = position[currentTime];
        position[++currentTime] = currentPosition + v;
    }
}

void calcHallOfFame(int totalTime){
    for(int t = 1; t <= totalTime; t++){
        if(bPosition[t] <= aPosition[t]){
            hallOfFame[t].first = 'a';
        }
        if(aPosition[t] <= bPosition[t]){
            hallOfFame[t].second = 'b';
        }
    }
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
    calcHallOfFame(totalTime);

    int ans = 0;
    for(int i = 1; i <= totalTime; i++){
        if(hallOfFame[i] != hallOfFame[i-1]){
            ans++;
        }
    }

    cout << ans;
    return 0;
}