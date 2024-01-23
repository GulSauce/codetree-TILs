#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int num = 1;
    for(int y = 0; y < n; y++){
        for(int x = 0; x <= y; x++){
            cout << num++ << ' ';
        }
        cout << '\n';
    }
    return 0;
}