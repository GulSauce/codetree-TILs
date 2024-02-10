#include <iostream>
#include <string>

using namespace std;

int main() {
    int n; cin >> n;
    string concatedStr = "";
    while(n--){
        string str; cin >> str;
        concatedStr += str;
    }
    cout << concatedStr;

    return 0;
}