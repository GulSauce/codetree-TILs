#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 0; y < n; y++){
        for(int x = n-y; x <= n; x++){
            cout << x << ' ';
        }
        cout << '\n';
    }
    return 0;
}