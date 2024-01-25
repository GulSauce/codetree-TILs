#include <iostream>

using namespace std;

int main() {
    int N; cin >> N;

    int numCount[1001] = {};
    while(N--){
        int num; cin >> num;
        numCount[num]++;
    }

    int maxVal = -1;
    for(int i = 1; i <= 1000; i++){
        if(0 == numCount[i] || 2 <= numCount[i]){
            continue;
        }

        if(maxVal < i){
            maxVal = i;
        }
    }

    cout << maxVal;
    return 0;
}