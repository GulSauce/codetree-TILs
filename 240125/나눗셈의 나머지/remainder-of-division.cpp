#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    int remainCount[10] = {};
    while(1 < a){
        remainCount[a % b]++;
        a /= b;
    }

    int remainCountSquareSum = 0;
    for(int i = 0; i <= 9; i++){
        remainCountSquareSum += remainCount[i]*remainCount[i];
    }

    cout << remainCountSquareSum;
    return 0;
}