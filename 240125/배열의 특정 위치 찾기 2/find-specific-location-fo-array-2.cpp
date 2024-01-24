#include <cmath>
#include <iostream>

using namespace std;

int main() {
    int num[11];
    int oddSum = 0;
    int evenSum = 0;
    for(int i = 1; i <= 10; i++){
        cin >> num[i];
    }
    for(int i = 1; i <= 10; i+=2){
        oddSum += num[i];
    }
    for(int i = 2; i <= 10; i+=2){
        evenSum += num[i];
    }
    cout << abs(oddSum -evenSum);
    return 0;
}