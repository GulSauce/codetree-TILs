#include <iostream>

using namespace std;

int main() {
    int n; 
    cin >> n;

    int decimalToBinary[100] = {};
    int digitCnt = 0;
    while(n){
        decimalToBinary[digitCnt++] = n % 2;
        n /= 2;
    }

    for(int i = digitCnt-1; i >= 0; i--){
        cout << decimalToBinary[i];
    }
    return 0;
}