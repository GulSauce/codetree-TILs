#include <iostream>

using namespace std;

int main() {
    char ch[11];
    for(int i = 1; i <= 10; i++){
        cin >> ch[i];
    }
    cout << ch[2] << ' ' << ch[5] << ' ' << ch[8];
    return 0;
}