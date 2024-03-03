#include <iostream>

using namespace std;

int main() {
    int A, B, C;
    cin >> A >> B >> C;

    int maxSum = 0;
    for(int i = 1; i*A <= C; i++){
        int aSum = i*A;
        int bSum = (C-aSum) / B * B;
        maxSum = max(aSum +  bSum, maxSum);
    }

    cout << maxSum;
    return 0;
}