#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    int length = str.length();
    string shiftedStr = str.substr(1, length-1) + str.substr(0, 1);
    cout << shiftedStr;
    return 0;
}