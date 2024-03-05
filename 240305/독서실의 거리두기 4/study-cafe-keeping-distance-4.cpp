#include <iostream>
#include <climits>

using namespace std;

string libarySeat;

int calcMinDistAfterSeat(int first, int second){
    libarySeat[first] = '1';
    libarySeat[second] = '1';

    int cnt = 0;
    int seatPosition[100];
    for(int i = 0; libarySeat[i] != '\0'; i++){
        if(libarySeat[i] == '1'){
            seatPosition[cnt++] = i;
        }
    }

    int minDist = INT_MAX;
    for(int i = 1; i <= cnt-1; i++){
        int currentDist = seatPosition[i]-seatPosition[i-1];
        minDist = min(minDist, currentDist);
    }
    
    libarySeat[first] = '0';
    libarySeat[second] = '0';

    return minDist;
}

int main() {
    int N;
    cin >> N;

    cin >> libarySeat;

    int maxMinDist = 0;
    for(int first = 0; first <= N-1; first++){
        for(int second = first+1; second <= N-1; second++){
            if(libarySeat[first] == '1' || libarySeat[second] == '1'){
                continue;
            }
            maxMinDist = max(maxMinDist, calcMinDistAfterSeat(first, second));
        }
    }

    cout << maxMinDist;
    return 0;
}