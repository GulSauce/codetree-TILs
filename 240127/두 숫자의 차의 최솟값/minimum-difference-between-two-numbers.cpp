#include <cmath>
#include <climits>
#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int nums[100];
    for(int i = 0; i <= n-1; i++){
        cin >> nums[i];
    }

    int minDiff = INT_MAX;
    for(int i = 0; i <= n-2; i++){
        int curDiff = abs(nums[i] - nums[i+1]);
        if(curDiff < minDiff){
            minDiff = curDiff;
        }
    }
    cout << minDiff;
    return 0;
}