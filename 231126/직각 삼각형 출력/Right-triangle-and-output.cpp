#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 0; y < n; y++){
        for(int x = 0; x < 2*y+1; x++)
            cout << '*';
        cout << '\n';
    }
    return 0;
}