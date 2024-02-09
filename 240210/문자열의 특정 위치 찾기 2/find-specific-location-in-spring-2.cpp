#include <iostream>
#include <string>

using namespace std;

int main() {
    string str[5] = {"apple", "banana", "grape", "blueberry", "orange"};
    char ch; cin >> ch;
    int cnt = 0;
    for(int i = 0; i <= 4; i++){
        if(str[i][2] == ch || str[i][3] == ch){
            cout << str[i] << '\n';
            cnt++;
        }
    }
    cout << cnt;
    return 0;
}