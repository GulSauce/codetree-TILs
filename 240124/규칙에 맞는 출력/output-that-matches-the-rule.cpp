#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 0; y < n; y++){
        for(int x = 5-y; x <= 5; x++){
            cout << x << ' ';
        }
        cout << '\n';
    }
    return 0;
}