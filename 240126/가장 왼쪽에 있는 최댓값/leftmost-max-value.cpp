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

    while(index != 1){
        cout << index << ' ';
        int curMaxVal = 0;
        for(int i = 1; i < index; i++){
            if(nums[i] < curMaxVal){
                continue;
            }

            curMaxVal = nums[i];
            index = i;
        }
    }
    cout << index;
    return 0;
}