#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = n; y > 0; y--){
        for(int i = 0; i < y; i++){
            for(int x = 0; x < y; x++)
                cout << '*';
            cout << ' ';
        }
        cout << '\n';
    }
    return 0;
}