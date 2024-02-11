#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    str[1] = 'a';
    
    int lastIdx = str.length() - 1;
    str[lastIdx-1] = 'a';
    cout << str;
    return 0;
}