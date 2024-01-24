#include <iostream>

using namespace std;

int main() {
    char ch[10];
    for(int i = 0; i < 10; i++){
        cin >> ch[i];
    }
    for(int i = 9; i >= 0; i--){
        cout << ch[i];
    }
    return 0;
}