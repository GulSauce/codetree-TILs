#include <iostream>

using namespace std;

int main() {
    int N; cin >> N;
    int maxVal = 0;
    int nums[1001];

    int index = 0;
    for(int i = 1; i <= N; i++){
        int num; cin >> num;
        
        if(maxVal < num){
            maxVal = num;
            index = i;
        }
        
        nums[i] = num;
    }
    cout << index << ' ';
    while(index != 1){
        int curMaxVal = 0;
        int nextIndex = 0;
        for(int i = 1; i < index; i++){
            if(nums[i] <= curMaxVal){
                continue;
            }

            curMaxVal = nums[i];
            nextIndex = i;
        }
        index = nextIndex;
        cout << index << ' ';
    }
    return 0;
}