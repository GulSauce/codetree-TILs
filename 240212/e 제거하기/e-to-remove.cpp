#include <iostream>
#include <string>

using namespace std;

int main() {
    string str; cin >> str;
    int searchedIdx = str.find('e');
    str.erase(searchedIdx, 1);
    cout << str;
    return 0;
}