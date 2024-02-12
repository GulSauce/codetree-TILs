#include <iostream>

using namespace std;

int main() {
    int asciiValue[5];
    for(int i = 0; i <= 4; i++){
        cin >> asciiValue[i];
    }

    for(int i = 0 ; i <= 4; i++){
        cout << (char)asciiValue[i] << ' ';
    }
    return 0;
}