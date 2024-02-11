#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    str.erase(1, 1);

    int lastIdx = str.length() - 1;
    str.erase(lastIdx -1 , 1);
    cout << str;
    return 0;
}