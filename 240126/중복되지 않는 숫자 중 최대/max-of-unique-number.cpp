#include <iostream>

using namespace std;

int main() {
    int N; cin >> N;

    int nums[1001] = {};
    while(N--){
        int num; cin >> num;
        nums[num]++;
    }

    int maxVal = -1;
    for(int i = 1; i <= 1000; i++){
        if(0 == nums[i] || 2 <= nums[i]){
            continue;
        }

        if(maxVal < nums[i]){
            maxVal = i;
        }
    }

    cout << maxVal;
    return 0;
}