#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int sumVal = 0;
    for(int i = n; i <= 100; i++)
        sumVal += i;

    cout << sumVal;
    return 0;
}