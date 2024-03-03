#include <iostream>

using namespace std;

int main() {
    int A, B, C;
    cin >> A >> B >> C;

    int maxSum = 0;
    for(int aCount = 0; aCount <= 1000; aCount++){
        for(int bCount = 0; bCount <= 1000; bCount++){
            int sum = aCount*A + bCount*B;
            if(C + 1 <= sum){
                continue;
            }
            maxSum = max(sum, maxSum);
        }
    }

    cout << maxSum;
    return 0;
}