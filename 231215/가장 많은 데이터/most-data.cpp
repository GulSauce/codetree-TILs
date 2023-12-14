#include <string>
#include <iostream>
#include <unordered_map>

using namespace std;

int main() {
    int n; cin >> n;
    unordered_map<string, int> mp;
    while(n--){
        string str; cin >> str;
        mp[str]++;
    }
    int res = 0;
    for(auto iter = mp.begin(); iter != mp.end(); iter++)
        res = res < iter->second ? iter->second : res;

    cout << res;
    return 0;
}