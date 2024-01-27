#include <iostream>

using namespace std;

int main() {
    for(int y = 0; y < 5; y++){ 
        for(int x = 0; x < 3; x++){
            char ch; cin >> ch;
            ch += ('A' - 'a');
            cout << ch << ' ';
        }
        cout << '\n';
    }
    return 0;
}