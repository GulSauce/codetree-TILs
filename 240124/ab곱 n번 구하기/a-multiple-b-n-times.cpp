#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 0; y < n; y++){
        int a, b; cin  >> a >> b;
        int sumVal = 1;
        for(int val = a; val <= b; val++){
            sumVal *= val;
        }
        cout << sumVal << '\n';
    }
    return 0;
}