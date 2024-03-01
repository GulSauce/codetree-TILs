#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int sum = 0;
int status[5];

int main() {
    for(int i = 0; i <= 4; i++){
        cin >> status[i];
        sum += status[i];
    }

    int minDiff = INT_MAX;

    for(int a1 = 0; a1 <= 4; a1++){
        for(int a2 = a1+1; a2<=4; a2++){
            for(int c1 = 0; c1 <=4 ; c1++){
                if(a1 == c1 || a2 == c1){
                    continue;
                }

                int aStatusSum = status[a1] + status[a2];
                int cStatusSum = status[c1];
                int bStatusSum = sum - aStatusSum - cStatusSum;
                
                if(aStatusSum == bStatusSum
                || aStatusSum == cStatusSum
                || bStatusSum == cStatusSum){
                    continue;
                }

                int minStatusSum = min({aStatusSum, bStatusSum, cStatusSum});
                int maxStatusSum = max({aStatusSum, bStatusSum, cStatusSum});
                minDiff = min(minDiff, abs(maxStatusSum-minStatusSum));
            }
        }
    }
    cout << minDiff;
    return 0;
}