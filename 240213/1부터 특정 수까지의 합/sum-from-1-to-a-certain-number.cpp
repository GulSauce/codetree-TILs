#include <iostream>

using namespace std;

int sum1ToNAndDivBY10(int N){
    int sumValue = N*(N+1)/2;
    int divValue = sumValue / 10;
    return divValue;
}

int main() {
    int N; cin >> N;
    int result = sum1ToNAndDivBY10(N);
    cout << result;
    return 0;
}