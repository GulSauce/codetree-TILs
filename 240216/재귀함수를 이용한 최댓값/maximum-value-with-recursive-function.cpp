#include <iostream>

using namespace std;

int number[100];
int n; 

int getMaxValue(int curIndex) {
    if(curIndex == n){
        return 0;
    }
    
    int curMaxValue = getMaxValue(curIndex + 1); 

    if(curMaxValue <= number[curIndex]){
        curMaxValue = number[curIndex];
    }

    return curMaxValue;
}

int main() {
    cin >> n;

    for(int i = 0; i <= n-1; i++){
        cin >> number[i];
    }

    int maxValue = getMaxValue(0);

    cout << maxValue;
    return 0;
}