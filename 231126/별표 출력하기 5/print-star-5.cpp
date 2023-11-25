#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 0; y < n; y++){
        for(int i = 0; i < n-y; i++){
            for(int x = 0; x < n-y; x++)
                cout << '*';
            cout << ' ';
        }
        cout << '\n';
    }
    return 0;
}