#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    char start = 'A';
    for(int y = 0; y < n; y++){
        for(int x = 0; x <= y; x++){
            cout << start++;
        }
        cout << '\n';
    }
    return 0;
}