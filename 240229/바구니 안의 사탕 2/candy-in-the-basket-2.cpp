#include <iostream>

using namespace std;

int candyCountArray[101];

bool isInRange(int index){
    return 1 <= index && index <= 100;
}

int main() {
    int N, K;
    cin >> N >> K;
    
    while(N--){
        int candyCount, position;
        cin >> candyCount >> position;
        candyCountArray[position] += candyCount;
    }

    int maxCandyCount = 0;
    for(int c = 1; c <= 100; c++){
        int currentCandyCount = 0;
        for(int i = c-K; i <= c+K; i++){
            if(!isInRange(i)){
                continue;
            }
            currentCandyCount += candyCountArray[i];
        }
        maxCandyCount = max(maxCandyCount, currentCandyCount);
    }

    cout << maxCandyCount;
    return 0;
}