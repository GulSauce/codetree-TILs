#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    int length = str.length();
    for(int i = 0; i <= length; i++){
        cout << str << '\n';
        str = str.substr(length-1, 1) + str.substr(0, length-1);
    }
    return 0;
}