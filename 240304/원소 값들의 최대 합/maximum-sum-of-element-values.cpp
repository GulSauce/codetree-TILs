#include <iostream>

using namespace std;

int n, m;

int numbers[101];

int caclSum(int startIndex){
    int loopCnt = m;
    int currentIndex = startIndex;
    int sum = 0;
    while(loopCnt--){
        sum += numbers[currentIndex];
        currentIndex = numbers[currentIndex];
    }
    return sum;
}

int main() {
    cin >> n >> m;
    for(int i = 1; i <= n; i++){
        cin >> numbers[i];
    }   
    
    int maxSum = 0;
    for(int startIndex = 1; startIndex <= n; startIndex++){
        int currentSum = caclSum(startIndex);
        maxSum = max(maxSum, currentSum);
    }
    cout << maxSum;
    return 0;
}