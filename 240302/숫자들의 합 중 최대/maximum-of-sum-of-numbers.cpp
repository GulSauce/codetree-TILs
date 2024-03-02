#include <iostream>

using namespace std;

int getSum(int number){
    int sum = 0;
    while(number){
        sum += number % 10;
        number /= 10; 
    }
    return sum;
}

int main() {
    int X, Y;
    cin >> X >> Y;

    int maxSum = 0;
    for(int i = X; i <= Y; i++){
        int currentSum = getSum(i);
        maxSum = max(maxSum, currentSum);
    }

    cout << maxSum;
    return 0;
}