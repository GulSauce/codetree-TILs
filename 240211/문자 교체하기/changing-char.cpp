#include <iostream>
#include <string>

using namespace std;

int main() {
    string str1, str2; cin >> str1 >> str2;
    string pastedStr = str1.substr(0, 2);
    str2[0] = pastedStr[0];
    str2[1] = pastedStr[1];
    cout << str2;
    return 0;
}