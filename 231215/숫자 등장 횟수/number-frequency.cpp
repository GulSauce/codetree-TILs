#include <iostream>
#include <unordered_map>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    unordered_map<int, int> mp;
    while(n--){
        int num; cin >> num;
        mp[num]++;
    }
    while(m--){
        int num; cin >> num;
        cout << mp[num] << ' ';
    }
    return 0;
}