#include <iostream>

using namespace std;

int aLegnthAtHour[1000*1000+1];
int bLegnthAtHour[1000*1000+1];

void runA(int& currentAPostion, int& currentATime, int v, int t){
    while(t--){
        currentAPostion += v;
        aLegnthAtHour[++currentATime] = currentAPostion;
    }
}

void runB(int& currentBPostion, int& currentBTime, int v, int t){
    while(t--){
        currentBPostion += v;
        bLegnthAtHour[++currentBTime] = currentBPostion;
    }
}

int calcChangeCount(int totalTime){
    char whoIsFirst = '\0';
    int cnt = 0;
    if(bLegnthAtHour[1] < aLegnthAtHour[1]){
        whoIsFirst = 'a';
    }
    if(aLegnthAtHour[1] < bLegnthAtHour[1]){
        whoIsFirst = 'b';
    }
 
    for(int t = 2; t <= totalTime; t++){
        if(whoIsFirst == 'a'){
            if(aLegnthAtHour[t] < bLegnthAtHour[t]){
                whoIsFirst = 'b';
                cnt++;
            }
        }
        if(whoIsFirst == 'b'){
            if(bLegnthAtHour[t] < aLegnthAtHour[t]){
                whoIsFirst = 'a';
                cnt++;
            }
        }
    }
    return cnt;
}

int main() {
    int N, M;
    cin >> N >> M;

    int currentAPostion = 0;
    int currentATime = 0;

    while(N--){
        int v, t;
        cin >> v >> t;
        runA(currentAPostion, currentATime, v, t);
    }

    int currentBPostion = 0;
    int currentBTime = 0;

    while(M--){
        int v, t;
        cin >> v >> t;
        runB(currentBPostion, currentBTime, v, t);
    }

    int ans = calcChangeCount(currentATime);
    cout << ans;
    return 0;
}