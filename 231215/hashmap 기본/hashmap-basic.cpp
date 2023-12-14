#include <string>
#include <iostream>
#include <unordered_map>

using namespace std;

int main() {
    unordered_map<int ,int> m;
    int n; cin >> n;
    while(n--){
        string str; cin >> str;
        if(str == "add"){
            int k, v; cin >> k >> v;
            m[k] = v;
        }
        if(str == "remove"){
            int k; cin >> k;
            m.erase(k);
        }
        if(str == "find"){
            int k; cin >> k;
            if(m.find(k) == m.end())
                cout << "None" << '\n';
            else
                cout << m[k] << '\n';
        }
    }
    return 0;
}