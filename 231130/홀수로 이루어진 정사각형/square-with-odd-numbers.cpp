#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for (int i = 0; i < n; i++){
        for(int j = 0; j < n; j++)
            cout << 11 + 2*j + 2*i << ' ';
        cout << '\n';
    }
    return 0;
}