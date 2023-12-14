#include <string>
#include <cstring>
#include <iostream>
#include <unordered_map>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    unordered_map<string, int> stringToInt;
    unordered_map<int, string> intToString;
    for(int i = 1; i <= n; i++){
        string str; cin >> str;
        stringToInt[str] = i;
        intToString[i] = str;
    }
    while(m--){
        string target; cin >> target;
        if(stringToInt.find(target) == stringToInt.end())
            cout << intToString[stoi(target)];
        else
            cout << stringToInt[target];
        cout << '\n';
    }
    return 0;
}