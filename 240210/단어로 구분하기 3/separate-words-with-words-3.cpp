#include <iostream>
#include <string>

using namespace std;

int main() {
    string str[10];
    for(int i = 0; i <= 9; i++){
        cin >> str[i];
    }
    for(int i = 9; i >= 0; i--){
        cout << str[i] << '\n';
    }
    return 0;
}