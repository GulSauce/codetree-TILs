#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    int sumVal = 0;
    for(int i = a; i <= b; i++)
        sumVal += i;
    
    cout << sumVal;
    return 0;
}