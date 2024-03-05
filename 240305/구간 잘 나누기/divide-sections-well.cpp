#include <iostream>

using namespace std;

int n, m;

int numbers[100];

bool checkIsValid(int maxSum){
    int partitionCount = 0;
    int currentSum = 0;
    for(int i = 0; i <= n-1; i++){
        int testSum = currentSum + numbers[i];
        if(maxSum + 1 <= numbers[i]){
            return false;
        }
        if(maxSum + 1 <= testSum){
            partitionCount++;
            currentSum = numbers[i];
            continue;
        }
        currentSum = testSum;
    }
    
    if(m <= partitionCount){
        return false;
    }

    return true;
}

int main() {
    cin >> n >> m;

    for(int i = 0; i <= n-1; i++){
        cin >> numbers[i];
    }

    for(int i = 1; i <= 10000; i++){
        if(checkIsValid(i)){
            cout << i;
            return 0;
        }
    }

    return 0;
}