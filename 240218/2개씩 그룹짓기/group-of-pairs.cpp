#include <climits>
#include <iostream>
#include <algorithm>

using namespace std;

int N; 

int numbers[2000];

int getMaxValue(){
    int halfIndex = N-1;
    int lastIndex = 2*N-1;

    int curMaxValue = INT_MIN;

    for(int i = 0; i <= halfIndex; i++){
        curMaxValue = max(curMaxValue, numbers[i] + numbers[lastIndex-i]);
    }
    return curMaxValue;
}

int main() {
    cin >> N;

    int lastIndex = 2*N -1;
    for(int i = 0; i <= lastIndex; i++){
        cin >> numbers[i];
    }

    sort(numbers, numbers + lastIndex + 1);

    int minMaxValue = getMaxValue();

    cout << minMaxValue;
    return 0;
}