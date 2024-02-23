#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int blockPosition[101];

void setBlock(int s, int e){
    for(int i = s; i <= e; i++){
        blockPosition[i]++;
    }
}

int main() {
    int N, K;
    cin >> N >> K;

    while(K--){
        int A, B;
        cin >> A >> B;
        setBlock(A, B);
    }

    int maxBlockCount = INT_MIN;

    for(int i = 1; i <= N; i++){
        maxBlockCount = max(maxBlockCount, blockPosition[i]);
    }

    cout << maxBlockCount;
    return 0;
}