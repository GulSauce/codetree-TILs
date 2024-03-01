#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int sum;
int status[6];

int main() {
    for(int i = 0; i <= 5; i++){
        cin >> status[i];
        sum += status[i];
    }

    int minDiff = INT_MAX;
    for(int a1 = 0; a1 <= 5; a1++){
        for(int a2 = a1+1; a2 <=5; a2++){
            for(int b1 = 0; b1 <= 5; b1++){
                for(int b2 = b1+1; b2 <=5; b2++){
                    if(a1 == b1 
                    || a1 == b2
                    || a2 == b1
                    || a2 == b2){
                        continue;
                    }

                    int sum1 = status[a1] + status[a2];
                    int sum2 = status[b1] + status[b2];
                    int sum3 = sum - sum1 - sum2;
                    int maxSum = max({sum1, sum2, sum3});
                    int minSum = min({sum1, sum2, sum3});
                    minDiff = min(minDiff, abs(maxSum-minSum));
                }
            }
        }
    }

    cout << minDiff;
    return 0;
}